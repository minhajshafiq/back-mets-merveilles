package org.metsetmerveilles.presentation.dto;

import org.junit.jupiter.api.Test;
import org.metsetmerveilles.domain.model.Menu;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MenuDtoTest {

    @Test
    void ShouldBuildFromDomain() {
        // Créer un objet Menu
        Menu menu = new Menu(1L, "name", "description", 1.0,
                Optional.of(List.of(1L)),
                Optional.of(List.of(2L)),
                Optional.of(List.of(3L)),
                Optional.of(List.of(4L)));

        // Créer l'objet attendu de type MenuDto
        MenuDto expected = new MenuDto(
                1L,
                "name",
                "description",
                1.0,
                List.of(1L),  // starterIds
                List.of(2L),  // mainCourseIds
                List.of(3L),  // dessertsIds
                List.of(4L)   // drinksIds
        );

        MenuDto actual = MenuDto.fromDomain(menu);

        assertEquals(expected, actual);
    }
}