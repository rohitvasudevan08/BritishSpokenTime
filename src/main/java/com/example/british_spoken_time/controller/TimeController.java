package com.example.british_spoken_time.controller;

import com.example.british_spoken_time.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/time")
public class TimeController {

    private final TimeSpokenService timeSpokenService;

    public TimeController(TimeSpokenService timeSpokenService) {
        this.timeSpokenService = timeSpokenService;
    }

    @GetMapping("/spoken")
    public String getSpokenTime(@RequestParam String time) {
        return timeSpokenService.toSpokenTime(time);
    }
}
