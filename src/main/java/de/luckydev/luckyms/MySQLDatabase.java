package de.luckydev.luckyms;

import de.luckydev.luckyms.column.Column;
import de.luckydev.luckyms.column.ColumnAttributes;
import de.luckydev.luckyms.column.PrimaryKey;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLDatabase {
    public String name;
    public MySQLService service;
    public Connection connection;

    public MySQLDatabase(String name, MySQLService service) {
        this.name = name;
        this.service = service;
    }

    public MySQLDatabase connect() throws MySQLException {
        if(isConnected()) return this;
        try {
            connection = DriverManager.getConnection(getURL(), service.username, service.password);
            return this;
        } catch (SQLException exception) {
            throw new MySQLException("Exception occurred while connecting with database on " + getURL() + "!\nSQLException: " + exception.toString());
        }
    }

    public MySQLDatabase disconnect() throws MySQLException {
        if(!isConnected()) return this;
        try {
            connection.close();
            return this;
        } catch (SQLException exception) {
            throw new MySQLException("Exception occurred while disconnecting with database on " + getURL() + "!\nSQLException: " + exception.toString());
        }
    }

    public MySQLDatabase createTableIfNotExists(String name, Class<?> layout) throws MySQLException {
        ArrayList<Column> columns = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE `").append(name).append("` (");
        try {
            for(Field field : layout.getDeclaredFields()) {
                if(field.getType().equals(Column.class)) {
                    if(field.getAnnotation(ColumnAttributes.class) != null) {
                        ColumnAttributes attributes = field.getAnnotation(ColumnAttributes.class);
                        sql.append(field.getName()).append(" ").append(attributes.type().toString());
                    }
                }
            }
            connection.createStatement().execute(sql.toString());
        } catch (SQLException exception) {
            throw new MySQLException("Exception occurred while parsing Class " + layout.getName()  + " to a TableLayout!\nException: " + exception.toString());
        }
        return this;
    }

    public MySQLTable getTable(String name) {
        return  new MySQLTable(this, service, name);
    }

    public boolean isConnected() {
        return connection != null;
    }

    public String getURL() {
        return service.getURL() + name;
    }
}
