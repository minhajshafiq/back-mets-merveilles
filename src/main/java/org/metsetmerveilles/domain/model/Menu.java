package org.metsetmerveilles.domain.model;

import org.apache.commons.lang3.StringUtils;

public record Menu(String label) {
    public Menu {
        if (StringUtils.isBlank(label)) {
            throw new IllegalArgumentException("Label cannot be null or empty");
        }
    }
}
