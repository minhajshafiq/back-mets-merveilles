package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.model.Desserts;
import org.metsetmerveilles.domain.service.desserts.IDessertsService;
import org.metsetmerveilles.presentation.dto.DessertsDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/desserts")
public class DessertsController {

    private final IDessertsService dessertsService;

    public DessertsController(IDessertsService dessertsService) {
        this.dessertsService = dessertsService;
    }

    @GetMapping
    public List<DessertsDto> list() {
        return dessertsService.getAllDesserts().stream()
                .map(DessertsDto::fromDomain)
                .toList();
    }

    @PostMapping
    public DessertsDto create(@RequestBody DessertsDto dessertsDto) {
        Desserts desserts = new Desserts(
                dessertsDto.id(),
                dessertsDto.name(),
                dessertsDto.description(),
                dessertsDto.price(),
                Optional.ofNullable(dessertsDto.menuId()));

                Desserts createdDesserts = dessertsService.createDesserts(desserts);

        return DessertsDto.fromDomain(createdDesserts);
    }

    @GetMapping("/{id}")
    public DessertsDto get(@PathVariable Long id) {
        Desserts desserts = dessertsService.getDessertsById(id);
        return DessertsDto.fromDomain(desserts);
    }

    @PutMapping("/{id}")
    public DessertsDto update(@PathVariable Long id, @RequestBody DessertsDto dessertsDto) {
        Desserts desserts = new Desserts(
                id,
                dessertsDto.name(),
                dessertsDto.description(),
                dessertsDto.price(),
                Optional.ofNullable(dessertsDto.menuId()));

        Desserts updatedDesserts = dessertsService.updateDesserts(desserts);

        return DessertsDto.fromDomain(updatedDesserts);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dessertsService.deleteDesserts(id);
    }
}
