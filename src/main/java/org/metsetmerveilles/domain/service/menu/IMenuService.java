package org.metsetmerveilles.domain.service.menu;

import org.metsetmerveilles.domain.model.Menu;

import java.util.List;

public interface IMenuService {
    List<Menu> getAllMenus();

    // Create a new Menu (Create)
    Menu createMenu(Menu menu);

    // Get a Menu by id (Read)
    Menu getMenuById(Long id);

    // Update a Menu by id (Update)
    Menu updateMenu(Menu menu);

    // Delete a Menu by id (Delete)
    void deleteMenu(Long id);
}
