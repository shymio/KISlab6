package ru.miit.kislab6.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.miit.kislab6.model.Settlement;

import java.util.List;

@Mapper
public interface SettlementMapper {
    int insertSettlement(Settlement settlement);
    int updateSettlement(Settlement settlement);
    int deleteSettlementById(Integer id);
    Settlement selectSettlementById(Integer id);
    List<Settlement> selectAllSettlements();
}
