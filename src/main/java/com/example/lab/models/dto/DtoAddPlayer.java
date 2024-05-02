package com.example.lab.models.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * Dto class for adding Players
 */
@Getter
@Setter
public class DtoAddPlayer {
    @NotBlank(message = "Name have errors")
    private String name;
    @NotBlank(message = "Position have errors")
    private String position;
    @Positive(message = "age must be positive number")
    private int age;
    @NotNull(message = "Player always need team")
    private Long team_id;

    public DtoAddPlayer(String name, String position, int age, Long team_id) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.team_id = team_id;
    }

    @Override
    public String toString() {
        return "DtoAddPlayer{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", age=" + age +
                ", team_id=" + team_id +
                '}';
    }
}
