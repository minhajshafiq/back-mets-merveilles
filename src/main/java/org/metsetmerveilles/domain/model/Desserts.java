package org.metsetmerveilles.domain.model;

import java.util.Optional;

public record Desserts(Long id, String name, String description, double price, Optional<Menu> menu) {
}
