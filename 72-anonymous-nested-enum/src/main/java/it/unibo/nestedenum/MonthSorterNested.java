package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    private enum Month {
        JANUARY(1, 31, "January"),
        FEBRUARY(2, 28, "February"),
        MARCH(3, 31, "March"),
        APRIL(4, 30, "April"),
        MAY(5, 31, "May"),
        JUNE(6, 30, "June"),
        JULY(7, 31, "July"),
        AUGUST(8, 31, "August"),
        SEPTEMBER(9, 30, "September"),
        OCTOBER(10, 31, "October"),
        NOVEMBER(11,30, "November"),
        DECEMBER(12, 31, "December");

        private final int id;
        private final int days;
        private final String actualName;

        private Month(final int id, final int days, final String name) {
            this.id = id;
            this.days = days;
            this.actualName = name;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.actualName;
        }

        public int getDays() {
            return this.days;
        }
        
        public static Month fromString(final String name) {

            Month match = null; 
            int numMatches = 0;

            for (Month month: Month.values()) {
                if (month.getName().toUpperCase().startsWith(name.toUpperCase())) {
                    match = month;
                    numMatches++;
                }
            }

            if (numMatches != 1) {
                String message = "The string " + name;
                message += (numMatches == 0 ? " cannot be resolved to a month" : " is ambiguous");
                throw new IllegalArgumentException(message);
            }

            return match;
        }
    }

    private static class SortByMonthOrder implements Comparator<String> {

        @Override
        public int compare(final String o1, final String o2) {
            final Month m1 = Month.fromString(o1);
            final Month m2 = Month.fromString(o2);

            return m1.getId() - m2.getId();
        }
    }

    private static class SortByDate implements Comparator<String> {

        @Override
        public int compare(final String o1, final String o2) {
            final Month m1 = Month.fromString(o1);
            final Month m2 = Month.fromString(o2);

            return m1.getDays() - m2.getDays();
        }

    }
}
