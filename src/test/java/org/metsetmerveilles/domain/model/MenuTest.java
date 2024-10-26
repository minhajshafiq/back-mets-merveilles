package org.metsetmerveilles.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    @Test
    void shouldNotBuildWithInvalidLabel() {
        assertThrows(IllegalArgumentException.class, () -> new Menu(null));
        assertThrows(IllegalArgumentException.class, () -> new Menu(""));
    }

    @Test
    void shouldBuildWithValidLabel() {
        Menu menu = new Menu("Menu");
        assertEquals("Menu", menu.label());
    }
}