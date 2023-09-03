package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;

/**
 * Создает SQL - запросы
 */
public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {

    private final EntityClassMetaData<T> entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaDataClient) {
        this.entityClassMetaData = entityClassMetaDataClient;
    }

    @Override
    public String getSelectAllSql() {
        var query = "SELECT * FROM %s;";
        var entityName = entityClassMetaData.getName();
        return query.formatted(entityName);
    }

    @Override
    public String getSelectByIdSql() {
        var query = "SELECT * FROM %s WHERE %s = ?;";
        var entityName = entityClassMetaData.getName();
        var idFieldName = entityClassMetaData.getIdField().getName();
        return query.formatted(entityName, idFieldName);
    }

    @Override
    public String getInsertSql() {
        var query = "INSERT INTO %s (%s) VALUES (%s);";
        var entityName = entityClassMetaData.getName();
        var fields = entityClassMetaData.getFieldsWithoutId();
        var fieldNames = String.join(", ", fields.stream().map(Field::getName).toList());
        var fieldValues = String.join(", ", fields.stream().map(field -> "?").toList());
        return query.formatted(entityName, fieldNames, fieldValues);
    }

    @Override
    public String getUpdateSql() {
        var query = "UPDATE %s SET %s WHERE %s = ?;";
        var entityName = entityClassMetaData.getName();
        var idFieldName = entityClassMetaData.getIdField().getName();
        var fieldsWithoutId = entityClassMetaData.getFieldsWithoutId();
        var fieldValuePairs = String.join(", ", fieldsWithoutId.stream()
                .map(field -> "%s = ?".formatted(field.getName()))
                .toList());
        return query.formatted(entityName, fieldValuePairs, idFieldName);
    }
}
