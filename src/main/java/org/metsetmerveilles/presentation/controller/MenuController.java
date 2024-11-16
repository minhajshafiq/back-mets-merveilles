package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.model.Menu;
import org.metsetmerveilles.domain.service.menu.IMenuService;
import org.metsetmerveilles.presentation.dto.MenuDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
        Menu createdMenu = menuService.createMenu(
                menuDto.name(),
                menuDto.description(),
                menuDto.price()
        );

        return MenuDto.fromDomain(createdMenu);
    }
}
