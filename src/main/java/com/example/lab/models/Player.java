package com.example.lab.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Entity
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "position")
    private String position;
    @Column(name = "age")
    private int age;
    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnore
    private Team team;
    public Player( String name, String position, Team team, int age) {
        this.name = name;
        this.position = position;
        this.team = team;
        this.age = age;
    }
    public Player() {
    }
    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", team='" + team + '\'' +
                ", age=" + age +
                '}';
    }
}
