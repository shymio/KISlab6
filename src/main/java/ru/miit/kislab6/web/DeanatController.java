package ru.miit.kislab6.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.miit.kislab6.model.Deanat;
import ru.miit.kislab6.service.DeanatService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/deanats")
public class DeanatController {

    private final DeanatService deanatService;

    public DeanatController(DeanatService deanatService) {
        this.deanatService = deanatService;
    }

    @GetMapping
    public List<Deanat> getAll() {
        return deanatService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deanat> getById(@PathVariable Integer id) {
        Deanat d = deanatService.findById(id);
        return ResponseEntity.ok(d);
    }

    @PostMapping
    public ResponseEntity<Deanat> create(@RequestBody Deanat deanat) {
        Deanat created = deanatService.create(deanat);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deanat> update(@PathVariable Integer id, @RequestBody Deanat deanat) {
        Deanat updated = deanatService.update(id, deanat);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        deanatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

