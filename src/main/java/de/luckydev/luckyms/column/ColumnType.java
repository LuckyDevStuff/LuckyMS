package de.luckydev.luckyms.column;

public enum ColumnType {
    INT(64), VARCHAR(32);
    public Integer defaultLength;
    ColumnType(Integer defaultLength) {
        this.defaultLength = defaultLength;
    }
}
