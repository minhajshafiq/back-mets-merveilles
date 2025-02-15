package org.metsetmerveilles.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Test
    void shouldNotBuildWithInvalidLabel() {
        // Vérifie que l'exception IllegalArgumentException est lancée pour un nom null
        assertThrows(IllegalArgumentException.class, () -> Menu.Create(1L, null, "description", 1.0,
                 "imageUrl",Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));

        // Vérifie que l'exception IllegalArgumentException est lancée pour un nom vide
        assertThrows(IllegalArgumentException.class, () -> Menu.Create(1L, "", "description", 1.0,
                "imageUrl",Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
    }

    @Test
    void shouldBuildWithValidLabel() {
        // Crée un menu valide en passant des Optional.empty() pour les listes
        Menu menu = Menu.Create(1L, "Menu", "description", 1.0,
                "imageUrl",Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());

        // Vérifie que le nom est correctement assigné
        assertEquals("Menu", menu.name());
    }
}