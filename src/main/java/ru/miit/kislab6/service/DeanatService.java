package ru.miit.kislab6.service;

import ru.miit.kislab6.model.Deanat;

import java.util.List;

public interface DeanatService {
    List<Deanat> findAll();
    Deanat findById(Integer id);
    Deanat create(Deanat deanat);
    Deanat update(Integer id, Deanat deanat);
    void delete(Integer id);
}

