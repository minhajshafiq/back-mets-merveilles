package org.metsetmerveilles.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public record Drinks(Long id, String name, String description, double price, Optional <Long> menuId) {
    public Drinks {
        // Validation pour le champ `name`
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        // Validation pour le champ `description`
        if (StringUtils.isBlank(description)) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }

        // Validation pour le champ `price`
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }
}
