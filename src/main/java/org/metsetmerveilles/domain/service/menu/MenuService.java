package org.metsetmerveilles.domain.service.menu;

import jakarta.persistence.EntityNotFoundException;
import org.metsetmerveilles.data_access.entity.MenuEntity;
import org.metsetmerveilles.data_access.repository.DrinksRepository;
import org.metsetmerveilles.data_access.repository.MenuRepository;
import org.metsetmerveilles.domain.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService implements IMenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, DrinksRepository drinksRepository) {
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

    // Create a new Menu (Create)
    @Override
    public Menu createMenu(Menu menu) {
        MenuEntity menuEntity = MenuEntity.fromDomain(menu);
        MenuEntity savedMenuEntity = menuRepository.save(menuEntity);
        return savedMenuEntity.toDomain();
    }

    @Override
    public Menu getMenuById(Long id) {
        MenuEntity menu = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu Not Found"));
        return menu.toDomain();
    }

    @Override
    public Menu updateMenu(Menu menu) {
        MenuEntity menuEntity = menuRepository.findById(menu.id())
                .orElseThrow(() -> new EntityNotFoundException("Menu Not Found"));

        // Mutualisation du code START
        menuEntity.updateDomain(menu);
        // Mutualisation du code END

        MenuEntity savedMenuEntity = menuRepository.save(menuEntity);

        return savedMenuEntity.toDomain();
    }

    @Override
    public void deleteMenu(Long id) {
        MenuEntity menuEntity = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu Not Found"));

        menuRepository.delete(menuEntity);
    }

}
