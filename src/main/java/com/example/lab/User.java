package com.example.lab;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int id;
    private String name;
    private int age;
    private String email;
}
