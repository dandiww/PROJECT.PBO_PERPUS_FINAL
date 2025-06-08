package utils;

import java.time.LocalDate;

public class DateUtils { // ← nama class diperbaiki di sini
    public static LocalDate today() {
        return LocalDate.now();
    }

    public static LocalDate daysFromNow(int days) {
        return LocalDate.now().plusDays(days);
    }
}
