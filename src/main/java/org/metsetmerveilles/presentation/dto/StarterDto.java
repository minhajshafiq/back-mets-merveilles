package org.metsetmerveilles.presentation.dto;

import org.metsetmerveilles.domain.model.Starter;

public record StarterDto(Long id, String name, String description, double price, Long menuId) {

    public static StarterDto fromDomain(Starter starter) {
        return new StarterDto(starter.id(), starter.name(), starter.description(), starter.price(), starter.menuId().orElse(null));
    }
}
