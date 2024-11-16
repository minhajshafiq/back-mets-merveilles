package org.metsetmerveilles.domain.service;

import org.metsetmerveilles.domain.model.MainCourse;

import java.util.List;
import java.util.Optional;

public interface IMainCourseService {
    List<MainCourse> getAllMainCourses();

    MainCourse createMainCourse(MainCourse mainCourse);

    MainCourse getMainCourseById(Long id);

    MainCourse updateMainCourse(Long id, String name, String description, double price, Optional<Long> menuId);

    void deleteMainCourse(Long id);

}
