package com.example.lab.models.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * A class that has brief information about the class Team
 */
@Getter
@Setter
public class DtoTeamInf {


    private String name;

    private String city;

    public DtoTeamInf(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public DtoTeamInf() {

    }

    @Override
    public String toString() {
        return "DtoTeamInf{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
