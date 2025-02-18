package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.model.Menu;
import org.metsetmerveilles.domain.service.FirebaseStorageService;
import org.metsetmerveilles.domain.service.menu.IMenuService;
import org.metsetmerveilles.presentation.dto.MenuDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://front-mets-merveilles.vercel.app/")
@RequestMapping("/menu")
public class MenuController {

    private final IMenuService menuService;
    private final FirebaseStorageService firebaseStorageService;

    public MenuController(IMenuService menuService, FirebaseStorageService firebaseStorageService) {
        this.menuService = menuService;
        this.firebaseStorageService = firebaseStorageService;
    }

    @GetMapping
    public List<MenuDto> list() {
        return menuService.getAllMenus().stream()
                .map(MenuDto::fromDomain)
                .toList();
    }

    @PostMapping(consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public MenuDto create(@RequestPart("data") MenuDto menuDto,
                          @RequestPart("file") MultipartFile file) throws IOException {

        // Uploader l'image vers Firebase Storage
        String imageUrl = firebaseStorageService.uploadImage(file, "menus");

        // Créer l'objet Menu
        Menu menu = Menu.Create(
                menuDto.id(),
                menuDto.name(),
                menuDto.description(),
                menuDto.price(),
                imageUrl,
                Optional.ofNullable(menuDto.starterIds()),
                Optional.ofNullable(menuDto.mainCourseIds()),
                Optional.ofNullable(menuDto.dessertsIds()),
                Optional.ofNullable(menuDto.drinksIds())
        );

        Menu createdMenu = menuService.createMenu(menu);

        return MenuDto.fromDomain(createdMenu);
    }


    @GetMapping("/{id}")
    public MenuDto get(@PathVariable Long id) {
        Menu menu = menuService.getMenuById(id);
        return MenuDto.fromDomain(menu);
    }

    @PutMapping("/{id}")
    public MenuDto update(@PathVariable Long id, @RequestBody MenuDto menuDto) {
        Menu menu = Menu.Create(
                id,
                menuDto.name(),
                menuDto.description(),
                menuDto.price(),
                menuDto.imageUrl(),
                Optional.ofNullable(menuDto.starterIds()),
                Optional.ofNullable(menuDto.mainCourseIds()),
                Optional.ofNullable(menuDto.dessertsIds()),
                Optional.ofNullable(menuDto.drinksIds())
        );

        Menu updatedMenu = menuService.updateMenu(menu);

        return MenuDto.fromDomain(updatedMenu);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        menuService.deleteMenu(id);
    }
}
