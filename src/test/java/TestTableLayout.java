import de.luckydev.luckyms.column.*;

public class TestTableLayout {
    @PrimaryKey @ColumnAttributes(type = ColumnType.VARCHAR, length = 32) Column uuid;
    @NotNull @ColumnAttributes(type = ColumnType.INT) Column id;
    @NotNull @Comment("This is a Comment") @ColumnAttributes(type = ColumnType.VARCHAR, length = 496) Column test;
}
