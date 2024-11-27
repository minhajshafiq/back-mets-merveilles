package org.metsetmerveilles.domain.service.desserts;

import org.metsetmerveilles.domain.model.Desserts;

import java.util.List;

public interface IDessertsService {
    List<Desserts> getAllDesserts();

    // Create a new Desserts (Create)
    Desserts createDesserts(Desserts desserts);

    // Get a Desserts by id (Read)
    Desserts getDessertsById(Long id);

    // Update a Desserts by id (Update)
    Desserts updateDesserts(Desserts desserts);

    // Delete a Desserts by id (Delete)
    void deleteDesserts(Long id);
}
