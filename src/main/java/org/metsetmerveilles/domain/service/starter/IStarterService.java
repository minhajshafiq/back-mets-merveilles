package org.metsetmerveilles.domain.service.starter;

import org.metsetmerveilles.domain.model.Starter;

import java.util.List;

public interface IStarterService {
    List<Starter> getAllStarters();

    Starter createStarter(Starter starter);

    Starter getStarterById(Long id);

    Starter updateStarter(Long id, String name, String description, double price, Long menuId);

    void deleteStarter(Long id);
}
