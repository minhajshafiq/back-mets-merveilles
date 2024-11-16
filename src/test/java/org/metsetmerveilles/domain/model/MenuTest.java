package org.metsetmerveilles.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    @Test
    void shouldNotBuildWithInvalidLabel() {
//        assertThrows(IllegalArgumentException.class, () -> new Menu(null));
//        assertThrows(IllegalArgumentException.class, () -> new Menu(""));
    }

    @Test
    void shouldBuildWithValidLabel() {
        Menu menu = new Menu(1L, "Menu", "description", 1.0);
        assertEquals("Menu", menu.name());
    }
}