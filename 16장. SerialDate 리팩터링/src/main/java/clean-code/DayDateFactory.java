package org.jfree.date;

public abstract class DayDateFactory {
    private static DayDateFactory factory = new SpreadsheetDateFactory();
    public static void setInstance(DayDateFactory factory) {
        DayDateFactory.factory = factory;
    }

    protected abstract DayDate _makeDate(int ordinal);
    protected abstract DayDate _makeDate(int day, Month month, int year);
    protected abstract DayDate _makeDate(int day, int month, int year);
    protected abstract DayDate _makeDate(java.util.Date date);
    protected abstract int _getMinumumYear();
    protected abstract int _getMaximumYear();

    // createInstance -> makeDate 로 변경 
    public static DayDate makeDate(int ordinal) {
        return factory._makeDate(ordinal);
    }

    public static DayDate makeDate(int day, Month month, int year)
    {
        return factory._makeDate(day, month, year);
    }

    public static DayDate makeDate(int day, int month, int year)
    {
        return factory._makeDate(day, month, year);
    }

    public static DayDate makeDate(java.util.Date date)
    {
        return factory._makeDate(date);
    }

    public static int getMinumumYear()
    {
        return factory._getMinumumYear();
    }

    public static int getMaximumYear()
    {
        return factory._getMaximumYear();
    }
}