package ru.miit.kislab6.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.miit.kislab6.model.Deanat;

import java.util.List;

@Mapper
public interface DeanatMapper {
    int insertDeanat(Deanat deanat);          // id установится автоматически (useGeneratedKeys в xml)
    int updateDeanat(Deanat deanat);
    int deleteDeanatById(Integer id);
    Deanat selectDeanatById(Integer id);
    List<Deanat> selectAllDeanats();
}
