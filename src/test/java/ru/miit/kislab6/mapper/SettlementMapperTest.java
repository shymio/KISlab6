package ru.miit.kislab6.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.miit.kislab6.model.Settlement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SettlementMapperTest {

    @Autowired
    private SettlementMapper settlementMapper;

    @Test
    void insertAndSelectById() {
        Settlement s = new Settlement(null, "Тестовое село", "деревня", "125080", 100, "+7(999)111-22-33", 2.0, "12345678901", 50.0);
        int rows = settlementMapper.insertSettlement(s);
        assertTrue(rows > 0);
        assertNotNull(s.getId());

        Settlement fetched = settlementMapper.selectSettlementById(s.getId());
        assertNotNull(fetched);
        assertEquals("Тестовое село", fetched.getName());
    }

    @Test
    void updateWorks() {
        Settlement s = new Settlement(null, "Старое село", "деревня", "123456", 50, "+7(999)000-11-22", 1.0, "12345678901", 50.0);
        settlementMapper.insertSettlement(s);
        Integer id = s.getId();
        assertNotNull(id);

        s.setName("Новое село");
        s.setPopulation(200);
        settlementMapper.updateSettlement(s);

        Settlement updated = settlementMapper.selectSettlementById(id);
        assertEquals("Новое село", updated.getName());
        assertEquals(200, updated.getPopulation());
    }

    @Test
    void deleteWorks() {
        Settlement s = new Settlement(null, "Удаляемое село", "село", "654321", 10, "+7(999)000-22-33", 1.0, "12345678901", 10.0);
        settlementMapper.insertSettlement(s);
        Integer id = s.getId();
        settlementMapper.deleteSettlementById(id);

        Settlement deleted = settlementMapper.selectSettlementById(id);
        assertNull(deleted);
    }
}
