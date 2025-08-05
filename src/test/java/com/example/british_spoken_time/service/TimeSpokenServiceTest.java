package com.example.british_spoken_time.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeSpokenServiceTest {

    private final TimeSpokenService service = new TimeSpokenService();

    @Test
    void testExactHours() {
        assertEquals("midnight", service.toSpokenTime("00:00"));
        assertEquals("noon", service.toSpokenTime("12:00"));
        assertEquals("one o'clock", service.toSpokenTime("01:00"));
    }

    @Test
    void testPastTimes() {
        assertEquals("5 past two", service.toSpokenTime("02:05"));
        assertEquals("quarter past four", service.toSpokenTime("04:15"));
        assertEquals("half past seven", service.toSpokenTime("07:30"));
    }

    @Test
    void testToTimes() {
        assertEquals("25 to eight", service.toSpokenTime("07:35"));
        assertEquals("quarter to ten", service.toSpokenTime("09:45"));
        assertEquals("5 to twelve", service.toSpokenTime("11:55"));
    }

    @Test
    void testMinuteEdgeCase() {
        assertEquals("28 to seven", service.toSpokenTime("06:32"));
        assertEquals("10 to eleven", service.toSpokenTime("10:50"));
    }

    @Test
    void testInvalidFormat() {
        assertThrows(Exception.class, () -> service.toSpokenTime("abc"));
        assertThrows(Exception.class, () -> service.toSpokenTime("25:00"));
        assertThrows(Exception.class, () -> service.toSpokenTime("12:60"));
    }
}
