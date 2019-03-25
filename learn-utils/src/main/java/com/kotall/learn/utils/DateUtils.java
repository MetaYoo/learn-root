package com.kotall.learn.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;

/**
 * 描述：
 * java8+
 *
 * @author: zpwang
 * @time: 2019/3/23 20:35
 */
public class DateUtils {

    /**
     * ISO8601标准
     */
    private static final String ISO_DATE_TIME_FORMATTER = "yyyy-MM-dd'T'HH:mm:ssXXX";

    /**
     * 日期默认格式
     */
    private static final String DEFAULT_DATE_FORMATTER = "yyyy-MM-dd";
    /**
     * 时间默认格式
     */
    private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
    /**
     * 日期时间默认格式
     */
    private static final String DEFAULT_DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将日期格式化成ISO8061格式字符串
     *
     * @param date
     * @return
     */
    public static String formatISO8601DateString(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return format(localDateTime, ISO_DATE_TIME_FORMATTER);
    }


    /**
     * 格式化日期时间为默认格式字符串
     *
     * @param dateTime
     * @return
     */
    public static String format(LocalDateTime dateTime) {
        return format(dateTime, DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * 格式化日期时间为制定格式字符串
     *
     * @param dateTime
     * @param pattern
     * @return
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        return format(dateTime, pattern, Locale.getDefault());
    }

    /**
     * 格式化日期时间为制定格式本地化的字符串
     *
     * @param dateTime
     * @param pattern
     * @param locale
     * @return
     */
    public static String format(LocalDateTime dateTime, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, locale);
        return formatter.format(dateTime);
    }

    /**
     * 将默认格式的日期转换成日期对象
     *
     * @param dateStr
     * @return
     */
    public static LocalDate parseToDate(String dateStr) {
        return parseToDate(dateStr, DEFAULT_DATE_FORMATTER);
    }

    /**
     * 将制定格式的日期转换成日期对象
     *
     * @param dateStr
     * @return
     */
    public static LocalDate parseToDate(String dateStr, String pattern) {
        return parseToDate(dateStr, pattern, Locale.getDefault());
    }

    /**
     * 将制定格式的日期转换成本地化的日期对象
     *
     * @param dateStr
     * @param locale
     * @return
     */
    public static LocalDate parseToDate(String dateStr, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, locale);
        return LocalDate.parse(dateStr, formatter);
    }

    /**
     * 将默认格式的日期时间字符串转换成LocalTime
     *
     * @param timeStr
     * @return
     */
    public static LocalTime parseToTime(String timeStr) {
        return parseToTime(timeStr, DEFAULT_TIME_PATTERN);
    }

    /**
     * 将制定格式的日期时间字符串转换成LocalTime
     *
     * @param timeStr
     * @param pattern
     * @return
     */
    public static LocalTime parseToTime(String timeStr, String pattern) {
        return parseToTime(timeStr, pattern, Locale.getDefault());
    }

    /**
     * 将制定格式的日期时间字符串转换成本地化的LocalTime
     *
     * @param timeStr
     * @param pattern
     * @param locale
     * @return
     */
    public static LocalTime parseToTime(String timeStr, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, locale);
        return LocalTime.parse(timeStr, formatter);
    }

    /**
     * 将默认格式的日期时间字符串转换成LocalDateTime
     *
     * @param dateTimeStr
     * @return
     */
    public static LocalDateTime parseToDateTime(String dateTimeStr) {
        return parseToDateTime(dateTimeStr, DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * 将制定格式的日期时间字符串转换成LocalDateTime
     *
     * @param dateTimeStr
     * @param pattern
     * @return
     */
    public static LocalDateTime parseToDateTime(String dateTimeStr, String pattern) {
        return parseToDateTime(dateTimeStr, pattern, Locale.getDefault());
    }

    /**
     * 将制定格式的日期时间字符串转换成本地化的LocalDateTime
     *
     * @param dateTimeStr
     * @param pattern
     * @param locale
     * @return
     */
    public static LocalDateTime parseToDateTime(String dateTimeStr, String pattern, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                .withLocale(locale)
                .withZone(ZoneId.systemDefault());
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    /**
     * 将时间戳转换成LocalDateTIme对象
     *
     * @param timeStamp
     * @return
     */
    public static LocalDateTime parseFromTimeStamp(long timeStamp) {
        return parseFromTimeStampMilli(timeStamp, ZoneId.systemDefault());
    }

    /**
     * 将时间戳转换成指定时区的LocalDateTIme对象
     *
     * @param timeStamp
     * @param zoneId
     * @return
     */
    public static LocalDateTime parseFromTimeStampMilli(long timeStamp, ZoneId zoneId) {
        Instant instant = Instant.ofEpochMilli(timeStamp);
        return LocalDateTime.ofInstant(instant, zoneId);
    }


    /**
     * 将LocalDateTime转换成毫秒
     *
     * @param dateTime
     * @return
     */
    public static long toTimeStampMilli(LocalDateTime dateTime) {
        return toTimeStampMilli(dateTime, ZoneId.systemDefault());
    }

    /**
     * 将LocalDateTime转换成毫秒
     *
     * @param dateTime
     * @param zoneId
     * @return
     */
    public static long toTimeStampMilli(LocalDateTime dateTime, ZoneId zoneId) {
        Instant instant = dateTime.atZone(zoneId).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 将java.util.Date转换成默认时区的LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(java.util.Date date) {
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 将java.util.Date转换成指定时区的LocalDateTime
     *
     * @param date
     * @param zoneId
     * @return
     */
    public static LocalDateTime toLocalDateTime(java.util.Date date, ZoneId zoneId) {
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    /**
     * 将LocalDateTime转换成默认时区的java.util.Date
     *
     * @param localDateTime
     * @return
     */
    public static java.util.Date toDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return java.util.Date.from(instant);
    }

    /**
     * 将LocalDateTime转换成指定时区的java.util.Date
     *
     * @param localDateTime
     * @param zoneId
     * @return
     */
    public static java.util.Date toDate(LocalDateTime localDateTime, ZoneId zoneId) {
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        return java.util.Date.from(instant);
    }

    /**
     * 当前月的第一天
     *
     * @param localDate
     * @return
     */
    public static LocalDate firstDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 给定LocalDate的月份的最后一天
     *
     * @param localDate
     * @return
     */
    public static LocalDate lastDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 给定LocalDate的月份的下一个月的第一天
     *
     * @param localDate
     * @return
     */
    public static LocalDate firstDayOfNextMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfNextMonth());
    }

    /**
     * 下一年的第一天
     *
     * @param localDate
     * @return
     */
    public static LocalDate firstDayOfYear(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfYear());
    }

    /**
     * 给定LocalDate的年份的第一天
     *
     * @param localDate
     * @return
     */
    public static LocalDate lastDayOfYear(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfYear());
    }

    /**
     * 给定LocalDate的下一年的第一天
     *
     * @param localDate
     * @return
     */
    public static LocalDate firstDayOfNextYear(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfNextYear());
    }

    /**
     * 给定日期的开始时间
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime atStartTimeOfDay(LocalDate localDate) {
        return localDate.atTime(LocalTime.of(0, 0, 0));
    }

    /**
     * 给定日期的结束时间
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime atEndTimeOfDay(LocalDate localDate) {
        return localDate.atTime(LocalTime.of(23, 59, 59));
    }

    /**
     * 给定日期前一天的开始时间
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime atStartTimeOfLastDay(LocalDate localDate) {
        return localDate.plusDays(-1).atTime(LocalTime.of(0, 0, 0));
    }

    /**
     * 给定日期前一天的结束时间
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime atEndTimeOfLastDay(LocalDate localDate) {
        return localDate.plusDays(-1).atTime(LocalTime.of(23, 59, 59));
    }

    /**
     * 给定日期下一天的开始时间
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime atStartTimeOfNextDay(LocalDate localDate) {
        return localDate.plusDays(1).atTime(LocalTime.of(0, 0, 0));
    }

    /**
     * 给定日期下一天的结束时间
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime atEndTimeOfNextDay(LocalDate localDate) {
        return localDate.plusDays(1).atTime(LocalTime.of(23, 59, 59));
    }

    /**
     * 相隔年
     *
     * @return
     */
    public static long internalYears(LocalDate from, LocalDate to) {
        return from.until(to, ChronoUnit.YEARS);
    }

    /**
     * 相隔月
     *
     * @return
     */
    public static long internalMonths(LocalDate from, LocalDate to) {
        return from.until(to, ChronoUnit.MONTHS);
    }

    /**
     * 相隔天
     *
     * @return
     */
    public static long internalDays(LocalDate from, LocalDate to) {
        return from.until(to, ChronoUnit.DAYS);
    }

    /**
     * 相隔小时
     *
     * @return
     */
    public static long internalHours(LocalTime from, LocalTime to) {
        return from.until(to, ChronoUnit.HOURS);
    }

    /**
     * 相隔分钟
     *
     * @return
     */
    public static long internalMinutes(LocalTime from, LocalTime to) {
        return from.until(to, ChronoUnit.MINUTES);
    }

    /**
     * 相隔秒
     *
     * @return
     */
    public static long internalSeconds(LocalTime from, LocalTime to) {
        return from.until(to, ChronoUnit.SECONDS);
    }

    /**
     * 计算指定默认格式日期时间字符串之间相隔年
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @return
     */
    public static long internalYearsOfDateTimeStr(String fromDateTimeStr, String toDateTimeStr) {
        return internalYearsOfDateTimeStr(fromDateTimeStr, toDateTimeStr, DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * 计算指定默认格式日期时间字符串之间相隔年
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @param pattern
     * @return
     */
    public static long internalYearsOfDateTimeStr(String fromDateTimeStr, String toDateTimeStr, String pattern) {
        return internalYearsOfDateTimeStr(fromDateTimeStr, pattern, toDateTimeStr, pattern);
    }

    /**
     * 计算指定格式日期时间字符串之间相隔年
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @return
     */
    public static long internalYearsOfDateTimeStr(String fromDateTimeStr, String fromDateTimePattern, String toDateTimeStr, String toDateTimePattern) {
        LocalDateTime fromDateTime = parseToDateTime(fromDateTimeStr, fromDateTimePattern);
        LocalDateTime toDateTime = parseToDateTime(toDateTimeStr, toDateTimePattern);
        return internalYears(fromDateTime.toLocalDate(), toDateTime.toLocalDate());
    }

    /**
     * 计算指定默认格式日期时间字符串之间相隔月
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @return
     */
    public static long internalMonthsOfDateTimeStr(String fromDateTimeStr, String toDateTimeStr) {
        return internalMonthsOfDateTimeStr(fromDateTimeStr, toDateTimeStr, DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * 计算指定默认格式日期时间字符串之间相隔月
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @param pattern
     * @return
     */
    public static long internalMonthsOfDateTimeStr(String fromDateTimeStr, String toDateTimeStr, String pattern) {
        return internalMonthsOfDateTimeStr(fromDateTimeStr, pattern, toDateTimeStr, pattern);
    }

    /**
     * 计算指定格式日期时间字符串之间相隔月
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @return
     */
    public static long internalMonthsOfDateTimeStr(String fromDateTimeStr, String fromDateTimePattern, String toDateTimeStr, String toDateTimePattern) {
        LocalDateTime fromDateTime = parseToDateTime(fromDateTimeStr, fromDateTimePattern);
        LocalDateTime toDateTime = parseToDateTime(toDateTimeStr, toDateTimePattern);
        return internalMonths(fromDateTime.toLocalDate(), toDateTime.toLocalDate());
    }

    /**
     * 计算指定默认格式日期时间字符串之间相隔日
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @return
     */
    public static long internalDaysOfDateTimeStr(String fromDateTimeStr, String toDateTimeStr) {
        return internalDaysOfDateTimeStr(fromDateTimeStr, toDateTimeStr, DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * 计算指定默认格式日期时间字符串之间相隔日
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @param pattern
     * @return
     */
    public static long internalDaysOfDateTimeStr(String fromDateTimeStr, String toDateTimeStr, String pattern) {
        return internalDaysOfDateTimeStr(fromDateTimeStr, pattern, toDateTimeStr, pattern);
    }

    /**
     * 计算指定格式日期时间字符串之间相隔日
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @return
     */
    public static long internalDaysOfDateTimeStr(String fromDateTimeStr, String fromDateTimePattern, String toDateTimeStr, String toDateTimePattern) {
        LocalDateTime fromDateTime = parseToDateTime(fromDateTimeStr, fromDateTimePattern);
        LocalDateTime toDateTime = parseToDateTime(toDateTimeStr, toDateTimePattern);
        return internalDays(fromDateTime.toLocalDate(), toDateTime.toLocalDate());
    }


    /**
     * 计算制定格式时间日期字符串之间相隔小时
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @return
     */
    public static long internalHoursOfDateTimeStr(String fromDateTimeStr, String toDateTimeStr) {
        return internalHoursOfDateTimeStr(fromDateTimeStr, toDateTimeStr, DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * 计算制定格式时间日期字符串之间相隔小时
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @param pattern
     * @return
     */
    public static long internalHoursOfDateTimeStr(String fromDateTimeStr, String toDateTimeStr, String pattern) {
        return internalHoursOfDateTimeStr(fromDateTimeStr, pattern, toDateTimeStr, pattern);
    }

    /**
     * 计算制定格式时间日期字符串之间相隔小时
     *
     * @param fromDateTimeStr
     * @param fromDateTimePattern
     * @param toDateTimeStr
     * @param toDateTimePattern
     * @return
     */
    public static long internalHoursOfDateTimeStr(String fromDateTimeStr, String fromDateTimePattern, String toDateTimeStr, String toDateTimePattern) {
        LocalDateTime fromDateTime = parseToDateTime(fromDateTimeStr, fromDateTimePattern);
        LocalDateTime toDateTime = parseToDateTime(toDateTimeStr, toDateTimePattern);
        return internalHours(fromDateTime.toLocalTime(), toDateTime.toLocalTime());
    }

    /**
     * 计算制定格式时间日期字符串之间相隔分
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @return
     */
    public static long internalMinutesOfDateTimeStr(String fromDateTimeStr, String toDateTimeStr) {
        return internalMinutesOfDateTimeStr(fromDateTimeStr, toDateTimeStr, DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * 计算制定格式时间日期字符串之间相隔分
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @param pattern
     * @return
     */
    public static long internalMinutesOfDateTimeStr(String fromDateTimeStr, String toDateTimeStr, String pattern) {
        return internalMinutesOfDateTimeStr(fromDateTimeStr, pattern, toDateTimeStr, pattern);
    }

    /**
     * 计算制定格式时间日期字符串之间相隔分
     *
     * @param fromDateTimeStr
     * @param fromDateTimePattern
     * @param toDateTimeStr
     * @param toDateTimePattern
     * @return
     */
    public static long internalMinutesOfDateTimeStr(String fromDateTimeStr, String fromDateTimePattern, String toDateTimeStr, String toDateTimePattern) {
        LocalDateTime fromDateTime = parseToDateTime(fromDateTimeStr, fromDateTimePattern);
        LocalDateTime toDateTime = parseToDateTime(toDateTimeStr, toDateTimePattern);
        return internalMinutes(fromDateTime.toLocalTime(), toDateTime.toLocalTime());
    }

    /**
     * 计算制定格式时间日期字符串之间相隔秒
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @return
     */
    public static long internalSecondsOfDateTimeStr(String fromDateTimeStr, String toDateTimeStr) {
        return internalSecondsOfDateTimeStr(fromDateTimeStr, toDateTimeStr, DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * 计算制定格式时间日期字符串之间相隔秒
     *
     * @param fromDateTimeStr
     * @param toDateTimeStr
     * @param pattern
     * @return
     */
    public static long internalSecondsOfDateTimeStr(String fromDateTimeStr, String toDateTimeStr, String pattern) {
        return internalSecondsOfDateTimeStr(fromDateTimeStr, pattern, toDateTimeStr, pattern);
    }


    /**
     * 计算制定格式时间日期字符串之间相隔秒
     *
     * @param fromDateTimeStr
     * @param fromDateTimePattern
     * @param toDateTimeStr
     * @param toDateTimePattern
     * @return
     */
    public static long internalSecondsOfDateTimeStr(String fromDateTimeStr, String fromDateTimePattern, String toDateTimeStr, String toDateTimePattern) {
        LocalDateTime fromDateTime = parseToDateTime(fromDateTimeStr, fromDateTimePattern);
        LocalDateTime toDateTime = parseToDateTime(toDateTimeStr, toDateTimePattern);
        return internalSeconds(fromDateTime.toLocalTime(), toDateTime.toLocalTime());
    }


    /**
     * 计算指定默认格式日期时间字符串之间相隔年
     *
     * @param fromDateStr
     * @param toDateStr
     * @return
     */
    public static long internalYearsOfDateStr(String fromDateStr, String toDateStr) {
        return internalYearsOfDateStr(fromDateStr, DEFAULT_DATE_FORMATTER, toDateStr, DEFAULT_DATE_FORMATTER);
    }

    /**
     * 计算指定默认格式日期时间字符串之间相隔年
     *
     * @param fromDateStr
     * @param toDateStr
     * @param pattern
     * @return
     */
    public static long internalYearsOfDateStr(String fromDateStr, String toDateStr, String pattern) {
        return internalYearsOfDateStr(fromDateStr, pattern, toDateStr, pattern);
    }

    /**
     * 计算指定格式日期时间字符串之间相隔年
     *
     * @param fromDateStr
     * @param toDateStr
     * @return
     */
    public static long internalYearsOfDateStr(String fromDateStr, String fromDatePattern, String toDateStr, String toDatePattern) {
        LocalDate fromDate = parseToDate(fromDateStr, fromDatePattern);
        LocalDate toDate = parseToDate(toDateStr, toDatePattern);
        return internalYears(fromDate, toDate);
    }

    /**
     * 计算指定默认格式日期时间字符串之间相隔月
     *
     * @param fromDateStr
     * @param toDateStr
     * @return
     */
    public static long internalMonthsOfDateStr(String fromDateStr, String toDateStr) {
        return internalMonthsOfDateStr(fromDateStr, toDateStr, DEFAULT_DATE_FORMATTER);
    }

    /**
     * 计算指定默认格式日期时间字符串之间相隔月
     *
     * @param fromDateStr
     * @param toDateStr
     * @param pattern
     * @return
     */
    public static long internalMonthsOfDateStr(String fromDateStr, String toDateStr, String pattern) {
        return internalMonthsOfDateStr(fromDateStr, pattern, toDateStr, pattern);
    }

    /**
     * 计算指定格式日期时间字符串之间相隔月
     *
     * @param fromDateStr
     * @param toDateStr
     * @return
     */
    public static long internalMonthsOfDateStr(String fromDateStr, String fromDatePattern, String toDateStr, String toDatePattern) {
        LocalDate fromDate = parseToDate(fromDateStr, fromDatePattern);
        LocalDate toDate = parseToDate(toDateStr, toDatePattern);
        return internalMonths(fromDate, toDate);
    }

    /**
     * 计算指定默认格式日期时间字符串之间相隔日
     *
     * @param fromDateStr
     * @param toDateStr
     * @return
     */
    public static long internalDaysOfDateStr(String fromDateStr, String toDateStr) {
        return internalDaysOfDateStr(fromDateStr, toDateStr, DEFAULT_DATE_FORMATTER);
    }

    /**
     * 计算指定默认格式日期时间字符串之间相隔日
     *
     * @param fromDateStr
     * @param toDateStr
     * @param pattern
     * @return
     */
    public static long internalDaysOfDateStr(String fromDateStr, String toDateStr, String pattern) {
        return internalDaysOfDateStr(fromDateStr, pattern, toDateStr, pattern);
    }

    /**
     * 计算指定格式日期时间字符串之间相隔日
     *
     * @param fromDateStr
     * @param toDateStr
     * @return
     */
    public static long internalDaysOfDateStr(String fromDateStr, String fromDatePattern, String toDateStr, String toDatePattern) {
        LocalDate fromDate = parseToDate(fromDateStr, fromDatePattern);
        LocalDate toDate = parseToDate(toDateStr, toDatePattern);
        return internalDays(fromDate, toDate);
    }


    /**
     * 计算制定格式时间日期字符串之间相隔小时
     *
     * @param fromTimeStr
     * @param toTimeStr
     * @return
     */
    public static long internalHoursOfTimeStr(String fromTimeStr, String toTimeStr) {
        return internalHoursOfTimeStr(fromTimeStr, toTimeStr, DEFAULT_TIME_PATTERN);
    }

    /**
     * 计算制定格式时间日期字符串之间相隔小时
     *
     * @param fromTimeStr
     * @param toTimeStr
     * @param pattern
     * @return
     */
    public static long internalHoursOfTimeStr(String fromTimeStr, String toTimeStr, String pattern) {
        return internalHoursOfTimeStr(fromTimeStr, pattern, toTimeStr, pattern);
    }

    /**
     * 计算制定格式时间日期字符串之间相隔小时
     *
     * @param fromTimeStr
     * @param fromTimePattern
     * @param toTimeStr
     * @param toTimePattern
     * @return
     */
    public static long internalHoursOfTimeStr(String fromTimeStr, String fromTimePattern, String toTimeStr, String toTimePattern) {
        LocalTime fromTime = parseToTime(fromTimeStr, fromTimePattern);
        LocalTime toTime = parseToTime(toTimeStr, toTimePattern);
        return internalHours(fromTime, toTime);
    }

    /**
     * 计算制定格式时间日期字符串之间相隔分
     *
     * @param fromTimeStr
     * @param toTimeStr
     * @return
     */
    public static long internalMinutesOfTimeStr(String fromTimeStr, String toTimeStr) {
        return internalMinutesOfTimeStr(fromTimeStr, toTimeStr, DEFAULT_TIME_PATTERN);
    }

    /**
     * 计算制定格式时间日期字符串之间相隔分
     *
     * @param fromTimeStr
     * @param toTimeStr
     * @param pattern
     * @return
     */
    public static long internalMinutesOfTimeStr(String fromTimeStr, String toTimeStr, String pattern) {
        return internalMinutesOfTimeStr(fromTimeStr, pattern, toTimeStr, pattern);
    }

    /**
     * 计算制定格式时间日期字符串之间相隔分
     *
     * @param fromTimeStr
     * @param fromTimePattern
     * @param toTimeStr
     * @param toTimePattern
     * @return
     */
    public static long internalMinutesOfTimeStr(String fromTimeStr, String fromTimePattern, String toTimeStr, String toTimePattern) {
        LocalTime fromTime = parseToTime(fromTimeStr, fromTimePattern);
        LocalTime toTime = parseToTime(toTimeStr, toTimePattern);
        return internalMinutes(fromTime, toTime);
    }

    /**
     * 计算制定格式时间日期字符串之间相隔秒
     *
     * @param fromTimeStr
     * @param toTimeStr
     * @return
     */
    public static long internalSecondsOfTimeStr(String fromTimeStr, String toTimeStr) {
        return internalSecondsOfTimeStr(fromTimeStr, toTimeStr, DEFAULT_TIME_PATTERN);
    }

    /**
     * 计算制定格式时间日期字符串之间相隔秒
     *
     * @param fromTimeStr
     * @param toTimeStr
     * @param pattern
     * @return
     */
    public static long internalSecondsOfTimeStr(String fromTimeStr, String toTimeStr, String pattern) {
        return internalSecondsOfTimeStr(fromTimeStr, pattern, toTimeStr, pattern);
    }

    /**
     * 计算制定格式时间日期字符串之间相隔秒
     *
     * @param fromTimeStr
     * @param fromTimePattern
     * @param toTimeStr
     * @param toTimePattern
     * @return
     */
    public static long internalSecondsOfTimeStr(String fromTimeStr, String fromTimePattern, String toTimeStr, String toTimePattern) {
        LocalTime fromTime = parseToTime(fromTimeStr, fromTimePattern);
        LocalTime toTime = parseToTime(toTimeStr, toTimePattern);
        return internalSeconds(fromTime, toTime);
    }

}
