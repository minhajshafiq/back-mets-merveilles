package org.metsetmerveilles.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Menu {
    private final Long id;
    private final String name;
    private final String description;
    private final Price price;
    private final String imageUrl;
    private final Optional<List<Long>> starterIds;
    private final Optional<List<Long>> mainCourseIds;
    private final Optional<List<Long>> dessertsIds;
    private final Optional<List<Long>> drinksIds;

    private Menu(Long id, String name, String description, Price price, String imageUrl, Optional<List<Long>> starterIds, Optional<List<Long>> mainCourseIds, Optional<List<Long>> dessertsIds, Optional<List<Long>> drinksIds) {
        // Validation pour le champ `name`
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        // Validation pour le champ `description`
        if (StringUtils.isBlank(description)) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }

        // Validation pour ID
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number");
        }
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.starterIds = starterIds;
        this.mainCourseIds = mainCourseIds;
        this.dessertsIds = dessertsIds;
        this.drinksIds = drinksIds;
    }

    public static Menu Create(Long id, String name, String description, double price, String imageUrl, Optional<List<Long>> starterIds, Optional<List<Long>> mainCourseIds, Optional<List<Long>> dessertsIds, Optional<List<Long>> drinksIds) {
        return new Menu(id, name, description, new Price(price), imageUrl, starterIds, mainCourseIds, dessertsIds, drinksIds);
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public Price price() {
        return price;
    }

    public String imageUrl() {
        return imageUrl;
    }

    public Optional<List<Long>> starterIds() {
        return starterIds;
    }

    public Optional<List<Long>> mainCourseIds() {
        return mainCourseIds;
    }

    public Optional<List<Long>> dessertsIds() {
        return dessertsIds;
    }

    public Optional<List<Long>> drinksIds() {
        return drinksIds;
    }

}
