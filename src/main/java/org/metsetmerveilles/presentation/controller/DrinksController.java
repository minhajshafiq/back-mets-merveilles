package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.model.Drinks;
import org.metsetmerveilles.domain.service.FirebaseStorageService;
import org.metsetmerveilles.domain.service.drinks.IDrinksService;
import org.metsetmerveilles.presentation.dto.DrinksDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://front-mets-merveilles.vercel.app/")
@RequestMapping("/drinks")
public class DrinksController {

    private final IDrinksService drinksService;
    private final FirebaseStorageService firebaseStorageService;

    public DrinksController(IDrinksService drinksService, FirebaseStorageService firebaseStorageService) {
        this.drinksService = drinksService;
        this.firebaseStorageService = firebaseStorageService;
    }

    @GetMapping
    public List<DrinksDto> list() {
        return drinksService.getAllDrinks().stream()
                .map(DrinksDto::fromDomain)
                .toList();
    }

    @PostMapping(consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public DrinksDto create(@RequestPart("data") DrinksDto drinksDto,
                            @RequestPart("file") MultipartFile file) throws IOException {

        // Uploader l'image vers Firebase Storage
        String imageUrl = firebaseStorageService.uploadImage(file, "drinks");

        // Créer l'objet Drink
        Drinks drinks = new Drinks(
                drinksDto.id(),
                drinksDto.name(),
                drinksDto.description(),
                drinksDto.price(),
                imageUrl,
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
                drinksDto.imageUrl(),
                Optional.ofNullable(drinksDto.menuId()));

        Drinks updatedDrinks = drinksService.updateDrinks(drinks);

        return DrinksDto.fromDomain(updatedDrinks);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        drinksService.deleteDrinks(id);
    }
}
