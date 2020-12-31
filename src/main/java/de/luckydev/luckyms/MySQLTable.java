package de.luckydev.luckyms;

public class MySQLTable {
    public MySQLDatabase database;
    public MySQLService service;
    public String name;

    public MySQLTable(MySQLDatabase database, MySQLService service, String name) {
        this.database = database;
        this.service = service;
        this.name = name;
    }
}
