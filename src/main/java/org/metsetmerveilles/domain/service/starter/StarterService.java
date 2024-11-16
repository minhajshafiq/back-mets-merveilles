package org.metsetmerveilles.domain.service.starter;

import jakarta.persistence.EntityNotFoundException;
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
                        Optional.ofNullable(starterEntity.getMenu().getId())
                ))
                .toList();
    }

    @Override
    public Starter createStarter(Starter starter) {
        StarterEntity starterEntity = StarterEntity.fromDomain(starter);

        starter.menuId().ifPresent(id -> {
            MenuEntity menuEntity = menuRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Menu not found"));
            starterEntity.setMenu(menuEntity);
        });

        StarterEntity savedStarterEntity = starterRepository.save(starterEntity);

        return savedStarterEntity.toDomain();
    }

    @Override
    public Starter getStarterById(Long id) {
        StarterEntity starter = starterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Starter not found"));
        return starter.toDomain();
    }

    @Override
    public Starter updateStarter(Long id, String name, String description, double price, Long menuId) {
        return null;
    }

    @Override
    public void deleteStarter(Long id) {
        starterRepository.deleteById(id);
    }
}
