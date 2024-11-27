package org.metsetmerveilles.domain.service.desserts;

import jakarta.persistence.EntityNotFoundException;
import org.metsetmerveilles.data_access.entity.DessertsEntity;
import org.metsetmerveilles.data_access.repository.DessertsRepository;
import org.metsetmerveilles.data_access.repository.MenuRepository;
import org.metsetmerveilles.domain.model.Desserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DessertsService implements IDessertsService {
    private final DessertsRepository dessertsRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public DessertsService(DessertsRepository dessertsRepository, MenuRepository menuRepository) {
        this.dessertsRepository = dessertsRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Desserts> getAllDesserts() {
        return dessertsRepository.findAll().stream()
                .map(dessertsEntity -> new Desserts(
                        dessertsEntity.getId(),
                        dessertsEntity.getName(),
                        dessertsEntity.getDescription(),
                        dessertsEntity.getPrice(),
                        Optional.ofNullable(dessertsEntity.getMenu().getId())
                ))
                .toList();
    }

    // Create a new Desserts (Create)
    @Override
    public Desserts createDesserts(Desserts desserts) {
        DessertsEntity dessertsEntity = DessertsEntity.fromDomain(desserts);

        // Mutualisation du code START
        dessertsEntity.associateMenu(desserts.menuId(), menuRepository);
        // Mutualisation du code END

        DessertsEntity savedDessertsEntity = dessertsRepository.save(dessertsEntity);

        return savedDessertsEntity.toDomain();
    }

    // Get a Desserts by id (Read)
    @Override
    public Desserts getDessertsById(Long id) {
        DessertsEntity desserts = dessertsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Desserts not found"));
        return desserts.toDomain();
    }

    // Update a Desserts by id (Update)
    @Override
    public Desserts updateDesserts(Desserts desserts) {
        DessertsEntity dessertsEntity = dessertsRepository.findById(desserts.id())
                .orElseThrow(() -> new EntityNotFoundException("Desserts not found"));

        // Mutualisation du code START
        dessertsEntity.updateDomain(desserts);

        dessertsEntity.associateMenu(desserts.menuId(), menuRepository);
        // Mutualisation du code END

        DessertsEntity updatedDessertsEntity = dessertsRepository.save(dessertsEntity);

        return updatedDessertsEntity.toDomain();
    }

    // Delete a Desserts by id (Delete)
    @Override
    public void deleteDesserts(Long id) {
        dessertsRepository.deleteById(id);
    }
}
