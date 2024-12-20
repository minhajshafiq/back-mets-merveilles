package org.metsetmerveilles.domain.service.menu;

import org.metsetmerveilles.data_access.repository.MenuRepository;
import org.metsetmerveilles.domain.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService implements IMenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(menuEntity -> new Menu(
                        menuEntity.getId(),
                        menuEntity.getName(),
                        menuEntity.getDescription(),
                        menuEntity.getPrice()
                ))
                .toList();
    }

    @Override
    public Menu createMenu(String name, String description, double price) {
        return null;
    }

}
