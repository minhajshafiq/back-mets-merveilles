package org.metsetmerveilles.domain.model;

import org.apache.commons.lang3.StringUtils;

public record Menu(Long id, String name, String description, double price) {
    public Menu {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Label cannot be null or empty");
        }
    }
}
