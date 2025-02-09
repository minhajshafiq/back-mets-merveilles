package org.metsetmerveilles.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

public record Menu(
        Long id,
        String name,
        String description,
        double price,
        Optional<List<Long>> starterIds,
        Optional<List<Long>> mainCourseIds,
        Optional<List<Long>> dessertsIds,
        Optional<List<Long>> drinksIds) {
    public Menu {
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

        // Validation pour ID
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }
    }
}
