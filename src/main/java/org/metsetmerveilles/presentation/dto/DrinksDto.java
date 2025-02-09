package org.metsetmerveilles.presentation.dto;

import org.metsetmerveilles.domain.model.Drinks;

public record DrinksDto(Long id, String name, String description, Double price, Long menuId) {

    public static DrinksDto fromDomain(Drinks drinks) {
        return new DrinksDto(drinks.id(),
                drinks.name(),
                drinks.description(),
                drinks.price(),
                drinks.menuId()
                        .orElse(null));
    }
}
