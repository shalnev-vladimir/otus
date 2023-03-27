package core;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.*;

import static java.lang.System.*;

public class ComparableClass {
    public static void main(String[] args) {
        List<MonthDate> monthDates = new ArrayList<>();
        MonthDate monthDate1 = new MonthDate(11, 11);
        MonthDate monthDate2 = new MonthDate(1, 25);
        MonthDate monthDate3 = new MonthDate(5, 13);
        MonthDate monthDate4 = new MonthDate(5, 14);

        monthDates.add(monthDate1);
        monthDates.add(monthDate2);
        monthDates.add(monthDate3);
        monthDates.add(monthDate4);

        Collections.sort(monthDates);
        out.println(monthDates);

        out.println("########### After Comparing ###########");

        monthDates.sort((o1, o2) -> new CompareToBuilder()
                .append(o1.getMonth(), o2.getMonth())
                .append(o1.getDay(), o2.getDay())
                .toComparison());

        out.println(monthDates);
    }

    static class MonthDate implements Comparable<MonthDate> {

        private int month;
        private int day;

        public MonthDate(int month, int day) {
            this.month = month;
            this.day = day;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        @Override
        public int compareTo(MonthDate o) {
            if (o == null) {
                return -1;
            }
            CompareToBuilder builder = new CompareToBuilder();
            return builder.append(this.month, o.month).append(this.day, o.day).toComparison();
        }

        @Override
        public String toString() {
            return "MonthDate{" +
                    "month=" + month +
                    ", day=" + day +
                    '}';
        }
    }
}
