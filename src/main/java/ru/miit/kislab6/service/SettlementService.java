package ru.miit.kislab6.service;

import ru.miit.kislab6.model.Settlement;

import java.util.List;

public interface SettlementService {
    List<Settlement> findAll();
    Settlement findById(Integer id);
    Settlement create(Settlement settlement);
    Settlement update(Integer id, Settlement settlement);
    void delete(Integer id);
}
