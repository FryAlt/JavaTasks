import java.util.Calendar;

class DateUtil {
    private int day;
    private int month;
    private int year;

    public DateUtil(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public boolean isLeapYear() {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public String toTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return String.valueOf(calendar.getTimeInMillis());
    }

    public String formatDate(String delimiter) {
        return day + delimiter + month + delimiter + year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}

class TimeDate extends DateUtil {
    private int hour;
    private int minute;
    private int second;

    public TimeDate(int day, int month, int year, int hour, int minute, int second) {
        super(day, month, year);
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public void setTime(int hour, int minute, int second) {
        if (hour >= 0 && hour < 24) this.hour = hour;
        if (minute >= 0 && minute < 60) this.minute = minute;
        if (second >= 0 && second < 60) this.second = second;
    }

    public String toTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getYear(), getMonth() - 1, getDay(), hour, minute, second);
        return String.valueOf(calendar.getTimeInMillis());
    }

    public String formatTime(String delimiter) {
        return hour + delimiter + minute + delimiter + second;
    }

    @Override
    public String formatDate(String delimiter) {
        return super.formatDate(delimiter) + " " + formatTime(":");
    }

    public static void main(String[] args) {
        TimeDate timeDate = new TimeDate(14, 3, 2025, 14, 30, 45);
        System.out.println("Это високосный год: " + timeDate.isLeapYear());
        System.out.println("Отметка времени: " + timeDate.toTimestamp());
        System.out.println("Отформатированная дата: " + timeDate.formatDate("/"));
        System.out.println("Отформатированное время: " + timeDate.formatTime(":"));
        
        timeDate.setTime(15, 45, 50);
        System.out.println("Обновленные отформатированные дата и время: " + timeDate.formatDate("-"));
    }
}