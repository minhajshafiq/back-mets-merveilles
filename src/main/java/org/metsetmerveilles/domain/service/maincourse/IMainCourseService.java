package org.metsetmerveilles.domain.service.maincourse;

import org.metsetmerveilles.domain.model.MainCourse;

import java.util.List;

public interface IMainCourseService {
    List<MainCourse> getAllMainCourses();

    // Create a new MainCourse (Create)
    MainCourse createMainCourse(MainCourse mainCourse);

    // Get a MainCourse by id (Read)
    MainCourse getMainCourseById(Long id);

    // Update a MainCourse by id (Update)
    MainCourse updateMainCourse(MainCourse mainCourse);

    // Delete a MainCourse by id (Delete)
    void deleteMainCourse(Long id);

}
