package ru.miit.kislab6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Settlement {
    private Integer id;
    private String name;        // название
    private String type;        // тип: город, деревня и т.д.
    private String postalIndex; // индекс
    private Integer population; // население
    private String phone;       // телефон
    private Double area;        // площадь
    private String oktmo;       // код ОКТМО
    private Double density;     // плотность населения
}
