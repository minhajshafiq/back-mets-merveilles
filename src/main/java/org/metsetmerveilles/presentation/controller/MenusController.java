package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.service.desserts.DessertsService;
import org.metsetmerveilles.domain.service.drinks.DrinksService;
import org.metsetmerveilles.domain.service.maincourse.MainCourseService;
import org.metsetmerveilles.domain.service.starter.StarterService;
import org.metsetmerveilles.presentation.dto.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://front-mets-merveilles.vercel.app")
@RequestMapping("/menus")
public class MenusController {

    private final StarterService starterService;
    private final MainCourseService mainCourseService;
    private final DessertsService dessertsService;
    private final DrinksService drinksService;

    public MenusController( StarterService starterService, MainCourseService mainCourseService, DessertsService dessertsService, DrinksService drinksService) {
        this.starterService = starterService;
        this.mainCourseService = mainCourseService;
        this.dessertsService = dessertsService;
        this.drinksService = drinksService;
    }

    @GetMapping
    public MenusDto list() {
        List<StarterDto> starters = starterService.getAllStarters().stream().map(StarterDto::fromDomain).toList();
        List<MainCourseDto> mainCourses = mainCourseService.getAllMainCourses().stream().map(MainCourseDto::fromDomain).toList();
        List<DessertsDto> desserts = dessertsService.getAllDesserts().stream().map(DessertsDto::fromDomain).toList();
        List<DrinksDto> drinks = drinksService.getAllDrinks().stream().map(DrinksDto::fromDomain).toList();

        return new MenusDto(starters, mainCourses, desserts, drinks);
    }
}
