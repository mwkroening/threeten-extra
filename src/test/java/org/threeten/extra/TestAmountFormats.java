/*
 * Copyright (c) 2007-present, Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.threeten.extra;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.Period;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

/**
 * Test AmountFormats.
 */
@RunWith(DataProviderRunner.class)
public class TestAmountFormats {

    //-----------------------------------------------------------------------
    @Test
    public void test_iso8601() {
        assertEquals(AmountFormats.iso8601(Period.of(0, 12, 6), Duration.ofMinutes(8 * 60 + 30)), "P12M6DT8H30M");
        assertEquals(AmountFormats.iso8601(Period.ZERO, Duration.ofMinutes(8 * 60 + 30)), "PT8H30M");
        assertEquals(AmountFormats.iso8601(Period.of(0, 12, 6), Duration.ZERO), "P12M6D");
    }

    //-----------------------------------------------------------------------
    @DataProvider
    public static Object[][] data_wordBased() {
        return new Object[][] {
            {Period.ofYears(0), Locale.ROOT, "0 days"},
            {Period.ofYears(1), Locale.ROOT, "1 year"},
            {Period.ofYears(2), Locale.ROOT, "2 years"},
            {Period.ofYears(12), Locale.ROOT, "12 years"},
            {Period.ofYears(-1), Locale.ROOT, "-1 year"},

            {Period.ofWeeks(0), Locale.ENGLISH, "0 days"},
            {Period.ofWeeks(1), Locale.ENGLISH, "7 days"},
            {Period.ofWeeks(4), Locale.ENGLISH, "28 days"},
            
            {Period.ofMonths(0), Locale.ENGLISH, "0 days"},
            {Period.ofMonths(1), Locale.ENGLISH, "1 month"},
            {Period.ofMonths(4), Locale.ENGLISH, "4 months"},
            {Period.ofMonths(14), Locale.ENGLISH, "1 year and 2 months"},
            
            {Period.ofYears(0), Locale.ENGLISH, "0 days"},
            {Period.ofYears(1), Locale.ENGLISH, "1 year"},
            {Period.ofYears(2), Locale.ENGLISH, "2 years"},
            {Period.ofYears(12), Locale.ENGLISH, "12 years"},
            {Period.ofYears(-1), Locale.ENGLISH, "-1 year"},
        };
    }

    @Test
    @UseDataProvider("data_wordBased")
    public void test_wordBased(Period period, Locale locale, String expected) {
        assertEquals(AmountFormats.wordBased(period, locale), expected);
    }

    @DataProvider
    public static Object[][] duration_wordBased() {
        return new Object[][] {
        	{Duration.ofMinutes(180 + 2), Locale.ENGLISH, "3 hours 2 minutes"},
        	{Duration.ofSeconds(180), Locale.ENGLISH, "3 minutes"},
            {Duration.ofSeconds(100), Locale.ENGLISH, "1 minute 40 seconds"},
            {Duration.ofSeconds(-140), Locale.ENGLISH, "-2 minutes -20 seconds"},
            {Duration.ofSeconds(-90), Locale.ENGLISH, "-1 minute -30 seconds"},
            {Duration.ofSeconds(-40), Locale.ENGLISH, "-40 seconds"},
            {Duration.ofMillis(1_000), Locale.ENGLISH, "1 second"},
            {Duration.ofMillis(3_000), Locale.ENGLISH, "3 seconds"},
        };
    }
    
    @Test
    @UseDataProvider("duration_wordBased")
    public void test_wordBased(Duration duration, Locale locale, String expected) {
        assertEquals(AmountFormats.wordBased(duration, locale), expected);
    }
}
