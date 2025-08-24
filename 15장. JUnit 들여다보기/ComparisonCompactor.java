package junit.framework;

public class ComparisonCompactor {

    private static final String ELLIPSIS = "...";
    private static final String DELTA_END = "]";
    private static final String DELTA_START = "[";

    private int contextLength;
    private String expected;
    private String actual;
    private int prefix;
    private int suffix;

    public ComparisonCompactor(int contextLength,
                               String expected,
                               String actual) {
        this.contextLength = contextLength;
        this.expected = expected;
        this.actual = actual;
    }

    public String compact(String message) {
        if (shouldNotCompact())
            return Assert.format(message, this.expected, this.actual);

        findCommonPrefix();
        findCommonSuffix();
        String expected = compactString(this.expected);
        String actual = compactString(this.actual);
        return Assert.format(message, expected, actual);
    }

    private boolean shouldNotCompact() {
        return this.expected == null || this.actual == null || areStringsEqual();
    }

    private String compactString(String source) {
        String result = DELTA_START +
                source.substring(this.prefix, source.length() - this.suffix + 1) +
                DELTA_END;

        if (this.prefix > 0)
            result = computeCommonPrefix() + result;
        if (this.suffix > 0)
            result = result + computeCommonSuffix();
        return result;
    }

    private void findCommonPrefix() {
        this.prefix = 0;
        int end = Math.min(this.expected.length(), this.actual.length());
        for (; this.prefix < end; this.prefix++) {
            if (this.expected.charAt(this.prefix) != this.actual.charAt(this.prefix))
                break;
        }
    }

    private void findCommonSuffix() {
        int expectedSuffix = this.expected.length() - 1;
        int actualSuffix = this.actual.length() - 1;
        for (; actualSuffix >= this.prefix && expectedSuffix >= this.prefix;
             actualSuffix--, expectedSuffix--) {
            if (this.expected.charAt(expectedSuffix) != this.actual.charAt(actualSuffix))
                break;
        }
        this.suffix = this.expected.length() - expectedSuffix;
    }

    private String computeCommonPrefix() {
        return (this.prefix > this.contextLength ? ELLIPSIS : "") +
                this.expected.substring(Math.max(0, this.prefix - this.contextLength), this.prefix);
    }

    private String computeCommonSuffix() {
        int end = Math.min(this.expected.length() - this.suffix + 1 + this.contextLength,
                this.expected.length());
        return this.expected.substring(this.expected.length() - this.suffix + 1, end) +
                (this.expected.length() - this.suffix + 1 < this.expected.length() - this.contextLength ? ELLIPSIS : "");
    }

    private boolean areStringsEqual() {
        return this.expected.equals(this.actual);
    }
}
