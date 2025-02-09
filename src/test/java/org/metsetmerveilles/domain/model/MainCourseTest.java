package org.metsetmerveilles.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MainCourseTest {

    @Test
    void shouldNotBuildWithInvalidName() {
        // Vérifie que l'exception IllegalArgumentException est lancée pour un nom null
        assertThrows(IllegalArgumentException.class, () -> new MainCourse(1L, null, "description", 1.0, Optional.empty()));

        // Vérifie que l'exception IllegalArgumentException est lancée pour un nom vide
        assertThrows(IllegalArgumentException.class, () -> new MainCourse(1L, "", "description", 1.0,Optional.empty()));
    }

    @Test
    void shouldBuildWithValidName() {
        // Crée un plat principal valide
        MainCourse mainCourse = new MainCourse(1L, "MainCourse", "description", 1.0, Optional.empty());

        // Vérifie que le nom est correctement assigné
        assertEquals("MainCourse", mainCourse.name());
    }

    @Test
    void shouldNotBuildWithInvalidDescription() {
        assertThrows(IllegalArgumentException.class, () -> new MainCourse(1L, "MainCourse", null, 1.0, Optional.empty()));
        assertThrows(IllegalArgumentException.class, () -> new MainCourse(1L, "MainCourse", "", 1.0, Optional.empty()));
    }

    @Test
    void shouldBuildWithValidDescription() {
        MainCourse mainCourse = new MainCourse(1L, "MainCourse", "A delicious dish", 1.0, Optional.empty());
        assertEquals("A delicious dish", mainCourse.description());
    }

    @Test
    void shouldNotBuildWithInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> new MainCourse(1L, "MainCourse", "description", -1.0, Optional.empty()));
        assertThrows(IllegalArgumentException.class, () -> new MainCourse(1L, "MainCourse", "description", 0.0, Optional.empty()));
    }

    @Test
    void shouldBuildWithValidPrice() {
        MainCourse mainCourse = new MainCourse(1L, "MainCourse", "description", 12.5, Optional.empty());
        assertEquals(12.5, mainCourse.price());
    }

}