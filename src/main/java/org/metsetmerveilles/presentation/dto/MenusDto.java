package org.metsetmerveilles.presentation.dto;

import java.util.List;

public record MenusDto(List<StarterDto> starters,
                       List<MainCourseDto> mainCourses,
                       List<DessertsDto> desserts,
                       List<DrinksDto> drinks) {
}
