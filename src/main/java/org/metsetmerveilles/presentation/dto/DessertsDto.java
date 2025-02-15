package org.metsetmerveilles.presentation.dto;

import org.metsetmerveilles.domain.model.Desserts;

public record DessertsDto(
        Long id,
        String name,
        String description,
        double price,
        String imageUrl,
        Long menuId
) {

    public static DessertsDto fromDomain(Desserts desserts) {
        return new DessertsDto(
                desserts.id(),
                desserts.name(),
                desserts.description(),
                desserts.price(),
                desserts.imageUrl(),
                desserts.menuId().orElse(null));
    }
}
