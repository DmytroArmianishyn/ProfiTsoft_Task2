package com.example.lab.service;

import com.example.lab.models.Team;
import com.example.lab.models.dto.DtoPlayerInf;
import com.example.lab.models.dto.DtoTeamId;
import com.example.lab.models.dto.DtoTeamInf;
import com.example.lab.repository.PlayerRepository;
import com.example.lab.repository.TeamRepository;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.List;


@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

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

    public String udateTeam(int id, DtoTeamInf teamInf){

        DtoTeamInf teamTmp = teamRepository.findByName(teamInf.getName());
        if (teamTmp == null) {
            Team team = new Team(teamInf.getName(), teamInf.getCity());
            teamRepository.save(team);
            return "Your team added";
        }
        return "You create team with duplicate name";
    }

    public void makeReport(DtoTeamId team){
        List<DtoPlayerInf> players = repository.findByTeam(team.getId());
        report(players);
    }
    @SneakyThrows
    public void report(List<DtoPlayerInf> players){
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
        String[] player = {"Lebron","AD","Russl","Rivers"};
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
        FileOutputStream out = new FileOutputStream("D:\\Java\\Lab\\src\\main\\resources\\files/test.xls");
        wb.write(out);
        wb.close();
    }
    }