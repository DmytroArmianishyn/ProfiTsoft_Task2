package com.example.lab.models.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Class for statistics on how many objects from the json file have been successfully processed
 */
@Getter
@Setter
public class Stat {

    int accepted;
    int failed;
}
