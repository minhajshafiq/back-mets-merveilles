package org.metsetmerveilles.domain.model;

import java.util.Optional;

public record Drinks(Long id, String name, String description, double price, Optional<Menu>menu) {
}
