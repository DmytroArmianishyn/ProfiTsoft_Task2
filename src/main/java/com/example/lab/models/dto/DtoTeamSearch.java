package com.example.lab.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoTeamSearch {
    private Long id;
    private String name;
    private String city;
    private int from;
    private int size;
    private String category;
}
