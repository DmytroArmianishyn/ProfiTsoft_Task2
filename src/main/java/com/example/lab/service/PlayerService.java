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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.List;

@Service
@Slf4j
public class PlayerService {
    @Autowired
    private PlayerRepository repository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    ObjectMapper mapper;

    public void add(DtoAddPlayer player){
        Long teamId = player.getTeam_id();
        Team team = teamRepository.findById(teamId);
        Player player1 = new Player(player.getName(),player.getPosition(),team,player.getAge());
        repository.save(player1);
        log.info("new row " + player1);
    }

    @SneakyThrows
    public String infPlayer(Long id){

        DtoPlayerInf player = repository.findPlayer(id);
        return mapper.writeValueAsString(player);
    }

    @SneakyThrows
    public String updatePlayer(Long id,DtoAddPlayer playerNew){
        Team team = teamRepository.findById(playerNew.getTeam_id());
        Player player = new Player(playerNew.getName(),playerNew.getPosition(),team,playerNew.getAge());
        player.setId(id);
        repository.save(player);
        return mapper.writeValueAsString(player);
    }

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
