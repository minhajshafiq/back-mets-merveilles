package org.metsetmerveilles.domain.service.drinks;

import jakarta.persistence.EntityNotFoundException;
import org.metsetmerveilles.data_access.entity.DrinksEntity;
import org.metsetmerveilles.data_access.entity.MenuEntity;
import org.metsetmerveilles.data_access.repository.DrinksRepository;
import org.metsetmerveilles.data_access.repository.MenuRepository;
import org.metsetmerveilles.domain.model.Drinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinksService implements IDrinksService {
    private final DrinksRepository drinksRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public DrinksService(DrinksRepository drinksRepository, MenuRepository menuRepository) {
        this.drinksRepository = drinksRepository;
        this.menuRepository = menuRepository;
    }


    @Override
    public List<Drinks> getAllDrinks() {
        return drinksRepository.findAll().stream()
                .map(drinksEntity -> new Drinks(
                        drinksEntity.getId(),
                        drinksEntity.getName(),
                        drinksEntity.getDescription(),
                        drinksEntity.getPrice(),
                        drinksEntity.getImageUrl(),
                        drinksEntity.getMenu() != null ? Optional.of(drinksEntity.getMenu().getId()) : Optional.empty()
                ))
                .toList();
    }

    // Create a new Drinks (Create)
    @Override
    public Drinks createDrinks(Drinks drinks) {
        DrinksEntity drinksEntity = DrinksEntity.fromDomain(drinks);

        updateMenuOnDrinks(drinksEntity, drinks.menuId());

        DrinksEntity savedDrinksEntity = drinksRepository.save(drinksEntity);

        return savedDrinksEntity.toDomain();
    }

    // Get a Drinks by id (Read)
    @Override
    public Drinks getDrinksById(Long id) {
        DrinksEntity drinks = drinksRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Drinks not found"));
        return drinks.toDomain();
    }


    // Update a Drinks (Update)
    @Override
    public Drinks updateDrinks(Drinks drinks) {
        DrinksEntity drinksEntity = drinksRepository.findById(drinks.id())
                .orElseThrow(() -> new EntityNotFoundException("Drinks not found"));

        updateDrinksProperties(drinksEntity, drinks);

        updateMenuOnDrinks(drinksEntity, drinks.menuId());

        DrinksEntity updatedDrinksEntity = drinksRepository.save(drinksEntity);

        return updatedDrinksEntity.toDomain();
    }

    // Delete a Drinks (Delete)
    @Override
    public void deleteDrinks(Long id) {
        drinksRepository.deleteById(id);
    }


    private void updateDrinksProperties(DrinksEntity drinksEntity, Drinks drinks) {
        drinksEntity.setName(drinks.name());
        drinksEntity.setDescription(drinks.description());
        drinksEntity.setPrice(drinks.price());
    }

    private void updateMenuOnDrinks(DrinksEntity drinksEntity, Optional<Long> menuId) {
        if (menuId.isEmpty()) {
            return;
        }

        MenuEntity menuEntity = menuRepository.findById(menuId.get())
                .orElseThrow(() -> new EntityNotFoundException("menu with id %d not found !".formatted(menuId.get())));

        drinksEntity.setMenu(menuEntity);
    }
}
