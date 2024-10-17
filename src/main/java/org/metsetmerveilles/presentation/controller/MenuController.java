package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.service.IMenuService;
import org.metsetmerveilles.presentation.dto.MenuDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .map(menu -> new MenuDto(menu.label()))
                .toList();
    }
}
