package com.example.lab.сontrollers;


import com.example.lab.models.dto.DtoAddPlayer;
import com.example.lab.models.dto.DtoTeamId;
import com.example.lab.repository.PlayerRepository;
import com.example.lab.repository.TeamRepository;
import com.example.lab.service.PlayerService;
import com.example.lab.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * This controller class handles HTTP requests related to players.
 */
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

    /**
     * Adds a new player.
     * @param player The player data to be added.
     */
    @PostMapping(path = "/api/player", consumes = "application/json")
    public void addPlayer(@Valid @RequestBody DtoAddPlayer player){
        service.add(player);

    }
    /**
     * Retrieves player information by ID.
     * @param id The ID of the player to retrieve.
     * @return The player information.
     */
    @SneakyThrows
    @GetMapping("/api/player/{id}")
    public String getPlayersById(@PathVariable Long id){
       return service.infPlayer(id);
    }

    /**
     * Updates player information.
     * @param id The ID of the player to update.
     * @param playerNew The updated player data.
     * @return The updated player information.
     */
    @SneakyThrows
    @PutMapping("/api/player/{id}")
    public String update(@PathVariable Long id,@RequestBody DtoAddPlayer playerNew){
        return service.updatePlayer(id,playerNew);
    }
    /**
     * Deletes a player by ID.
     * @param id The ID of the player to delete.
     */
    @SneakyThrows
    @DeleteMapping("/api/player/{id}")
    public void delete(@PathVariable int id){
        repository.deleteById(id);
    }


    /**
     * Loads players from a file.
     * @param multipartFile The file containing player data.
     * @return A message indicating the success or failure of the operation.
     */
    @SneakyThrows
    @PostMapping(value = "api/load",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public String load(@RequestParam("file")MultipartFile multipartFile){
        return service.readPlayers(multipartFile);
    }

    /**
     * Creates a report of players for a specified team.
     * @param teamId The ID of the team.
     * @return The Excel report containing player information.
     */
    @SneakyThrows
    @PostMapping(value = "/api/player/_report", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> creteReport(@RequestBody DtoTeamId teamId){
       byte[] bytes = teamService.makeReport(teamId);
       return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"file name " + "Stat.xls").body(bytes);

    }
}