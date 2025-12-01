package ru.miit.kislab6.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.miit.kislab6.model.Settlement;
import ru.miit.kislab6.service.SettlementService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/settlements")
@Tag(name = "Settlements", description = "Операции с населёнными пунктами")
public class SettlementController {

    private final SettlementService settlementService;

    public SettlementController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех населённых пунктов")
    public List<Settlement> getAll() {
        return settlementService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить населённый пункт по id")
    public ResponseEntity<Settlement> getById(
            @Parameter(description = "ID населённого пункта", example = "1", required = true)
            @PathVariable Integer id) {
        Settlement s = settlementService.findById(id);
        return ResponseEntity.ok(s);
    }

    @PostMapping
    @Operation(summary = "Создать населённый пункт")
    public ResponseEntity<Settlement> create(@RequestBody Settlement settlement) {
        System.out.println(">>> RECEIVED SETTLEMENT = " + settlement);
        Settlement created = settlementService.create(settlement);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить населённый пункт")
    public ResponseEntity<Settlement> update(
            @Parameter(description = "ID населённого пункта", required = true) @PathVariable Integer id,
            @RequestBody Settlement settlement) {
        Settlement updated = settlementService.update(id, settlement);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить населённый пункт")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID населённого пункта", required = true) @PathVariable Integer id) {
        settlementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
