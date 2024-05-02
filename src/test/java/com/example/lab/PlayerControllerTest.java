package com.example.lab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddPlayer() throws Exception {
        String playerJson = "{\"name\":\"LeBron James\",\"position\":\"Forward\",\"age\":36}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/player")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetPlayerById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/player/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("LeBron James"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.position").value("Forward"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(36));
    }

    @Test
    public void testUpdatePlayer() throws Exception {
        String updatedPlayerJson = "{\"name\":\"LeBron James\",\"position\":\"Forward\",\"age\":37}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/player/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPlayerJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeletePlayer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/player/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
