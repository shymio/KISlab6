package ru.miit.kislab6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deanat {
    private Integer id;
    private String name;
    private String address;
    private String phone;
}