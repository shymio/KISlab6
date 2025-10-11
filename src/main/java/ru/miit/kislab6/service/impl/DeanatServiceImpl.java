package ru.miit.kislab6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.miit.kislab6.mapper.DeanatMapper;
import ru.miit.kislab6.model.Deanat;
import ru.miit.kislab6.service.DeanatService;
import ru.miit.kislab6.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeanatServiceImpl implements DeanatService {

    private final DeanatMapper deanatMapper;

    @Override
    public List<Deanat> findAll() {
        return deanatMapper.selectAllDeanats();
    }

    @Override
    public Deanat findById(Integer id) {
        Deanat d = deanatMapper.selectDeanatById(id);
        if (d == null) {
            throw new ResourceNotFoundException("Deanat with id " + id + " not found");
        }
        return d;
    }

    @Override
    public Deanat create(Deanat deanat) {
        // MyBatis useGeneratedKeys выставит id в объекте deanat
        deanatMapper.insertDeanat(deanat);
        return deanat;
    }

    @Override
    public Deanat update(Integer id, Deanat deanat) {
        // проверим существование
        Deanat existing = deanatMapper.selectDeanatById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Deanat with id " + id + " not found");
        }
        deanat.setId(id);
        deanatMapper.updateDeanat(deanat);
        return deanatMapper.selectDeanatById(id);
    }

    @Override
    public void delete(Integer id) {
        Deanat existing = deanatMapper.selectDeanatById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Deanat with id " + id + " not found");
        }
        deanatMapper.deleteDeanatById(id);
    }
}

