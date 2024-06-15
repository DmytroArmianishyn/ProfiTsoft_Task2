package com.example.lab.service;

import com.example.lab.models.dto.DtoAddPlayer;
import com.example.lab.models.dto.DtoPlayerInf;
import com.example.lab.models.Player;
import com.example.lab.models.Team;
import com.example.lab.models.dto.Stat;
import com.example.lab.parser.PlayerParser;
import com.example.lab.repository.PlayerRepository;
import com.example.lab.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.List;

/**
 * This service class handles the business logic related to players.
 */
@Service
@Slf4j
public class PlayerService {
    @Autowired
    private PlayerRepository repository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * Adds a new player.
     * @param player The player information to be added.
     */
    public void add(DtoAddPlayer player){
        Long teamId = player.getTeam_id();
        Team team = teamRepository.findById(teamId);
        Player player1 = new Player(player.getName(),player.getPosition(),team,player.getAge());
        repository.save(player1);
        log.info("new row " + player1);
    }

    /**
     * Retrieves information about a player by ID.
     * @param id The ID of the player.
     * @return The information about the player as a JSON string.
     */
    @SneakyThrows
    public String infPlayer(Long id){


        DtoPlayerInf player = repository.findPlayer(id);
        kafkaTemplate.send("topic-email","some one search player with id " + id + " " + player.toString());
        return mapper.writeValueAsString(player);
    }
//    @SneakyThrows
//    public String infPlayer(){
//
//        DtoPlayerInf player = repository.findAll();
//        return mapper.writeValueAsString(player);
//    }

    /**
     * Updates an existing player.
     * @param id The ID of the player to be updated.
     * @param playerNew The new information for the player.
     * @return The updated player information as a JSON string.
     */
    @SneakyThrows
    public String updatePlayer(Long id,DtoAddPlayer playerNew){
        Team team = teamRepository.findById(playerNew.getTeam_id());
        Player player = new Player(playerNew.getName(),playerNew.getPosition(),team,playerNew.getAge());
        player.setId(id);
        repository.save(player);
        return mapper.writeValueAsString(player);
    }

    /**
     * Reads player information from a multipart file and adds them.
     * @param multipartFile The multipart file containing player information.
     * @return A message confirming the successful addition of players.
     */
    @SneakyThrows
    public  String readPlayers(MultipartFile multipartFile){
        File file = new File(multipartFile.getOriginalFilename());
        PlayerParser parser = new PlayerParser();
        byte[] bytes = multipartFile.getBytes();
        String text = new String(bytes);
        Stat stat = new Stat();
        List<DtoAddPlayer> players = parser.playerCreated(mapper,text,stat);
        for (int i = 0; i <players.size() ; i++) {
            add(players.get(i));
        }
        String mess = mapper.writeValueAsString(stat);
        return mapper.writeValueAsString(mess);

    }
}
