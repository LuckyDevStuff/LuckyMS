package de.luckydev.luckyms;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLTable {
    public MySQLDatabase database;
    public MySQLService service;
    public String name;

    public MySQLTable(MySQLDatabase database, MySQLService service, String name) {
        this.database = database;
        this.service = service;
        this.name = name;
    }

    public String sGetWhereIs(String column, String where, String is) throws MySQLException {
        try {
            ResultSet resultSet = database.connection.createStatement().executeQuery("SELECT * FROM `" + name + "` WHERE `" + where + "`='" + is + "'");
            resultSet.next();
            return resultSet.getString(column);
        } catch(SQLException exception) {
            throw new MySQLException("Exception occurred while selecting stuff in table '" + name + "'!\nSQLException: " + exception.toString());
        }
    }

    public Integer iGetWhereIs(String column, String where, String is) throws MySQLException {
        try {
            ResultSet resultSet = database.connection.createStatement().executeQuery("SELECT * FROM `" + name + "` WHERE `" + where + "`='" + is + "'");
            resultSet.next();
            return resultSet.getInt(column);
        } catch(SQLException exception) {
            throw new MySQLException("Exception occurred while selecting stuff in table '" + name + "'!\nSQLException: " + exception.toString());
        }
    }

    public MySQLTable insert(String... values) throws MySQLException {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO `").append(name).append("` VALUES (");
            boolean next = false;
            for(String value : values) { sql.append(next?", '":"'").append(value).append("'"); next = true; }
            sql.append(")");
            System.out.println(sql.toString());
            database.connection.createStatement().execute(sql.toString());
            return this;
        } catch(SQLException exception) {
            throw new MySQLException("Exception occurred while inserting stuff in table '" + name + "'!\nSQLException: " + exception.toString());
        }
    }
}
