package ru.miit.kislab6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.miit.kislab6.mapper.SettlementMapper;
import ru.miit.kislab6.model.Settlement;
import ru.miit.kislab6.service.SettlementService;
import ru.miit.kislab6.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementServiceImpl implements SettlementService {

    private final SettlementMapper settlementMapper;

    @Override
    public List<Settlement> findAll() {
        return settlementMapper.selectAllSettlements();
    }

    @Override
    public Settlement findById(Integer id) {
        Settlement s = settlementMapper.selectSettlementById(id);
        if (s == null) {
            throw new ResourceNotFoundException("Settlement with id " + id + " not found");
        }
        return s;
    }

    @Override
    public Settlement create(Settlement settlement) {
        settlementMapper.insertSettlement(settlement);
        return settlement;
    }

    @Override
    public Settlement update(Integer id, Settlement settlement) {
        Settlement existing = settlementMapper.selectSettlementById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Settlement with id " + id + " not found");
        }
        settlement.setId(id);
        settlementMapper.updateSettlement(settlement);
        return settlementMapper.selectSettlementById(id);
    }

    @Override
    public void delete(Integer id) {
        Settlement existing = settlementMapper.selectSettlementById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Settlement with id " + id + " not found");
        }
        settlementMapper.deleteSettlementById(id);
    }
}
