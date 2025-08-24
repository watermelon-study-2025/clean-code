package junit.framework;

public class ComparisonCompactor {
    private static final String ELLIPSIS = "...";
    private static final String DELTA_END = "]";
    private static final String DELTA_START = "[";

    private int contextLength;
    private String expected;
    private String actual;
    private int prefixIndex;
    private int suffixIndex;
    private String compactExpected;
    private String compactActual;

    public ComparisonCompactor(int contextLength,
                               String expected,
                               String actual) {
        this.contextLength = contextLength;
        this.expected = expected;
        this.actual = actual;
    }

    public String formatCompactedComparison(String message) {
        if (canBeCompacted()) {
            compactExpectedAndActual();
            return Assert.format(message, compactExpected, compactActual);
        } else {
            return Assert.format(message, expected, actual);
        }
    }

    private void compactExpectedAndActual() {
        prefixIndex = findCommonPrefix();
        suffixIndex = findCommonSuffix(prefixIndex);
        compactExpected = compactString(expected);
        compactActual = compactString(actual);
    }

    private boolean canBeCompacted() {
        return expected != null && actual != null && !areStringsEqual();
    }

    private String compactString(String source) {
        String result = DELTA_START +
                source.substring(prefixIndex, source.length() - suffixIndex + 1) +
                DELTA_END;

        if (prefixIndex > 0)
            result = computeCommonPrefix() + result;
        if (suffixIndex > 0)
            result = result + computeCommonSuffix();
        return result;
    }

    private int findCommonPrefix() {
        int prefixIndex = 0;
        int end = Math.min(this.expected.length(), this.actual.length());
        for (; prefixIndex < end; prefixIndex++) {
            if (this.expected.charAt(prefixIndex) != this.actual.charAt(prefixIndex))
                break;
        }
        return prefixIndex;
    }

    private int findCommonSuffix(int prefixIndex) {
        int expectedSuffix = this.expected.length() - 1;
        int actualSuffix = this.actual.length() - 1;
        for (; actualSuffix >= prefixIndex && expectedSuffix >= prefixIndex;
             actualSuffix--, expectedSuffix--) {
            if (this.expected.charAt(expectedSuffix) != this.actual.charAt(actualSuffix))
                break;
        }
        return expected.length() - expectedSuffix;
    }

    private String computeCommonPrefix() {
        return (prefixIndex > this.contextLength ? ELLIPSIS : "") +
                this.expected.substring(Math.max(0, prefixIndex - this.contextLength), prefixIndex);
    }

    private String computeCommonSuffix() {
        int end = Math.min(this.expected.length() - suffixIndex + 1 + this.contextLength,
                this.expected.length());
        return this.expected.substring(this.expected.length() - suffixIndex + 1, end) +
                (this.expected.length() - suffixIndex + 1 < this.expected.length() - this.contextLength ? ELLIPSIS : "");
    }

    private boolean areStringsEqual() {
        return this.expected.equals(this.actual);
    }
}
