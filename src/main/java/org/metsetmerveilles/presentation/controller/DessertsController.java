package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.model.Desserts;
import org.metsetmerveilles.domain.service.FirebaseStorageService;
import org.metsetmerveilles.domain.service.desserts.IDessertsService;
import org.metsetmerveilles.presentation.dto.DessertsDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://front-mets-merveilles.vercel.app/")
@RequestMapping("/desserts")
public class DessertsController {

    private final IDessertsService dessertsService;
    private final FirebaseStorageService firebaseStorageService;

    public DessertsController(IDessertsService dessertsService, FirebaseStorageService firebaseStorageService) {
        this.dessertsService = dessertsService;
        this.firebaseStorageService = firebaseStorageService;
    }


    @GetMapping
    public List<DessertsDto> list() {
        return dessertsService.getAllDesserts().stream()
                .map(DessertsDto::fromDomain)
                .toList();
    }

    @PostMapping(consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public DessertsDto create(@RequestPart("data") DessertsDto dessertsDto,
                              @RequestPart("file") MultipartFile file) throws IOException {

        // Uploader l'image vers Firebase Storage
        String imageUrl = firebaseStorageService.uploadImage(file, "desserts");

        // Créer l'objet Dessert
        Desserts desserts = new Desserts(
                null,
                dessertsDto.name(),
                dessertsDto.description(),
                dessertsDto.price(),
                imageUrl,
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
                dessertsDto.imageUrl(),
                Optional.ofNullable(dessertsDto.menuId()));

        Desserts updatedDesserts = dessertsService.updateDesserts(desserts);

        return DessertsDto.fromDomain(updatedDesserts);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dessertsService.deleteDesserts(id);
    }
}
