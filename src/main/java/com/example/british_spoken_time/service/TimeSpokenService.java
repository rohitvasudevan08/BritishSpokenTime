package com.example.british_spoken_time.service;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeSpokenService {

    public String toSpokenTime(String timeInput) {
        LocalTime time = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("H:mm"));
        int hour = time.getHour();
        int minute = time.getMinute();

        if (hour == 0 && minute == 0) return "midnight";
        if (hour == 12 && minute == 0) return "noon";

        String[] numbers = {
            "twelve", "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine", "ten", "eleven"
        };

        int hourIn12 = hour % 12;
        String hourWord = numbers[hourIn12];
        String nextHourWord = numbers[(hourIn12 + 1) % 12];

        switch (minute) {
            case 0: return hourWord + " o'clock";
            case 15: return "quarter past " + hourWord;
            case 30: return "half past " + hourWord;
            case 45: return "quarter to " + nextHourWord;
            default:
                if (minute < 30) return minute + " past " + hourWord;
                else return (60 - minute) + " to " + nextHourWord;
        }
    }
}
