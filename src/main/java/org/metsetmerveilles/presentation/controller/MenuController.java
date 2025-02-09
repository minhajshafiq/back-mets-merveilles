package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.model.Menu;
import org.metsetmerveilles.domain.service.menu.IMenuService;
import org.metsetmerveilles.presentation.dto.MenuDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/menu")
public class MenuController {

    private final IMenuService menuService;

    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<MenuDto> list() {
        return menuService.getAllMenus().stream()
                .map(MenuDto::fromDomain)
                .toList();
    }

    @PostMapping
    public MenuDto create(@RequestBody MenuDto menuDto) {
        Menu menu = new Menu(
                menuDto.id(),
                menuDto.name(),
                menuDto.description(),
                menuDto.price(),
                Optional.ofNullable(menuDto.starterIds()),
                Optional.ofNullable(menuDto.mainCourseIds()),
                Optional.ofNullable(menuDto.dessertsIds()),
                Optional.ofNullable(menuDto.drinksIds())
        );

        Menu createdMenu = menuService.createMenu(
                menu
        );

        return MenuDto.fromDomain(createdMenu);
    }

    @GetMapping("/{id}")
    public MenuDto get(@PathVariable Long id) {
        Menu menu = menuService.getMenuById(id);
        return MenuDto.fromDomain(menu);
    }

    @PutMapping("/{id}")
    public MenuDto update(@PathVariable Long id, @RequestBody MenuDto menuDto) {
        Menu menu = new Menu(
                id,
                menuDto.name(),
                menuDto.description(),
                menuDto.price(),
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
