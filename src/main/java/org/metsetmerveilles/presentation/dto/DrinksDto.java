package org.metsetmerveilles.presentation.dto;

import org.metsetmerveilles.domain.model.Drinks;

public record DrinksDto(
        Long id,
        String name,
        String description,
        double price,
        String imageUrl,
        Long menuId
) {

    public static DrinksDto fromDomain(Drinks drinks) {
        return new DrinksDto(
                drinks.id(),
                drinks.name(),
                drinks.description(),
                drinks.price(),
                drinks.imageUrl(),
                drinks.menuId()
                        .orElse(null));
    }
}
