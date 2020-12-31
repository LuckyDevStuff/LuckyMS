import de.luckydev.luckyms.column.Column;
import de.luckydev.luckyms.column.ColumnAttributes;
import de.luckydev.luckyms.column.ColumnType;

public class TestTableLayout {
    @ColumnAttributes(type = ColumnType.VARCHAR, value = 64) Column uuid;
    @ColumnAttributes(type = ColumnType.INT) Column id;
}
