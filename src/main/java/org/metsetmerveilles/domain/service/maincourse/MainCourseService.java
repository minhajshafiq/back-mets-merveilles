package org.metsetmerveilles.domain.service.maincourse;

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

    // Create a new MainCourse (Create)
    @Override
    public MainCourse createMainCourse(MainCourse mainCourse) {
        MainCourseEntity mainCourseEntity = MainCourseEntity.fromDomain(mainCourse);

        updateMenuOnMainCourse(mainCourseEntity, mainCourse.menuId());

        MainCourseEntity savedCourseEntity = mainCourseRepository.save(mainCourseEntity);

        return savedCourseEntity.toDomain();
    }

    // Get a MainCourse by id (Read)
    @Override
    public MainCourse getMainCourseById(Long id)
            throws EntityNotFoundException {
        MainCourseEntity mainCourse = mainCourseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Main course not found"));
        return mainCourse.toDomain();
    }

    // Update a MainCourse by id (Update)
    @Override
    public MainCourse updateMainCourse(MainCourse mainCourse) {
        MainCourseEntity mainCourseEntity = mainCourseRepository.findById(mainCourse.id())
                .orElseThrow(() -> new EntityNotFoundException("Main course not found"));

        updateMenuOnMainCourse(mainCourseEntity, mainCourse.menuId());


        MainCourseEntity savedMainCourseEntity = mainCourseRepository.save(mainCourseEntity);

        return savedMainCourseEntity.toDomain();
    }

    @Override
    public void deleteMainCourse(Long id) {
        mainCourseRepository.deleteById(id);
    }


    private void updateMenuOnMainCourse(MainCourseEntity mainCourseEntity, Optional<Long> menuId) {
        if (menuId.isEmpty()) {
            return;
        }

        MenuEntity menuEntity = menuRepository.findById(menuId.get())
                .orElseThrow(() -> new EntityNotFoundException("menu with id %d not found !".formatted(menuId.get())));

        mainCourseEntity.setMenu(menuEntity);
    }
}
