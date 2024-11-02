package org.metsetmerveilles.domain.service;

import org.metsetmerveilles.domain.model.Menu;

import java.util.List;

public interface IMenuService {
    List<Menu> getAllMenus();

    Menu createMenu(Menu menu);
}
