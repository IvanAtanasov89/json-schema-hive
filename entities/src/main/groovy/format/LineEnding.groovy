package format

class LineEnding {
    static String addEnding(String string, boolean isLast) {
        "${string}${commaOrNoComma(isLast)}"
    }

    private static String commaOrNoComma(boolean isLast) {
        isLast ? "" : ","
    }
}
