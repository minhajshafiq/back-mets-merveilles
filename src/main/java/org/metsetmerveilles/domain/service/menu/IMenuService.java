package org.metsetmerveilles.domain.service.menu;

import org.metsetmerveilles.domain.model.Menu;

import java.util.List;

public interface IMenuService {
    List<Menu> getAllMenus();

    Menu createMenu(String name, String description, double price);
}
