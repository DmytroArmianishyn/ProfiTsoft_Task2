package com.example.lab.service;
import com.example.lab.models.Team;
import com.example.lab.models.dto.DtoPlayerInf;
import com.example.lab.models.dto.DtoTeamId;
import com.example.lab.models.dto.DtoTeamInf;
import com.example.lab.models.dto.DtoTeamSearch;
import com.example.lab.repository.PlayerRepository;
import com.example.lab.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * This service class provides methods to handle team-related operations.
 */
@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    /**
     * Adds a new team.
     * @param teamInf The information of the team to be added.
     * @return A message indicating the success or failure of the operation.
     */
    @Autowired
    PlayerRepository repository;
    public String addTeam(DtoTeamInf teamInf){

        DtoTeamInf teamTmp = teamRepository.findByName(teamInf.getName());
        if (teamTmp == null) {
            Team team = new Team(teamInf.getName(), teamInf.getCity());
            teamRepository.save(team);
            return "Your team added";
        }
        return "You create team with duplicate name";
    }

    /**
     * Updates an existing team.
     * @param id The ID of the team to be updated.
     * @param teamInf The new information for the team.
     * @return A message indicating the success or failure of the operation.
     */
    public String udateTeam(int id, DtoTeamInf teamInf){

        DtoTeamInf teamTmp = teamRepository.findByName(teamInf.getName());
        if (teamTmp == null) {
            Team team = new Team(teamInf.getName(), teamInf.getCity());
            teamRepository.save(team);
            return "Your team added";
        }
        return "You create team with duplicate name";
    }
    /**
     * Generates a report of players in the specified team.
     * @param team The ID of the team.
     * @return The byte array representing the Excel file containing the player report.
     */
    public byte[] makeReport(DtoTeamId team){
        List<DtoPlayerInf> players = repository.findByTeam(team.getId());
         byte[] exelfile = report(players);
        return exelfile;
    }
    /**
     * Generates an Excel report of the players.
     * @param players The list of players.
     * @return The byte array representing the Excel file containing the player report.
     */
    @SneakyThrows
    public byte[] report(List<DtoPlayerInf> players){
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Players");
        Row row = sheet.createRow(0);
        Cell name = row.createCell(0);
        name.setCellValue("Name");
        Cell position = row.createCell(1);
        position.setCellValue("Position");
        Cell age = row.createCell(2);
        age.setCellValue("Age");
        Cell team = row.createCell(3);
        team.setCellValue("Team");
        sheet.setColumnWidth(0,5000);
        for (int i = 0; i <players.size() ; i++) {
            Row row1 = sheet.createRow(i+1);
            Cell name1 = row1.createCell(0);
            name1.setCellValue(players.get(i).getName());
            Cell position1 = row1.createCell(1);
            position1.setCellValue(players.get(i).getPosition());
            Cell age1 = row1.createCell(2);
            age1.setCellValue(players.get(i).getAge());
            Cell team1 = row1.createCell(3);
            team1.setCellValue(players.get(i).getTeamName());
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        wb.write(bos);
        wb.close();
        return bos.toByteArray();
    }
    /**
     * Retrieves a list of players for a specified team and returns the result in JSON format along with information about the total number of pages.
     * @param team The search criteria for players, including team ID, pagination details (from and size).
     * @param mapper ObjectMapper instance used for JSON serialization.
     * @return A JSON string containing the list of players and information about total pages.
     */
    @SneakyThrows
    public String putlist(DtoTeamSearch team, ObjectMapper mapper){

        Pageable pageable = PageRequest.of(team.getFrom(),team.getSize());
        List<DtoPlayerInf> players = repository.findByTeam(team.getId(), pageable,"name") ;
        int sub = team.getSize()-team.getFrom();
        Long allplayers = repository.findAllCount(team.getId());
        Long pages = ((allplayers-sub)/sub)+1;
        StringBuilder json= new StringBuilder();
        json.append(mapper.writeValueAsString(players));
        json.append("totalPages:" + pages);
        TeamService teamService = new TeamService();
        teamService.report(players);
        return json.toString();
    }
    }