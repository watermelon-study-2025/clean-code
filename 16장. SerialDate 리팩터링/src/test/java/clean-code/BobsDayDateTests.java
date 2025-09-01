package daehun.study.cleancode.ch16.jfree.date;

import junit.framework.TestCase;
import daehun.study.cleancode.ch16.jfree.date.*;
import static daehun.study.cleancode.ch16.jfree.date.DayDate.*;

import java.util.*;

import org.jfree.date.*;

public class BobsDayDateTests extends TestCase {

    public void teststringToWeekday() throws Exception {

        try {
            stringToWeekday("Hello");
            fail("Invalid day index string should throw exception");
        } catch (Exception ignore) {
        }

        assertEquals(Day.MONDAY, stringToWeekday("Monday"));
        assertEquals(Day.MONDAY, stringToWeekday("Mon"));
        assertEquals(Day.MONDAY,stringToWeekday("monday")); // step01 : 대소문자 구분 없이 모두 통과해야함
        assertEquals(Day.MONDAY,stringToWeekday("MONDAY"));
        assertEquals(Day.MONDAY, stringToWeekday("mon"));

        assertEquals(Day.TUESDAY, stringToWeekday("Tuesday"));
        assertEquals(Day.TUESDAY, stringToWeekday("Tue"));
        assertEquals(Day.TUESDAY,stringToWeekday("tuesday"));
        assertEquals(Day.TUESDAY,stringToWeekday("TUESDAY"));
        assertEquals(Day.TUESDAY, stringToWeekday("tue"));
        // assertEquals(Day.TUESDAY, stringToWeekday("tues"));

        assertEquals(Day.WEDNESDAY, stringToWeekday("Wednesday"));
        assertEquals(Day.WEDNESDAY, stringToWeekday("Wed"));
        assertEquals(Day.WEDNESDAY,stringToWeekday("wednesday"));
        assertEquals(Day.WEDNESDAY,stringToWeekday("WEDNESDAY"));
        assertEquals(Day.WEDNESDAY, stringToWeekday("wed"));

        assertEquals(Day.THURSDAY, stringToWeekday("Thursday"));
        assertEquals(Day.THURSDAY, stringToWeekday("Thu"));
        assertEquals(Day.THURSDAY,stringToWeekday("thursday"));
        assertEquals(Day.THURSDAY,stringToWeekday("THURSDAY"));
        assertEquals(Day.THURSDAY, stringToWeekday("thu"));
        // assertEquals(Day.THURSDAY, stringToWeekday("thurs"));

        assertEquals(Day.FRIDAY, stringToWeekday("Friday"));
        assertEquals(Day.FRIDAY, stringToWeekday("Fri"));
        assertEquals(Day.FRIDAY,stringToWeekday("friday"));
        assertEquals(Day.FRIDAY,stringToWeekday("FRIDAY"));
        assertEquals(Day.FRIDAY, stringToWeekday("fri"));

        assertEquals(Day.SATURDAY, stringToWeekday("Saturday"));
        assertEquals(Day.SATURDAY, stringToWeekday("Sat"));
        assertEquals(Day.SATURDAY,stringToWeekday("saturday"));
        assertEquals(Day.SATURDAY,stringToWeekday("SATURDAY"));
        assertEquals(Day.SATURDAY, stringToWeekday("sat"));

        assertEquals(Day.SUNDAY, stringToWeekday("Sunday"));
        assertEquals(Day.SUNDAY, stringToWeekday("Sun"));
        assertEquals(Day.SUNDAY,stringToWeekday("sunday"));
        assertEquals(Day.SUNDAY,stringToWeekday("SUNDAY"));
        assertEquals(Day.SUNDAY, stringToWeekday("sun"));
    }

    public void testweekdayToString() throws Exception {
        assertEquals("Sunday", weekdayToString(Day.SUNDAY));
        assertEquals("Monday", weekdayToString(Day.MONDAY));
        assertEquals("Tuesday", weekdayToString(Day.TUESDAY));
        assertEquals("Wednesday", weekdayToString(Day.WEDNESDAY));
        assertEquals("Thursday", weekdayToString(Day.THURSDAY));
        assertEquals("Friday", weekdayToString(Day.FRIDAY));
        assertEquals("Saturday", weekdayToString(Day.SATURDAY));
    }

    public void testmonthToString() throws Exception {
        assertEquals("Month.JANUARY", monthToString(Month.JANUARY));
        assertEquals("Month.FEBRUARY", monthToString(Month.FEBRUARY));
        assertEquals("Month.MARCH", monthToString(Month.MARCH));
        assertEquals("Month.APRIL", monthToString(Month.APRIL));
        assertEquals("May", monthToString(Month.MAY));
        assertEquals("June", monthToString(Month.JUNE));
        assertEquals("July", monthToString(Month.JULY));
        assertEquals("August", monthToString(Month.AUGUST));
        assertEquals("September", monthToString(Month.SEPTEMBER));
        assertEquals("October", monthToString(Month.OCTOBER));
        assertEquals("November", monthToString(Month.NOVEMBER));
        assertEquals("December", monthToString(Month.DECEMBER));

        assertEquals("Jan", monthToString(Month.JANUARY, true));
        assertEquals("Feb", monthToString(Month.FEBRUARY, true));
        assertEquals("Mar", monthToString(Month.MARCH, true));
        assertEquals("Apr", monthToString(Month.APRIL, true));
        assertEquals("May", monthToString(Month.MAY, true));
        assertEquals("Jun", monthToString(Month.JUNE, true));
        assertEquals("Jul", monthToString(Month.JULY, true));
        assertEquals("Aug", monthToString(Month.AUGUST, true));
        assertEquals("Sep", monthToString(Month.SEPTEMBER, true));
        assertEquals("Oct", monthToString(Month.OCTOBER, true));
        assertEquals("Nov", monthToString(Month.NOVEMBER, true));
        assertEquals("Dec", monthToString(Month.DECEMBER, true));

        try {
            monthToString(-1);
            fail("Invalid month code should throw exception");
        } catch (IllegalArgumentException e) {
        }

    }

    public void testStringToMonthCode() throws Exception {
        assertEquals(Month.JANUARY, stringToMonthCode("1"));
        assertEquals(Month.FEBRUARY, stringToMonthCode("2"));
        assertEquals(Month.MARCH, stringToMonthCode("3"));
        assertEquals(Month.APRIL, stringToMonthCode("4"));
        assertEquals(Month.MAY, stringToMonthCode("5"));
        assertEquals(Month.JUNE, stringToMonthCode("6"));
        assertEquals(Month.JULY, stringToMonthCode("7"));
        assertEquals(Month.AUGUST, stringToMonthCode("8"));
        assertEquals(Month.SEPTEMBER, stringToMonthCode("9"));
        assertEquals(Month.OCTOBER, stringToMonthCode("10"));
        assertEquals(Month.NOVEMBER, stringToMonthCode("11"));
        assertEquals(Month.DECEMBER, stringToMonthCode("12"));

        // step10 : MonthContants -> enum Month
        try {
            stringToMonthCode("0");
            fail("Invalid month index should throw exception");
        } catch (IllegalArgumentException e) {
        }

        try {
            stringToMonthCode("13");
            fail("Invalid month index should throw exception");
        } catch (IllegalArgumentException e) {
        }

        try {
            stringToMonthCode("Hello");
            fail("Invalid month index should throw exception");
        } catch (IllegalArgumentException e) {
        }

        for (Month m : Month.values()) {
            assertEquals(m, stringToMonthCode(monthToString(m, false)));
            assertEquals(m, stringToMonthCode(monthToString(m, true)));
        }

        assertEquals(Month.JANUARY,stringToMonthCode("jan"));   // step02 : 대소문자 구분 없이 모두 통과해야함
        assertEquals(Month.FEBRUARY,stringToMonthCode("feb"));
        assertEquals(Month.MARCH,stringToMonthCode("mar"));
        assertEquals(Month.APRIL,stringToMonthCode("apr"));
        assertEquals(Month.MAY,stringToMonthCode("may"));
        assertEquals(Month.JUNE,stringToMonthCode("jun"));
        assertEquals(Month.JULY,stringToMonthCode("jul"));
        assertEquals(Month.AUGUST,stringToMonthCode("aug"));
        assertEquals(Month.SEPTEMBER,stringToMonthCode("sep"));
        assertEquals(Month.OCTOBER,stringToMonthCode("oct"));
        assertEquals(Month.NOVEMBER,stringToMonthCode("nov"));
        assertEquals(Month.DECEMBER,stringToMonthCode("dec"));

        assertEquals(Month.JANUARY,stringToMonthCode("JAN"));
        assertEquals(Month.FEBRUARY,stringToMonthCode("FEB"));
        assertEquals(Month.MARCH,stringToMonthCode("MAR"));
        assertEquals(Month.APRIL,stringToMonthCode("APR"));
        assertEquals(Month.MAY,stringToMonthCode("Month.MAY"));
        assertEquals(Month.JUNE,stringToMonthCode("JUN"));
        assertEquals(Month.JULY,stringToMonthCode("JUL"));
        assertEquals(Month.AUGUST,stringToMonthCode("AUG"));
        assertEquals(Month.SEPTEMBER,stringToMonthCode("SEP"));
        assertEquals(Month.OCTOBER,stringToMonthCode("OCT"));
        assertEquals(Month.NOVEMBER,stringToMonthCode("NOV"));
        assertEquals(Month.DECEMBER,stringToMonthCode("DEC"));

        assertEquals(Month.JANUARY,stringToMonthCode("Month.JANUARY"));
        assertEquals(Month.FEBRUARY,stringToMonthCode("Month.FEBRUARY"));
        assertEquals(Month.MARCH,stringToMonthCode("Month.MARCH"));
        assertEquals(Month.APRIL,stringToMonthCode("Month.APRIL"));
        assertEquals(Month.MAY,stringToMonthCode("may"));
        assertEquals(Month.JUNE,stringToMonthCode("june"));
        assertEquals(Month.JULY,stringToMonthCode("july"));
        assertEquals(Month.AUGUST,stringToMonthCode("august"));
        assertEquals(Month.SEPTEMBER,stringToMonthCode("september"));
        assertEquals(Month.OCTOBER,stringToMonthCode("october"));
        assertEquals(Month.NOVEMBER,stringToMonthCode("november"));
        assertEquals(Month.DECEMBER,stringToMonthCode("december"));

        assertEquals(Month.JANUARY,stringToMonthCode("Month.JANUARY"));
        assertEquals(Month.FEBRUARY,stringToMonthCode("Month.FEBRUARY"));
        assertEquals(Month.MARCH,stringToMonthCode("MAR"));
        assertEquals(Month.APRIL,stringToMonthCode("Month.APRIL"));
        assertEquals(Month.MAY,stringToMonthCode("Month.MAY"));
        assertEquals(Month.JUNE,stringToMonthCode("Month.JUNE"));
        assertEquals(Month.JULY,stringToMonthCode("Month.JULY"));
        assertEquals(Month.AUGUST,stringToMonthCode("Month.AUGUST"));
        assertEquals(Month.SEPTEMBER,stringToMonthCode("Month.SEPTEMBER"));
        assertEquals(Month.OCTOBER,stringToMonthCode("Month.OCTOBER"));
        assertEquals(Month.NOVEMBER,stringToMonthCode("Month.NOVEMBER"));
        assertEquals(Month.DECEMBER,stringToMonthCode("Month.DECEMBER"));
    }

    public void testIsLeapYear() throws Exception {
        assertFalse(isLeapYear(1900));
        assertFalse(isLeapYear(1901));
        assertFalse(isLeapYear(1902));
        assertFalse(isLeapYear(1903));
        assertTrue(isLeapYear(1904));
        assertTrue(isLeapYear(1908));
        assertFalse(isLeapYear(1955));
        assertTrue(isLeapYear(1964));
        assertTrue(isLeapYear(1980));
        assertTrue(isLeapYear(2000));
        assertFalse(isLeapYear(2001));
        assertFalse(isLeapYear(2100));
    }

    public void testLeapYearCount() throws Exception {
        assertEquals(0, leapYearCount(1900));
        assertEquals(0, leapYearCount(1901));
        assertEquals(0, leapYearCount(1902));
        assertEquals(0, leapYearCount(1903));
        assertEquals(1, leapYearCount(1904));
        assertEquals(1, leapYearCount(1905));
        assertEquals(1, leapYearCount(1906));
        assertEquals(1, leapYearCount(1907));
        assertEquals(2, leapYearCount(1908));
        assertEquals(24, leapYearCount(1999));
        assertEquals(25, leapYearCount(2001));
        assertEquals(49, leapYearCount(2101));
        assertEquals(73, leapYearCount(2201));
        assertEquals(97, leapYearCount(2301));
        assertEquals(122, leapYearCount(2401));
    }

    public void testLastDayOfMonth() throws Exception {
        assertEquals(31, lastDayOfMonth(Month.JANUARY, 1901));
        assertEquals(28, lastDayOfMonth(Month.FEBRUARY, 1901));
        assertEquals(31, lastDayOfMonth(Month.MARCH, 1901));
        assertEquals(30, lastDayOfMonth(Month.APRIL, 1901));
        assertEquals(31, lastDayOfMonth(Month.MAY, 1901));
        assertEquals(30, lastDayOfMonth(Month.JUNE, 1901));
        assertEquals(31, lastDayOfMonth(Month.JULY, 1901));
        assertEquals(31, lastDayOfMonth(Month.AUGUST, 1901));
        assertEquals(30, lastDayOfMonth(Month.SEPTEMBER, 1901));
        assertEquals(31, lastDayOfMonth(Month.OCTOBER, 1901));
        assertEquals(30, lastDayOfMonth(Month.NOVEMBER, 1901));
        assertEquals(31, lastDayOfMonth(Month.DECEMBER, 1901));
        assertEquals(29, lastDayOfMonth(Month.FEBRUARY, 1904));
    }

    public void testAddDays() throws Exception {
        DayDate newYears = d(1, Month.JANUARY, 1900);
        assertEquals(d(2, Month.JANUARY, 1900), addDays(1, newYears));
        assertEquals(d(1, Month.FEBRUARY, 1900), addDays(31, newYears));
        assertEquals(d(1, Month.JANUARY, 1901), addDays(365, newYears));
        assertEquals(d(31, Month.DECEMBER, 1904), addDays(5 * 365, newYears));
    }

    private static SpreadsheetDate d(int day, int month, int year) { return new SpreadsheetDate(day, month, year); }

    public void testAddMonths() throws Exception {
        assertEquals(d(1, Month.FEBRUARY, 1900), addMonths(1, d(1, Month.JANUARY, 1900)));
        assertEquals(d(28, Month.FEBRUARY, 1900), addMonths(1, d(31, Month.JANUARY, 1900)));
        assertEquals(d(28, Month.FEBRUARY, 1900), addMonths(1, d(30, Month.JANUARY, 1900)));
        assertEquals(d(28, Month.FEBRUARY, 1900), addMonths(1, d(29, Month.JANUARY, 1900)));
        assertEquals(d(28, Month.FEBRUARY, 1900), addMonths(1, d(28, Month.JANUARY, 1900)));
        assertEquals(d(27, Month.FEBRUARY, 1900), addMonths(1, d(27, Month.JANUARY, 1900)));

        assertEquals(d(30, Month.JUNE, 1900), addMonths(5, d(31, Month.JANUARY, 1900)));
        assertEquals(d(30, Month.JUNE, 1901), addMonths(17, d(31, Month.JANUARY, 1900)));

        assertEquals(d(29, Month.FEBRUARY, 1904), addMonths(49, d(31, Month.JANUARY, 1900)));

    }

    public void testAddYears() throws Exception {
        assertEquals(d(1, Month.JANUARY, 1901), addYears(1, d(1, Month.JANUARY, 1900)));
        assertEquals(d(28, Month.FEBRUARY, 1905), addYears(1, d(29, Month.FEBRUARY, 1904)));
        assertEquals(d(28, Month.FEBRUARY, 1905), addYears(1, d(28, Month.FEBRUARY, 1904)));
        assertEquals(d(28, Month.FEBRUARY, 1904), addYears(1, d(28, Month.FEBRUARY, 1903)));
    }

    public void testGetPreviousDayOfWeek() throws Exception {
        assertEquals(d(24, Month.FEBRUARY, 2006), getPreviousDayOfWeek(Day.FRIDAY, d(1, Month.MARCH, 2006)));
        assertEquals(d(22, Month.FEBRUARY, 2006), getPreviousDayOfWeek(Day.WEDNESDAY, d(1, Month.MARCH, 2006)));
        assertEquals(d(29, Month.FEBRUARY, 2004), getPreviousDayOfWeek(Day.SUNDAY, d(3, Month.MARCH, 2004)));
        assertEquals(d(29, Month.DECEMBER, 2004), getPreviousDayOfWeek(Day.WEDNESDAY, d(5, Month.JANUARY, 2005)));
    }

    public void testGetFollowingDayOfWeek() throws Exception {
        assertEquals(d(1, Month.JANUARY, 2005),getFollowingDayOfWeek(SATURDAY, d(25, Month.DECEMBER, 2004)));   // step03 : 버그 수정 (연말 -> 연초 넘어갈 때)
        assertEquals(d(1, Month.JANUARY, 2005), getFollowingDayOfWeek(SATURDAY, d(26, Month.DECEMBER, 2004)));
        assertEquals(d(3, Month.MARCH, 2004), getFollowingDayOfWeek(WEDNESDAY, d(28, Month.FEBRUARY, 2004)));
    }

    public void testGetNearestDayOfWeek() throws Exception {
        assertEquals(d(16, Month.APRIL, 2006), getNearestDayOfWeek(Day.SUNDAY, d(16, Month.APRIL, 2006)));
        assertEquals(d(16, Month.APRIL, 2006), getNearestDayOfWeek(Day.SUNDAY, d(17, Month.APRIL, 2006)));
        assertEquals(d(16, Month.APRIL, 2006), getNearestDayOfWeek(Day.SUNDAY, d(18, Month.APRIL, 2006)));
        assertEquals(d(16, Month.APRIL, 2006), getNearestDayOfWeek(Day.SUNDAY, d(19, Month.APRIL, 2006)));
        assertEquals(d(23, Month.APRIL, 2006), getNearestDayOfWeek(Day.SUNDAY, d(20, Month.APRIL, 2006)));
        assertEquals(d(23, Month.APRIL, 2006), getNearestDayOfWeek(Day.SUNDAY, d(21, Month.APRIL, 2006)));
        assertEquals(d(23, Month.APRIL, 2006), getNearestDayOfWeek(Day.SUNDAY, d(22, Month.APRIL, 2006)));

        assertEquals(d(17, Month.APRIL, 2006), getNearestDayOfWeek(Day.MONDAY, d(16, Month.APRIL, 2006)));  // step04 : 버그 수정 (과거 날짜만 나오는 문제)
        assertEquals(d(17, Month.APRIL, 2006), getNearestDayOfWeek(Day.MONDAY, d(17, Month.APRIL, 2006)));
        assertEquals(d(17, Month.APRIL, 2006), getNearestDayOfWeek(Day.MONDAY, d(18, Month.APRIL, 2006)));
        assertEquals(d(17, Month.APRIL, 2006), getNearestDayOfWeek(Day.MONDAY, d(19, Month.APRIL, 2006)));
        assertEquals(d(17, Month.APRIL, 2006), getNearestDayOfWeek(Day.MONDAY, d(20, Month.APRIL, 2006)));
        assertEquals(d(24, Month.APRIL, 2006), getNearestDayOfWeek(Day.MONDAY, d(21, Month.APRIL, 2006)));
        assertEquals(d(24, Month.APRIL, 2006), getNearestDayOfWeek(Day.MONDAY, d(22, Month.APRIL, 2006)));

        assertEquals(d(18, Month.APRIL, 2006), getNearestDayOfWeek(Day.TUESDAY, d(16, Month.APRIL, 2006)));
        assertEquals(d(18, Month.APRIL, 2006), getNearestDayOfWeek(Day.TUESDAY, d(17, Month.APRIL, 2006)));
        assertEquals(d(18, Month.APRIL, 2006), getNearestDayOfWeek(Day.TUESDAY, d(18, Month.APRIL, 2006)));
        assertEquals(d(18, Month.APRIL, 2006), getNearestDayOfWeek(Day.TUESDAY, d(19, Month.APRIL, 2006)));
        assertEquals(d(18, Month.APRIL, 2006), getNearestDayOfWeek(Day.TUESDAY, d(20, Month.APRIL, 2006)));
        assertEquals(d(18, Month.APRIL, 2006), getNearestDayOfWeek(Day.TUESDAY, d(21, Month.APRIL, 2006)));
        assertEquals(d(25, Month.APRIL, 2006), getNearestDayOfWeek(Day.TUESDAY, d(22, Month.APRIL, 2006)));

        assertEquals(d(19, Month.APRIL, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(16, Month.APRIL, 2006)));
        assertEquals(d(19, Month.APRIL, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(17, Month.APRIL, 2006)));
        assertEquals(d(19, Month.APRIL, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(18, Month.APRIL, 2006)));
        assertEquals(d(19, Month.APRIL, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(19, Month.APRIL, 2006)));
        assertEquals(d(19, Month.APRIL, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(20, Month.APRIL, 2006)));
        assertEquals(d(19, Month.APRIL, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(21, Month.APRIL, 2006)));
        assertEquals(d(19, Month.APRIL, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(22, Month.APRIL, 2006)));

        assertEquals(d(13, Month.APRIL, 2006), getNearestDayOfWeek(Day.THURSDAY, d(16, Month.APRIL, 2006)));
        assertEquals(d(20, Month.APRIL, 2006), getNearestDayOfWeek(Day.THURSDAY, d(17, Month.APRIL, 2006)));
        assertEquals(d(20, Month.APRIL, 2006), getNearestDayOfWeek(Day.THURSDAY, d(18, Month.APRIL, 2006)));
        assertEquals(d(20, Month.APRIL, 2006), getNearestDayOfWeek(Day.THURSDAY, d(19, Month.APRIL, 2006)));
        assertEquals(d(20, Month.APRIL, 2006), getNearestDayOfWeek(Day.THURSDAY, d(20, Month.APRIL, 2006)));
        assertEquals(d(20, Month.APRIL, 2006), getNearestDayOfWeek(Day.THURSDAY, d(21, Month.APRIL, 2006)));
        assertEquals(d(20, Month.APRIL, 2006), getNearestDayOfWeek(Day.THURSDAY, d(22, Month.APRIL, 2006)));

        assertEquals(d(14, Month.APRIL, 2006), getNearestDayOfWeek(Day.FRIDAY, d(16, Month.APRIL, 2006)));
        assertEquals(d(14, Month.APRIL, 2006), getNearestDayOfWeek(Day.FRIDAY, d(17, Month.APRIL, 2006)));
        assertEquals(d(21, Month.APRIL, 2006), getNearestDayOfWeek(Day.FRIDAY, d(18, Month.APRIL, 2006)));
        assertEquals(d(21, Month.APRIL, 2006), getNearestDayOfWeek(Day.FRIDAY, d(19, Month.APRIL, 2006)));
        assertEquals(d(21, Month.APRIL, 2006), getNearestDayOfWeek(Day.FRIDAY, d(20, Month.APRIL, 2006)));
        assertEquals(d(21, Month.APRIL, 2006), getNearestDayOfWeek(Day.FRIDAY, d(21, Month.APRIL, 2006)));
        assertEquals(d(21, Month.APRIL, 2006), getNearestDayOfWeek(Day.FRIDAY, d(22, Month.APRIL, 2006)));

        assertEquals(d(15, Month.APRIL, 2006), getNearestDayOfWeek(Day.SATURDAY, d(16, Month.APRIL, 2006)));
        assertEquals(d(15, Month.APRIL, 2006), getNearestDayOfWeek(Day.SATURDAY, d(17, Month.APRIL, 2006)));
        assertEquals(d(15, Month.APRIL, 2006), getNearestDayOfWeek(Day.SATURDAY, d(18, Month.APRIL, 2006)));
        assertEquals(d(22, Month.APRIL, 2006), getNearestDayOfWeek(Day.SATURDAY, d(19, Month.APRIL, 2006)));
        assertEquals(d(22, Month.APRIL, 2006), getNearestDayOfWeek(Day.SATURDAY, d(20, Month.APRIL, 2006)));
        assertEquals(d(22, Month.APRIL, 2006), getNearestDayOfWeek(Day.SATURDAY, d(21, Month.APRIL, 2006)));
        assertEquals(d(22, Month.APRIL, 2006), getNearestDayOfWeek(Day.SATURDAY, d(22, Month.APRIL, 2006)));
    }

    public void testEndOfCurrentMonth() throws Exception {
        DayDate d = DayDateFactory.makeDate(2);
        assertEquals(d(31, Month.JANUARY, 2006), d.getEndOfCurrentMonth(d(1, Month.JANUARY, 2006)));
        assertEquals(d(28, Month.FEBRUARY, 2006), d.getEndOfCurrentMonth(d(1, Month.FEBRUARY, 2006)));
        assertEquals(d(31, Month.MARCH, 2006), d.getEndOfCurrentMonth(d(1, Month.MARCH, 2006)));
        assertEquals(d(30, Month.APRIL, 2006), d.getEndOfCurrentMonth(d(1, Month.APRIL, 2006)));
        assertEquals(d(31, Month.MAY, 2006), d.getEndOfCurrentMonth(d(1, Month.MAY, 2006)));
        assertEquals(d(30, Month.JUNE, 2006), d.getEndOfCurrentMonth(d(1, Month.JUNE, 2006)));
        assertEquals(d(31, Month.JULY, 2006), d.getEndOfCurrentMonth(d(1, Month.JULY, 2006)));
        assertEquals(d(31, Month.AUGUST, 2006), d.getEndOfCurrentMonth(d(1, Month.AUGUST, 2006)));
        assertEquals(d(30, Month.SEPTEMBER, 2006), d.getEndOfCurrentMonth(d(1, Month.SEPTEMBER, 2006)));
        assertEquals(d(31, Month.OCTOBER, 2006), d.getEndOfCurrentMonth(d(1, Month.OCTOBER, 2006)));
        assertEquals(d(30, Month.NOVEMBER, 2006), d.getEndOfCurrentMonth(d(1, Month.NOVEMBER, 2006)));
        assertEquals(d(31, Month.DECEMBER, 2006), d.getEndOfCurrentMonth(d(1, Month.DECEMBER, 2006)));
        assertEquals(d(29, Month.FEBRUARY, 2008), d.getEndOfCurrentMonth(d(1, Month.FEBRUARY, 2008)));
    }

    public void testWeekInMonthToString() throws Exception {
        assertEquals("First", weekInMonthToString(WeekInMonth.FIRST));
        assertEquals("Second", weekInMonthToString(WeekInMonth.SECOND));
        assertEquals("Third", weekInMonthToString(WeekInMonth.THIRD));
        assertEquals("Fourth", weekInMonthToString(WeekInMonth.FOURTH));
        assertEquals("Last", weekInMonthToString(WeekInMonth.LAST));
    }

    public void testRelativeToString() throws Exception {
        assertEquals("Preceding", relativeToString(PRECEDING));
        assertEquals("Nearest", relativeToString(NEAREST));
        assertEquals("Following", relativeToString(FOLLOWING));

        try {
            relativeToString(-1000);
            fail("Invalid relative code should throw exception");
        } catch (IllegalArgumentException e) {  // step05 : 오류 문자열 반환 대신 예외 발생 처리
        }
    }

    public void testMakeDateFromDDMMYYY() throws Exception {
        DayDate date = DayDateFactory.makeDate(1, Month.JANUARY, 1900);
        assertEquals(1, date.getDayOfMonth());
        assertEquals(Month.JANUARY, date.getMonth());
        assertEquals(1900, date.getYYYY());
        assertEquals(2, date.toSerial());
    }

    public void testMakeDateFromSerial() throws Exception {
        assertEquals(d(1, Month.JANUARY, 1900), DayDateFactory.makeDate(2));
        assertEquals(d(1, Month.JANUARY, 1901), DayDateFactory.makeDate(367));
    }

    public void testMakeDateFromJavaDate() throws Exception {
        assertEquals(d(1, Month.JANUARY, 1900), DayDateFactory.makeDate(new GregorianCalendar(1900, 0, 1).getTime()));
        assertEquals(d(1, Month.JANUARY, 2006), DayDateFactory.makeDate(new GregorianCalendar(2006, 0, 1).getTime()));
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(BobsDayDateTests.class);
    }
}