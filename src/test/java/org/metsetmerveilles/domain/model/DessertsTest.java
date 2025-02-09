package org.metsetmerveilles.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DessertsTest {

    @Test
    void shouldNotBuildWithInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> new Desserts(1L, null, "description", 1.0, Optional.empty()));
        assertThrows(IllegalArgumentException.class, () -> new Desserts(1L, "", "description", 1.0, Optional.empty()));
    }

    @Test
    void shouldBuildWithValidName() {
        Desserts desserts = new Desserts(1L, "Chocolate Cake", "description", 1.0, Optional.empty());
        assertEquals("Chocolate Cake", desserts.name());
    }

    @Test
    void shouldNotBuildWithInvalidDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Desserts(1L, "Chocolate Cake", null, 1.0, Optional.empty()));
        assertThrows(IllegalArgumentException.class, () -> new Desserts(1L, "Chocolate Cake", "", 1.0, Optional.empty()));
    }

    @Test
    void shouldBuildWithValidDescription() {
        Desserts desserts = new Desserts(1L, "Chocolate Cake", "Sweet dessert", 1.0, Optional.empty());
        assertEquals("Sweet dessert", desserts.description());
    }

    @Test
    void shouldNotBuildWithInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Desserts(1L, "Chocolate Cake", "description", -1.0, Optional.empty()));
        assertThrows(IllegalArgumentException.class, () -> new Desserts(1L, "Chocolate Cake", "description", 0.0, Optional.empty()));
    }

    @Test
    void shouldBuildWithValidPrice() {
        Desserts desserts = new Desserts(1L, "Chocolate Cake", "description", 3.5, Optional.empty());
        assertEquals(3.5, desserts.price());
    }
}