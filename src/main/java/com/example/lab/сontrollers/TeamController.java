package com.example.lab.сontrollers;
import com.example.lab.models.dto.DtoPlayerInf;
import com.example.lab.models.dto.DtoTeamInf;
import com.example.lab.models.dto.DtoTeamSearch;
import com.example.lab.repository.PlayerRepository;
import com.example.lab.repository.TeamRepository;
import com.example.lab.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class TeamController {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository repository;

    @Autowired
    TeamService teamService;


    @SneakyThrows
    @GetMapping("/api/teams")
    public String getTeams(){
        List<DtoTeamInf> teams = teamRepository.findAllTeams();
        return mapper.writeValueAsString(teams);
    }

    @DeleteMapping("api/teams/{id}")
    public void deleteTeam(@PathVariable int id){
        teamRepository.deleteById(id);
    }

    @SneakyThrows
    @PostMapping("/api/team")
    public String addTeam(@RequestBody DtoTeamInf teamInf) {
       return teamService.addTeam(teamInf);
    }

    @SneakyThrows
    @PutMapping("/api/team/{id}")
    public void update(DtoTeamInf teamInf){
    }

    /**
     * Retrieves a list of players for a specified team.
     * @param team The search criteria for players.
     * @return The list of players and total pages.
     */
    @SneakyThrows
    @PostMapping("/api/team/_list")
    public String putList(@RequestBody DtoTeamSearch team,ObjectMapper mapper){
       return teamService.putlist(team,mapper);
    }
}
