package com.example.british_spoken_time.controller;

import com.example.british_spoken_time.service.TimeSpokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TimeController.class)
class TimeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@MockBean
    private TimeSpokenService timeSpokenService;

    @Test
    void testGetSpokenTimeSuccess() throws Exception {
        when(timeSpokenService.toSpokenTime("07:30")).thenReturn("half past seven");

        mockMvc.perform(get("/api/time/spoken")
                .param("time", "07:30"))
                .andExpect(status().isOk())
                .andExpect(content().string("half past seven"));

        verify(timeSpokenService, times(1)).toSpokenTime("07:30");
    }

    @Test
    void testInvalidInputFormat() throws Exception {
        when(timeSpokenService.toSpokenTime("invalid"))
                .thenThrow(new RuntimeException("Invalid time"));

        mockMvc.perform(get("/api/time/spoken")
                .param("time", "invalid"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testMissingQueryParam() throws Exception {
        mockMvc.perform(get("/api/time/spoken"))
                .andExpect(status().isBadRequest());
    }
}
