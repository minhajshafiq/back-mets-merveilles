package org.metsetmerveilles.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StarterTest {

    @Test
    void shouldNotBuildWithInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> new Starter(1L, null, "description", 1.0, "imageUrl", Optional.empty()));
        assertThrows(IllegalArgumentException.class, () -> new Starter(1L, "", "description", 1.0, "imageUrl", Optional.empty()));
    }

    @Test
    void shouldBuildWithValidName() {
        Starter starter = new Starter(1L, "Soup", "description", 1.0, "imageUrl", Optional.empty());
        assertEquals("Soup", starter.name());
    }

    @Test
    void shouldNotBuildWithInvalidDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Starter(1L, "Soup", null, 1.0, "imageUrl", Optional.empty()));
        assertThrows(IllegalArgumentException.class, () -> new Starter(1L, "Soup", "", 1.0, "imageUrl", Optional.empty()));
    }

    @Test
    void shouldBuildWithValidDescription() {
        Starter starter = new Starter(1L, "Soup", "Warm starter", 1.0, "imageUrl", Optional.empty());
        assertEquals("Warm starter", starter.description());
    }

    @Test
    void shouldNotBuildWithInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Starter(1L, "Soup", "description", -1.0, "imageUrl", Optional.empty()));
        assertThrows(IllegalArgumentException.class, () -> new Starter(1L, "Soup", "description", 0.0, "imageUrl", Optional.empty()));
    }

    @Test
    void shouldBuildWithValidPrice() {
        Starter starter = new Starter(1L, "Soup", "description", 5.0, "imageUrl", Optional.empty());
        assertEquals(5.0, starter.price());
    }
}