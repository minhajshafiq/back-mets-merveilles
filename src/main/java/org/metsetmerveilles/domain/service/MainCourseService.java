package org.metsetmerveilles.domain.service;

import jakarta.persistence.EntityNotFoundException;
import org.metsetmerveilles.data_access.entity.MainCourseEntity;
import org.metsetmerveilles.data_access.entity.MenuEntity;
import org.metsetmerveilles.data_access.repository.MainCourseRepository;
import org.metsetmerveilles.data_access.repository.MenuRepository;
import org.metsetmerveilles.domain.model.MainCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainCourseService implements IMainCourseService {
    private final MainCourseRepository mainCourseRepository;
    private final MenuRepository menuRepository;


    @Autowired
    public MainCourseService(MainCourseRepository mainCourseRepository, MenuRepository menuRepository) {
        this.mainCourseRepository = mainCourseRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MainCourse> getAllMainCourses() {
        return mainCourseRepository.findAll().stream()
                .map(mainCourseEntity -> new MainCourse(
                        mainCourseEntity.getId(),
                        mainCourseEntity.getName(),
                        mainCourseEntity.getDescription(),
                        mainCourseEntity.getPrice(),
                        Optional.ofNullable(mainCourseEntity.getMenu().getId())
                ))
                .toList();
    }

    @Override
    public MainCourse createMainCourse(MainCourse mainCourse) {
        MainCourseEntity mainCourseEntity = MainCourseEntity.fromDomain(mainCourse);

        mainCourse.menuId().ifPresent(id -> {
            MenuEntity menuEntity = menuRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Menu not found"));
            mainCourseEntity.setMenu(menuEntity);
        });

        MainCourseEntity savedCourseEntity = mainCourseRepository.save(mainCourseEntity);

        return savedCourseEntity.toDomain();
    }

    @Override
    public MainCourse getMainCourseById(Long id)
            throws EntityNotFoundException {
        MainCourseEntity mainCourse = mainCourseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Main course not found"));
        return mainCourse.toDomain();
    }

    @Override
    public MainCourse updateMainCourse(Long id, String name, String description, double price, Optional<Long> menuId)
            throws RuntimeException {
        return null;
    }

    @Override
    public void deleteMainCourse(Long id)
            throws RuntimeException {
        mainCourseRepository.deleteById(id);
    }
}
