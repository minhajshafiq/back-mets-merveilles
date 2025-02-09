package org.metsetmerveilles.presentation.dto;

import org.metsetmerveilles.domain.model.Menu;

import java.util.List;

public record MenuDto(
        Long id,
        String name,
        String description,
        double price,
        List<Long> starterIds,
        List<Long> mainCourseIds,
        List<Long> dessertsIds,
        List<Long> drinksIds
) {
    public static MenuDto fromDomain(Menu menu) {
        return new MenuDto(
                menu.id(),
                menu.name(),
                menu.description(),
                menu.price(),
                menu.starterIds().orElse(List.of()), // Ajout des starters liés
                menu.mainCourseIds().orElse(List.of()), // Ajout des main courses liés
                menu.dessertsIds().orElse(List.of()), // Ajout des desserts liés
                menu.drinksIds().orElse(List.of()) // Ajout des drinks liés
        );
    }
}
