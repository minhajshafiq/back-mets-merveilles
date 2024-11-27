package org.metsetmerveilles.domain.service.drinks;

import org.metsetmerveilles.domain.model.Drinks;

import java.util.List;

public interface IDrinksService {
    List<Drinks> getAllDrinks();

    // Create a new Drinks (Create)
    Drinks createDrinks(Drinks drinks);

    // Get a Drinks by id (Read)
    Drinks getDrinksById(Long id);

    // Update a Drinks (Update)
    Drinks updateDrinks(Drinks drinks);

    // Delete a Drinks (Delete)
    void deleteDrinks(Long id);
}
