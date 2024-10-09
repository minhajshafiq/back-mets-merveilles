package org.metsetmerveilles.presentation.controller;

import java.util.List;
import org.metsetmerveilles.presentation.dto.MenuDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @GetMapping
    public List<MenuDto> list() {
        return List.of(new MenuDto("MonMenu" ));
    }
}
