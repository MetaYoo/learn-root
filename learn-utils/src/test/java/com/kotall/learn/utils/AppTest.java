package com.kotall.learn.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testDateUtils() {
        LocalDate localDate = DateUtils.parseToDate("2019-03-24", "yyyy-MM-dd");
        Assert.assertEquals("2019-03-24", localDate.toString());

        LocalTime localTime = DateUtils.parseToTime("09:07:01", "HH:mm:ss");
        Assert.assertEquals("09:07:01", localTime.toString());

        LocalDateTime localDateTime = DateUtils.parseToDateTime("2019-03-24 09:07:01", "yyyy-MM-dd HH:mm:ss");
        Assert.assertEquals("2019-03-24T09:07:01", localDateTime.toString());

        localDateTime = DateUtils.atStartTimeOfDay(LocalDate.now());
        System.out.println(localDateTime);

        localDateTime = DateUtils.atEndTimeOfDay(LocalDate.now());
        System.out.println(localDateTime);

        long years = DateUtils.internalYears(localDate, LocalDate.now().plusYears(6));
        System.out.println(years);
        long months = DateUtils.internalMonths(localDate, LocalDate.now().plusYears(6));
        System.out.println(months);
        long days = DateUtils.internalDays(localDate, LocalDate.now().plusYears(6));
        System.out.println(days);

        String f = DateUtils.format(localDateTime, "yyyy-MM-dd HH:mm:ss");
        System.out.println(f);

    }
}
