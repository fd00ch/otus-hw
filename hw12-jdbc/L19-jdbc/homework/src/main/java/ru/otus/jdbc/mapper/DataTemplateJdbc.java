package ru.otus.jdbc.mapper;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData,
                            EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        var selectByIdSqlQuery = entitySQLMetaData.getSelectByIdSql();
        return dbExecutor.executeSelect(connection, selectByIdSqlQuery, List.of(id), rs -> {
            try {
                if (rs.next()) {
                    return extractObjectFromResultSet(rs);
                }
                return null;
            } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new DataTemplateException(e);
            }
        });
    }

    @Override
    public List<T> findAll(Connection connection) {
        var selectAllSqlQuery = entitySQLMetaData.getSelectAllSql();
        return dbExecutor.executeSelect(connection, selectAllSqlQuery, Collections.emptyList(), rs -> {
            var objectList = new ArrayList<T>();
            try {
                while (rs.next()) {
                    objectList.add(extractObjectFromResultSet(rs));
                }
                return objectList;
            } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new DataTemplateException(e);
            }
        }).orElseThrow(() -> new RuntimeException("Unexpected error"));

    }

    @Override
    public long insert(Connection connection, T entity) {
        var insertSqlQuery = entitySQLMetaData.getInsertSql();
        try {
            var fieldsWithoutIdValues = extractObjectFieldsWithoutIdValues(entity);
            return dbExecutor.executeStatement(connection, insertSqlQuery, fieldsWithoutIdValues);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    @Override
    public void update(Connection connection, T entity) {
        var updateSqlQuery = entitySQLMetaData.getUpdateSql();
        try {
            var idFieldValue = extractObjectFieldValue(entity, entityClassMetaData.getIdField());

            var fieldValues = extractObjectFieldsWithoutIdValues(entity);
            fieldValues.add(idFieldValue);

            dbExecutor.executeStatement(connection, updateSqlQuery, fieldValues);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    private T extractObjectFromResultSet(ResultSet rs)
            throws InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        var newObject = entityClassMetaData.getConstructor().newInstance();
        var allFields = entityClassMetaData.getAllFields();
        for (var field : allFields) {
            var savedFieldAccessible = field.canAccess(newObject);
            field.setAccessible(true);
            field.set(newObject, rs.getObject(field.getName()));
            field.setAccessible(savedFieldAccessible);
        }
        return newObject;
    }



    private List<Object> extractObjectFieldsWithoutIdValues(T entity) throws IllegalAccessException {
        var fields = entityClassMetaData.getFieldsWithoutId();
        var fieldValues = new ArrayList<>();
        for (var field : fields) {
            fieldValues.add(extractObjectFieldValue(entity, field));
        }
        return fieldValues;
    }

    private Object extractObjectFieldValue(T entity, Field field) throws IllegalAccessException {
        var savedFieldAccessible = field.canAccess(entity);
        field.setAccessible(true);
        var fieldValue = field.get(entity);
        field.setAccessible(savedFieldAccessible);
        return fieldValue;
    }
}
