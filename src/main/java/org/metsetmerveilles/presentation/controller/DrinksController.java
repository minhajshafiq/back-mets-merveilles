package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.model.Drinks;
import org.metsetmerveilles.domain.service.drinks.IDrinksService;
import org.metsetmerveilles.presentation.dto.DrinksDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/drinks")
public class DrinksController {

    private final IDrinksService drinksService;

    public DrinksController(IDrinksService drinksService) {
        this.drinksService = drinksService;
    }

    @GetMapping
    public List<DrinksDto> list() {
        return drinksService.getAllDrinks().stream()
                .map(DrinksDto::fromDomain)
                .toList();
    }

    @PostMapping
    public DrinksDto create(@RequestBody DrinksDto drinksDto) {
        Drinks drinks = new Drinks(
                drinksDto.id(),
                drinksDto.name(),
                drinksDto.description(),
                drinksDto.price(),
                Optional.ofNullable(drinksDto.menuId()));

        Drinks createdDrinks = drinksService.createDrinks(drinks);

        return DrinksDto.fromDomain(createdDrinks);
    }

    @GetMapping("/{id}")
    public DrinksDto get(@PathVariable Long id) {
        Drinks drinks = drinksService.getDrinksById(id);

        return DrinksDto.fromDomain(drinks);
    }

    @PutMapping("/{id}")
    public DrinksDto update(@PathVariable Long id, @RequestBody DrinksDto drinksDto) {
        Drinks drinks = new Drinks(
                id,
                drinksDto.name(),
                drinksDto.description(),
                drinksDto.price(),
                Optional.ofNullable(drinksDto.menuId()));

        Drinks updatedDrinks = drinksService.updateDrinks(drinks);

        return DrinksDto.fromDomain(updatedDrinks);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        drinksService.deleteDrinks(id);
    }
}
