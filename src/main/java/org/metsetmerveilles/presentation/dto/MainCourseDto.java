package org.metsetmerveilles.presentation.dto;

import org.metsetmerveilles.domain.model.MainCourse;

public record MainCourseDto(
        Long id,
        String name,
        String description,
        double price,
        String imageUrl,
        Long menuId
) {

    public static MainCourseDto fromDomain(MainCourse mainCourse) {
        return new MainCourseDto(
                mainCourse.id(),
                mainCourse.name(),
                mainCourse.description(),
                mainCourse.price(),
                mainCourse.imageUrl(),
                mainCourse.menuId().orElse(null));
    }
}
