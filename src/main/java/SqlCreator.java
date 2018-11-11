public class SqlCreator {

    private String addComma(String sqlPart, boolean isLast) {
        return sqlPart + (isLast ? "" : ",");
    }
}
