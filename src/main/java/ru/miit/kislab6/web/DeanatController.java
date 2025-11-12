package ru.miit.kislab6.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.miit.kislab6.model.Deanat;
import ru.miit.kislab6.service.DeanatService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/deanats")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Deanats", description = "Операции с деканатами")
public class DeanatController {

    private final DeanatService deanatService;

    public DeanatController(DeanatService deanatService) {
        this.deanatService = deanatService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех деканатов")
    public List<Deanat> getAll() {
        return deanatService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить деканат по id")
    public ResponseEntity<Deanat> getById(
            @Parameter(description = "ID деканата", example = "1", required = true)
            @PathVariable Integer id) {
        Deanat d = deanatService.findById(id);
        return ResponseEntity.ok(d);
    }

    @PostMapping
    @Operation(summary = "Создать деканат")
    public ResponseEntity<Deanat> create(@RequestBody Deanat deanat) {
        Deanat created = deanatService.create(deanat);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить деканат")
    public ResponseEntity<Deanat> update(
            @Parameter(description = "ID деканата", required = true) @PathVariable Integer id,
            @RequestBody Deanat deanat) {
        Deanat updated = deanatService.update(id, deanat);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить деканат")
    public ResponseEntity<Void> delete(@Parameter(description = "ID деканата", required = true) @PathVariable Integer id) {
        deanatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}