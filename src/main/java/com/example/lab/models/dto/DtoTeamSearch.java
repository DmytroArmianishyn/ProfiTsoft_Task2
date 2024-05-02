package com.example.lab.models.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * A class that helps to search for a specific number of players and count pages
 */
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
