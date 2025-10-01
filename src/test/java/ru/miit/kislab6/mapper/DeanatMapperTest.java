package ru.miit.kislab6.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.miit.kislab6.model.Deanat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // откатывает транзакцию после каждого теста
class DeanatMapperTest {

    @Autowired
    private DeanatMapper deanatMapper;

    @Test
    void insertAndSelectById() {
        Deanat d = new Deanat(null, "Тестовый деканат", "Тестовая 1", "+7-111-222-33-44");
        int rows = deanatMapper.insertDeanat(d);
        assertTrue(rows > 0, "Ожидаем, что insert вернёт >0");
        assertNotNull(d.getId(), "id должен быть установлен (useGeneratedKeys)");
        Deanat fetched = deanatMapper.selectDeanatById(d.getId());
        assertNotNull(fetched);
        assertEquals("Тестовый деканат", fetched.getName());
    }

    @Test
    void selectAllIncludesInserted() {
        Deanat d = new Deanat(null, "All test", "addr", "111");
        deanatMapper.insertDeanat(d);

        List<Deanat> all = deanatMapper.selectAllDeanats();
        assertNotNull(all);
        assertFalse(all.isEmpty());
        assertTrue(all.stream().anyMatch(x -> x.getId().equals(d.getId())),
                "Список всех должен содержать вставленную запись");
    }

    @Test
    void updateWorks() {
        Deanat d = new Deanat(null, "Для обновления", "Старый адрес", "000");
        deanatMapper.insertDeanat(d);
        Integer id = d.getId();
        assertNotNull(id);

        d.setName("Обновлённый деканат");
        d.setAddress("Новый адрес");
        int rows = deanatMapper.updateDeanat(d);
        assertEquals(1, rows, "Ожидаем 1 обновлённую строку");

        Deanat updated = deanatMapper.selectDeanatById(id);
        assertNotNull(updated);
        assertEquals("Обновлённый деканат", updated.getName());
        assertEquals("Новый адрес", updated.getAddress());
    }

    @Test
    void deleteWorks() {
        Deanat d = new Deanat(null, "Для удаления", "Адрес", "000");
        deanatMapper.insertDeanat(d);
        Integer id = d.getId();
        assertNotNull(id);

        int rows = deanatMapper.deleteDeanatById(id);
        // в зависимости от реализации mapper может вернуть 1 или 0 — проверим что запись исчезла
        Deanat deleted = deanatMapper.selectDeanatById(id);
        assertNull(deleted, "Запись должна быть удалена");
    }
}
