package com.example.aa1autoescuelaandroid.util;
import androidx.room.TypeConverter;
import java.time.LocalDate;

public class Converters {

    @TypeConverter
    public static LocalDate fromString(String value) {
        if (value == null) return null;
        return DateUtil.parseDate(value);
    }

    @TypeConverter
    public static String localDateToString(LocalDate date) {
        if (date == null) return null;
        return DateUtil.formatDate(date);
    }
}