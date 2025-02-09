package org.metsetmerveilles.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DrinksTest {


    @Test
    void shouldNotBuildWithInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> new Drinks(1L, null, "description", 1.0, Optional.empty()));
        assertThrows(IllegalArgumentException.class, () -> new Drinks(1L, "", "description", 1.0, Optional.empty()));
    }

    @Test
    void shouldBuildWithValidName() {
        Drinks drinks = new Drinks(1L, "Coca-Cola", "description", 1.0, Optional.empty());
        assertEquals("Coca-Cola", drinks.name());
    }

    @Test
    void shouldNotBuildWithInvalidDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Drinks(1L, "Coca-Cola", null, 1.0, Optional.empty()));
        assertThrows(IllegalArgumentException.class, () -> new Drinks(1L, "Coca-Cola", "", 1.0, Optional.empty()));
    }

    @Test
    void shouldBuildWithValidDescription() {
        Drinks drinks = new Drinks(1L, "Coca-Cola", "Refreshing drink", 1.0, Optional.empty());
        assertEquals("Refreshing drink", drinks.description());
    }

    @Test
    void shouldNotBuildWithInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Drinks(1L, "Coca-Cola", "description", -1.0, Optional.empty()));
        assertThrows(IllegalArgumentException.class, () -> new Drinks(1L, "Coca-Cola", "description", 0.0, Optional.empty()));
    }

    @Test
    void shouldBuildWithValidPrice() {
        Drinks drinks = new Drinks(1L, "Coca-Cola", "description", 2.5, Optional.empty());
        assertEquals(2.5, drinks.price());
    }
}