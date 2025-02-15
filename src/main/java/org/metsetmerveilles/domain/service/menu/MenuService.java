package org.metsetmerveilles.domain.service.menu;

import jakarta.persistence.EntityNotFoundException;
import org.metsetmerveilles.data_access.entity.*;
import org.metsetmerveilles.data_access.repository.*;
import org.metsetmerveilles.domain.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService implements IMenuService {
    private final MenuRepository menuRepository;
    private final StarterRepository starterRepository;
    private final MainCourseRepository mainCourseRepository;
    private final DessertsRepository dessertsRepository;
    private final DrinksRepository drinksRepository;

    @Autowired
    public MenuService(
            MenuRepository menuRepository,
                       StarterRepository starterRepository,
                       MainCourseRepository mainCourseRepository,
                       DessertsRepository dessertsRepository,
                       DrinksRepository drinksRepository
    )
    {
        this.menuRepository = menuRepository;
        this.starterRepository = starterRepository;
        this.mainCourseRepository = mainCourseRepository;
        this.dessertsRepository = dessertsRepository;
        this.drinksRepository = drinksRepository;
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(menuEntity -> Menu.Create(
                        menuEntity.getId(),
                        menuEntity.getName(),
                        menuEntity.getDescription(),
                        menuEntity.getPrice(),
                        menuEntity.getImageUrl(),
                        Optional.ofNullable(menuEntity.getStarter())
                                .map(starters -> starters.stream()
                                        .map(StarterEntity::getId)
                                        .toList()),
                        Optional.ofNullable(menuEntity.getMainCourse())
                                .map(mainCourses -> mainCourses.stream()
                                        .map(MainCourseEntity::getId)
                                        .toList()),
                        Optional.ofNullable(menuEntity.getDesserts())
                                .map(desserts -> desserts.stream()
                                        .map(DessertsEntity::getId)
                                        .toList()),
                        Optional.ofNullable(menuEntity.getDrinks())
                                .map(drinks -> drinks.stream()
                                        .map(DrinksEntity::getId)
                                        .toList())
                ))
                .toList();
    }

    @Override
    public Menu createMenu(Menu menu) {
        MenuEntity menuEntity = MenuEntity.fromDomain(menu);

        updateStarterOnMenu(menuEntity, menu.starterIds());
        updateMainCourseOnMenu(menuEntity, menu.mainCourseIds());
        updateDessertsOnMenu(menuEntity, menu.dessertsIds());
        updateDrinksOnMenu(menuEntity, menu.drinksIds());

        MenuEntity savedMenuEntity = menuRepository.save(menuEntity);

        return savedMenuEntity.toDomain();
    }

    @Override
    public Menu getMenuById(Long id) {
        MenuEntity menuEntity = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));
        return menuEntity.toDomain();
    }

    @Override
    public Menu updateMenu(Menu menu) {
        MenuEntity menuEntity = menuRepository.findById(menu.id())
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));

        updateMenuProperties(menuEntity, menu);

        updateStarterOnMenu(menuEntity, menu.starterIds());
        updateMainCourseOnMenu(menuEntity, menu.mainCourseIds());
        updateDessertsOnMenu(menuEntity, menu.dessertsIds());
        updateDrinksOnMenu(menuEntity, menu.drinksIds());

        MenuEntity savedMenuEntity = menuRepository.save(menuEntity);

        return savedMenuEntity.toDomain();
    }

    @Override
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    private void updateStarterOnMenu(MenuEntity menuEntity, Optional<List<Long>> starterIds) {
        if (starterIds.isEmpty()) {
            return;
        }

        List<StarterEntity> starters = starterRepository.findAllById(starterIds.get());
        if (starters.isEmpty()) {
            throw new EntityNotFoundException("Starter(s) not found");
        }

        menuEntity.setStarter(starters);
    }

    private void updateMainCourseOnMenu(MenuEntity menuEntity, Optional<List<Long>> mainCourseIds) {
        if (mainCourseIds.isEmpty()) {
            return;
        }

        List<MainCourseEntity> mainCourses = mainCourseRepository.findAllById(mainCourseIds.get());
        if (mainCourses.isEmpty()) {
            throw new EntityNotFoundException("Main course(s) not found");
        }

        menuEntity.setMainCourse(mainCourses);
    }

    private void updateDessertsOnMenu(MenuEntity menuEntity, Optional<List<Long>> dessertsIds) {
        if (dessertsIds.isEmpty()) {
            return;
        }

        List<DessertsEntity> desserts = dessertsRepository.findAllById(dessertsIds.get());
        if (desserts.isEmpty()) {
            throw new EntityNotFoundException("Dessert(s) not found");
        }

        menuEntity.setDesserts(desserts);
    }

    private void updateDrinksOnMenu(MenuEntity menuEntity, Optional<List<Long>> drinksIds) {
        if (drinksIds.isEmpty()) {
            return;
        }

        List<DrinksEntity> drinks = drinksRepository.findAllById(drinksIds.get());
        if (drinks.isEmpty()) {
            throw new EntityNotFoundException("Drink(s) not found");
        }

        menuEntity.setDrinks(drinks);
    }

    private void updateMenuProperties(MenuEntity menuEntity, Menu menu) {
        menuEntity.setName(menu.name());
        menuEntity.setDescription(menu.description());
        menuEntity.setPrice(menu.price().value());
    }
}
