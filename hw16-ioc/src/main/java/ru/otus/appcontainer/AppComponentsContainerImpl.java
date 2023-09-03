package ru.otus.appcontainer;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.exception.ComponentNotFoundException;
import ru.otus.exception.ConflictingComponentException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    public AppComponentsContainerImpl(Class<?> ... initialConfigClasses) {
        processConfigs(initialConfigClasses);
    }

    public AppComponentsContainerImpl(String initialConfigClassesPackage) {
        processConfigs(initialConfigClassesPackage);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        try {
            Object methodClassInstance = configClass.getDeclaredConstructor().newInstance();
            var configClassMethods = new ArrayList<>(Arrays.asList(configClass.getMethods()));
            var appComponentsMethodsFilteredSorted = configClassMethods.stream()
                    .filter(method -> method.isAnnotationPresent(AppComponent.class))
                    .sorted(Comparator.comparingInt(method -> method.getAnnotation(AppComponent.class).order()))
                    .toList();
            appComponentsMethodsFilteredSorted.forEach(method -> addComponentToContext(methodClassInstance, method));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void processConfigs(Class<?> ... configClasses) {
        var configs = new ArrayList<>(Arrays.asList(configClasses));
        configs.forEach(this::checkConfigClass);
        var configsSorted = configs.stream()
                .sorted(Comparator.comparingInt(method -> method.getAnnotation(AppComponentsContainerConfig.class).order()))
                .toList();
        configsSorted.forEach(this::processConfig);
    }

    private void processConfigs(String configClassesPackage) {
        Set<Class<?>> packageClasses = findAllClassesUsingReflectionsLibrary(configClassesPackage);
        var configClasses = packageClasses.stream()
                .filter(clazz -> clazz.isAnnotationPresent(AppComponentsContainerConfig.class))
                .sorted(Comparator.comparingInt(clazz -> clazz.getAnnotation(AppComponentsContainerConfig.class).order()))
                .toList();
        Class<?>[] classArray = configClasses.toArray(new Class<?>[0]);
        processConfigs(classArray);
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    public Set<Class<?>> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(Object.class));
    }

    private void addComponentToContext(Object methodClassInstance, Method appConfigMethod) {
        try {
            var componentName = getComponentName(appConfigMethod);
            var existingComponent = appComponentsByName.get(componentName);
            if (existingComponent != null) {
                throw new ConflictingComponentException(
                        String.format("Component with name %s already exists", componentName));
            }
            var component = createComponent(methodClassInstance, appConfigMethod);
            appComponents.add(component);
            appComponentsByName.put(componentName, component);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getComponentName(Method method) {
        var componentName = method.getAnnotation(AppComponent.class).name();
        if (componentName == null || componentName.isEmpty()) {
            char[] c = method.getName().toCharArray();
            c[0] = Character.toLowerCase(c[0]);
            componentName = new String(c);
        }
        return componentName;
    }

    private Object createComponent(Object methodClassInstance, Method appConfigMethod)
            throws InvocationTargetException, IllegalAccessException {
        Object[] parameters = new Object[appConfigMethod.getParameters().length];
        for (int i = 0; i < appConfigMethod.getParameters().length; i++) {
            var parameter = appConfigMethod.getParameters()[i];
            var parameterName = parameter.getName();
            var parameterClass = parameter.getType();
            var parameterValue = appComponents.stream()
                    .filter(obj -> parameterClass.isAssignableFrom(obj.getClass()))
                    .findFirst()
                    .orElseThrow(() ->
                            new ComponentNotFoundException(String.format("Component %s is not found", parameterName)));
            parameters[i] = parameterValue;
        }
        return appConfigMethod.invoke(methodClassInstance, parameters);
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        var foundComponents = appComponents.stream()
                .filter(componentClass::isInstance)
                .map(componentClass::cast)
                .toList();
        if (foundComponents.isEmpty()) {
            throw new ComponentNotFoundException(String.format("Component %s not found", componentClass.getName()));
        }
        if (foundComponents.size() > 1) {
            throw new ConflictingComponentException(String.format("Found more than one component %s", componentClass.getName()));
        }
        return foundComponents.get(0);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        var component = appComponentsByName.get(componentName);
        if (component == null) {
            throw new ComponentNotFoundException(String.format("Component %s not found", componentName));
        }
        var clazz = component.getClass();
        return (C) clazz.cast(component);
    }
}
