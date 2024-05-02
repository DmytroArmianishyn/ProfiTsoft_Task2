package com.example.lab.parser;


import com.example.lab.models.dto.DtoAddPlayer;
import com.example.lab.models.dto.Stat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for parsing a JSON file
 */
public class PlayerParser {

    /**
     * Parses json format and creates players, then adds them to the player array
     * @param mapper Class object ObjectMapper
     * @param json Accepts a json string
     * @return The List of players
     */
    public List<DtoAddPlayer> playerCreated(ObjectMapper mapper, String json, Stat stat){
        List<DtoAddPlayer> players = new ArrayList<>();
        int countAccept=0;
        int countFailed=0;
        try {
            players=mapper.readValue(json, new TypeReference<List<DtoAddPlayer>>(){});
            for (DtoAddPlayer player:players) {
                if (player.getName() == null || player.getPosition() == null || player.getTeam_id()==null)
                    countFailed++;
                else
                    countAccept++;
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        stat.setAccepted(countAccept);
        stat.setFailed(countFailed);

        return players;
    }
}
