package org.metsetmerveilles.domain.service.starter;

import org.metsetmerveilles.domain.model.Starter;

import java.util.List;

public interface IStarterService {
    List<Starter> getAllStarters();

    // Create a new Starter (Create)
    Starter createStarter(Starter starter);

    // Get a Starter by id (Read)
    Starter getStarterById(Long id);

    // Update a Starter by id (Update)
    Starter updateStarter(Starter starter);

    // Delete a Starter by id (Delete)
    void deleteStarter(Long id);
}
