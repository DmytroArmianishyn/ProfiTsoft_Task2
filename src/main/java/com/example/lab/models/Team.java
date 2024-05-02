package com.example.lab.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Getter
@Setter
@Component
@Table(name = "Team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(name = "name")
    private String name;
    @Column(name = "city")
    private String city;

    public Team(String name, String city) {
        this.name = name;
        this.city = city;
    }
    public Team() {
    }

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players;

}
