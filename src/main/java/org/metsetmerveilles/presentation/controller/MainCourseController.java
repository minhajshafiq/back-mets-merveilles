package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.model.MainCourse;
import org.metsetmerveilles.domain.service.maincourse.IMainCourseService;
import org.metsetmerveilles.presentation.dto.MainCourseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/main-course")
public class MainCourseController {

    private final IMainCourseService mainCourseService;

    public MainCourseController(IMainCourseService mainCourseService) {
        this.mainCourseService = mainCourseService;
    }

    @GetMapping
    public List<MainCourseDto> list() {
        return mainCourseService.getAllMainCourses().stream()
                .map(mainCourse -> new MainCourseDto(
                        mainCourse.id(),
                        mainCourse.name(),
                        mainCourse.description(),
                        mainCourse.price(),
                        mainCourse.menuId().orElse(null)
                ))
                .toList();
    }

    @PostMapping
    public MainCourseDto create(@RequestBody MainCourseDto mainCourseDto) {
        MainCourse mainCourse = new MainCourse(
                mainCourseDto.id(),
                mainCourseDto.name(),
                mainCourseDto.description(),
                mainCourseDto.price(),
                Optional.ofNullable(mainCourseDto.menuId()));

        MainCourse createdMainCourse = mainCourseService.createMainCourse(
                mainCourse
        );

        return new MainCourseDto(
                createdMainCourse.id(),
                createdMainCourse.name(),
                createdMainCourse.description(),
                createdMainCourse.price(),
                createdMainCourse.menuId().orElse(null)
        );
    }
}
