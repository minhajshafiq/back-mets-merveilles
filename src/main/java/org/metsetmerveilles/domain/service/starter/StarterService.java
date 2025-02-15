package org.metsetmerveilles.domain.service.starter;

import jakarta.persistence.EntityNotFoundException;
import org.metsetmerveilles.data_access.entity.MainCourseEntity;
import org.metsetmerveilles.data_access.entity.MenuEntity;
import org.metsetmerveilles.data_access.entity.StarterEntity;
import org.metsetmerveilles.data_access.repository.MenuRepository;
import org.metsetmerveilles.data_access.repository.StarterRepository;
import org.metsetmerveilles.domain.model.Starter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StarterService implements IStarterService {
    private final StarterRepository starterRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public StarterService(StarterRepository starterRepository, MenuRepository menuRepository) {
        this.starterRepository = starterRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Starter> getAllStarters() {
        return starterRepository.findAll().stream()
                .map(starterEntity -> new Starter(
                        starterEntity.getId(),
                        starterEntity.getName(),
                        starterEntity.getDescription(),
                        starterEntity.getPrice(),
                        starterEntity.getImageUrl(),
                        Optional.ofNullable(starterEntity.getMenu().getId())
                ))
                .toList();
    }

    // Create a new Starter (Create)
    @Override
    public Starter createStarter(Starter starter) {
        StarterEntity starterEntity = StarterEntity.fromDomain(starter);

        updateMenuOnStarter(starterEntity, starter.menuId());

        StarterEntity savedStarterEntity = starterRepository.save(starterEntity);

        return savedStarterEntity.toDomain();
    }

    // Get a Starter by id (Read)
    @Override
    public Starter getStarterById(Long id) {
        StarterEntity starter = starterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Starter not found"));
        return starter.toDomain();
    }

    // Update a Starter by id (Update)
    @Override
    public Starter updateStarter(Starter starter) {
        StarterEntity starterEntity = starterRepository.findById(starter.id())
                .orElseThrow(() -> new EntityNotFoundException("Starter not found"));

        updateStarterProperties(starterEntity, starter);

        updateMenuOnStarter(starterEntity, starter.menuId());
        

        StarterEntity savedStarterEntity = starterRepository.save(starterEntity);

        return savedStarterEntity.toDomain();
    }

    // Delete a Starter by id (Delete)
    @Override
    public void deleteStarter(Long id) {
        starterRepository.deleteById(id);
    }

    private void updateStarterProperties(StarterEntity starterEntity, Starter starter) {
        starterEntity.setName(starter.name());
        starterEntity.setDescription(starter.description());
        starterEntity.setPrice(starter.price());
    }

    private void updateMenuOnStarter(StarterEntity starterEntity, Optional<Long> menuId) {
        if (menuId.isEmpty()) {
            return;
        }

        MenuEntity menuEntity = menuRepository.findById(menuId.get())
                .orElseThrow(() -> new EntityNotFoundException("menu with id %d not found !".formatted(menuId.get())));

        starterEntity.setMenu(menuEntity);
    }
}
