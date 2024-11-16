package org.metsetmerveilles.presentation.dto;

import org.metsetmerveilles.domain.model.Menu;

public record MenuDto(Long id, String name, String description, double price) {

    public static MenuDto fromDomain(Menu menu) {
        return new MenuDto(menu.id(), menu.name(), menu.description(), menu.price());
    }
}
