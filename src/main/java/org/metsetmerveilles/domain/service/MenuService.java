package org.metsetmerveilles.domain.service;

import org.metsetmerveilles.data_access.entity.MenuEntity;
import org.metsetmerveilles.data_access.repository.MenuRepository;
import org.metsetmerveilles.domain.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
                .map(menuEntity -> new Menu(menuEntity.getName()))
                .toList();
    }

    @Override
    public Menu createMenu(Menu menu) {
        return null;
    }

//    @Override
//    public Menu createMenu(Menu menu) {
//        MenuEntity menuEntity = menuRepository.save(new MenuEntity());
//        return new Menu(menuEntity.getId());
//    }

}
