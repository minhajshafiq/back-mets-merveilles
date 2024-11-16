package org.metsetmerveilles.domain.model;

import java.util.Optional;

public record MainCourse(Long id, String name, String description, double price, Optional <Long> menuId) {
}
