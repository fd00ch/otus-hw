package org.otus.annotationprocessor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.otus.annotation.CustomToString;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;

@SupportedAnnotationTypes("org.otus.annotation.CustomToString")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class CustomToStringProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(CustomToString.class);
        for (Element element : annotatedElements) {
            if (element.getKind() != ElementKind.CLASS) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Not a class", element);
                continue;
            }
            if (element.getKind() == ElementKind.CLASS) {
                PackageElement packageElement = processingEnv.getElementUtils().getPackageOf(element);
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, String.format("Class name: %s, %s, %s ",
                        element.getSimpleName(), element.getKind(), packageElement.getQualifiedName()));

                ClassName field = ClassName.get("java.lang.reflect", "Field");

                MethodSpec toCustomString = MethodSpec.methodBuilder("toCustomString")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(String.class)
                        .addParameter(Object.class, "object")
                        .addStatement("$T[] allFields = object.getClass().getDeclaredFields()", Field.class)
                        .addStatement("StringBuilder sb = new StringBuilder()")
                        .addStatement("sb.append(object.getClass().getSimpleName()).append(\": {\")")
                        .beginControlFlow("for (Field field : allFields)")
                        .addStatement("field.setAccessible(true)")
                        .beginControlFlow("try")
                        .addStatement("sb.append(field.getName()).append(\" is '\").append(field.get(object)).append(\"', \")")
                        .nextControlFlow("catch (IllegalAccessException e)")
                        .addStatement("e.printStackTrace()")
                        .endControlFlow()
                        .endControlFlow()
                        .addStatement("sb.delete(sb.length() - 2, sb.length())")
                        .addStatement("sb.append(\"}\")")
                        .addStatement("return sb.toString()")
                        .build();

                TypeSpec javaClass = TypeSpec.classBuilder("CustomToStringHelper")
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(toCustomString)
                        .build();

                JavaFile javaFile = JavaFile.builder(String.valueOf(packageElement.getQualifiedName()), javaClass)
                        .build();
                try {
                    javaFile.writeTo(processingEnv.getFiler());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }
}
