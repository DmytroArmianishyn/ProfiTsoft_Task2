    package com.example.lab.repository;

    import com.example.lab.models.dto.DtoPlayerInf;
    import com.example.lab.models.Player;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;
    import java.util.List;

    @Repository
    public interface PlayerRepository extends JpaRepository<Player,Integer> {
        @Query("SELECT  new com.example.lab.models.dto.DtoPlayerInf(p.name,p.position,p.age,t.name) From Player p JOIN  p.team t where t.id=:team_id order by :category")
            List<DtoPlayerInf> findByTeam(@Param("team_id") Long team_id, Pageable pageable, @Param("category") String category);
        @Query("SELECT new com.example.lab.models.dto.DtoPlayerInf(p.name, p.position, p.age, t.name) FROM Player p JOIN p.team t WHERE p.id = :id")
        DtoPlayerInf findPlayer(@Param("id") Long id);
        @Query("SELECT  new com.example.lab.models.dto.DtoPlayerInf(p.name,p.position,p.age,t.name) From Player p JOIN  p.team t where t.id=:team_id ")
        List<DtoPlayerInf> findByTeam(@Param("team_id") Long team_id);

        @Query("SELECT count (*) FROM Player p where p.team.id=:team_id")
        Long findAllCount(@Param("team_id") Long team_id);
    }
