package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.model.Starter;
import org.metsetmerveilles.domain.service.starter.IStarterService;
import org.metsetmerveilles.presentation.dto.StarterDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/starter")
public class StarterController {

    public final IStarterService starterService;

    public StarterController(IStarterService starterService) {
        this.starterService = starterService;
    }

    @GetMapping
    public List<StarterDto> list() {
        return starterService.getAllStarters().stream()
                .map(StarterDto::fromDomain)
                .toList();
    }

    @PostMapping
    public StarterDto create(@RequestBody StarterDto starterDto) {
        Starter starter = new Starter(
                starterDto.id(),
                starterDto.name(),
                starterDto.description(),
                starterDto.price(),
                Optional.ofNullable(starterDto.menuId()));

        Starter createdStarter = starterService.createStarter(starter);

        return StarterDto.fromDomain(createdStarter);
    }

    @GetMapping("/{id}")
    public StarterDto get(@PathVariable Long id) {
        Starter starter = starterService.getStarterById(id);
        return StarterDto.fromDomain(starter);
    }

    @PutMapping("/{id}")
    public StarterDto update(@PathVariable Long id, @RequestBody StarterDto starterDto) {
        Starter starter = new Starter(
                id,
                starterDto.name(),
                starterDto.description(),
                starterDto.price(),
                Optional.ofNullable(starterDto.menuId()));

        Starter updatedStarter = starterService.updateStarter(starter);

        return StarterDto.fromDomain(updatedStarter);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        starterService.deleteStarter(id);
    }
}
