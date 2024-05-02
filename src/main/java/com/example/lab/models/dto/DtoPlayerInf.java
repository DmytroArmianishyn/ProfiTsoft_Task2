package com.example.lab.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoPlayerInf{
    @NotBlank(message = "Name have errors")
    private String name;
    @NotBlank(message = "Position have errors")
    private String position;
    @Positive(message = "age must be positive number")
    private int age;
    @NotBlank(message = "Team name have errors")
    private String teamName;

    public DtoPlayerInf(String name, String position, int age, String teamName) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "DtoPlayerInf{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", age=" + age +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
