package com.example.lab.сontrollers;


import com.example.lab.models.dto.DtoAddPlayer;
import com.example.lab.models.dto.DtoPlayerInf;
import com.example.lab.models.dto.DtoTeamId;
import com.example.lab.models.dto.DtoTeamSearch;
import com.example.lab.repository.PlayerRepository;
import com.example.lab.repository.TeamRepository;
import com.example.lab.service.PlayerService;
import com.example.lab.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository repository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    ObjectMapper mapper;
    @Autowired
    PlayerService service;

    @PostMapping(path = "/api/player", consumes = "application/json")
    public void addPlayer(@Valid @RequestBody DtoAddPlayer player){
        service.add(player);

    }
    @SneakyThrows
    @GetMapping("/api/player/{id}")
    public String getPlayersById(@PathVariable Long id){
       return service.infPlayer(id);
    }

    @SneakyThrows
    @PutMapping("/api/player/{id}")
    public String update(@PathVariable Long id,@RequestBody DtoAddPlayer playerNew){
        return service.updatePlayer(id,playerNew);
    }
    @SneakyThrows
    @DeleteMapping("/api/player/{id}")
    public void delete(@PathVariable int id){
        repository.deleteById(id);
    }
    @SneakyThrows
    @PostMapping("/api/team/_list")
    public String putList(@RequestBody DtoTeamSearch team){
        Pageable pageable = PageRequest.of(team.getFrom(),team.getSize());
        List<DtoPlayerInf> players = repository.findByTeam(team.getId(), pageable,"name") ;
        int sub = team.getSize()-team.getFrom();
        Pageable countpages = PageRequest.of(team.getSize()+1,repository.findByTeam(team.getId()).size());
       Long allplayer = repository.findAllCount(team.getId());
        Long pages = (allplayer-sub)/sub;
        StringBuilder json= new StringBuilder();
        json.append(mapper.writeValueAsString(players));
        json.append("totalPages:" + pages);
        TeamService teamService = new TeamService();
        teamService.report(players);
        return json.toString();
    }

    @SneakyThrows
    @PostMapping(value = "api/load",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public String load(@RequestParam("file")MultipartFile multipartFile){
        return service.readPlayers(multipartFile);
    }

    @SneakyThrows
    @PostMapping("/api/player/_report")
    public void creteReport(@RequestBody DtoTeamId teamId){
        teamService.makeReport(teamId);
    }
}