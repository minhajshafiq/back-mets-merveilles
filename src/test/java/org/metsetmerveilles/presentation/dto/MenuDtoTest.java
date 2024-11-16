package org.metsetmerveilles.presentation.dto;

import org.junit.jupiter.api.Test;
import org.metsetmerveilles.domain.model.Menu;

import static org.junit.jupiter.api.Assertions.*;

class MenuDtoTest {

    @Test
    void ShouldBuildFromDomain() {
        Menu menu = new Menu(1L, "name", "description", 1.0);
        MenuDto expected = new MenuDto(1L, "name", "description", 1.0);

        MenuDto actual = MenuDto.fromDomain(menu);

        assertEquals(expected, actual);
    }
}