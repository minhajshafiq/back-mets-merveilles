package org.metsetmerveilles.domain.service;

import org.antlr.v4.runtime.atn.Transition;
import org.metsetmerveilles.domain.model.Menu;

import java.util.List;

public interface IMenuService {
    List<Menu> getAllMenus();

    Menu createMenu(String label);
}
