package com.example.lab;

import com.example.lab.models.dto.DtoTeamInf;
import com.example.lab.repository.PlayerRepository;
import com.example.lab.repository.TeamRepository;
import com.example.lab.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private TeamRepository teamRepository;

    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    private TeamService teamService;

    @Test
    public void testGetTeams() throws Exception {
        when(teamRepository.findAllTeams()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teams"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    public void testDeleteTeam() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/teams/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddTeam() throws Exception {
        DtoTeamInf teamInf = new DtoTeamInf();
        teamInf.setName("Minnesota TimberWolfs");
        teamInf.setCity("Minnesota ");

        String teamInfJson = objectMapper.writeValueAsString(teamInf);

        when(teamService.addTeam(teamInf)).thenReturn("Success");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/team")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamInfJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Success"));
    }

    @Test
    public void testUpdateTeam() throws Exception {
        DtoTeamInf teamInf = new DtoTeamInf();
        teamInf.setName("Minnesota Bears");
        teamInf.setCity("Minnesota");

        String teamInfJson = objectMapper.writeValueAsString(teamInf);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/team/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamInfJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
