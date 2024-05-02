package com.example.lab.repository;

import com.example.lab.models.dto.DtoTeamInf;
import com.example.lab.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {

    Team findById(Long id);

    @Query("SELECT new com.example.lab.models.dto.DtoTeamInf(t.name,t.city) from Team t ")
    List<DtoTeamInf> findAllTeams();

    @Query("SELECT new com.example.lab.models.dto.DtoTeamInf(t.name,t.city) from Team t where t.name=:name")
    DtoTeamInf findByName(@Param("name") String name);
}
