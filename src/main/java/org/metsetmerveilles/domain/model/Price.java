package org.metsetmerveilles.domain.model;

public record Price(double value) {
    public Price {
        if (value <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }
}
