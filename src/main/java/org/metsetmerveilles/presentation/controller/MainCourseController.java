package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.model.MainCourse;
import org.metsetmerveilles.domain.service.maincourse.IMainCourseService;
import org.metsetmerveilles.presentation.dto.MainCourseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/main-course")
public class MainCourseController {

    private final IMainCourseService mainCourseService;

    public MainCourseController(IMainCourseService mainCourseService) {
        this.mainCourseService = mainCourseService;
    }

    @GetMapping
    public List<MainCourseDto> list() {
        return mainCourseService.getAllMainCourses().stream()
                .map(MainCourseDto::fromDomain)
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

        return MainCourseDto.fromDomain(createdMainCourse);
    }

    @GetMapping("/{id}")
    public MainCourseDto get(@PathVariable Long id) {
        MainCourse mainCourse = mainCourseService.getMainCourseById(id);
        return MainCourseDto.fromDomain(mainCourse);
    }

    @PutMapping("/{id}")
    public MainCourseDto update(@PathVariable Long id, @RequestBody MainCourseDto mainCourseDto) {
        MainCourse mainCourse = new MainCourse(
                id,
                mainCourseDto.name(),
                mainCourseDto.description(),
                mainCourseDto.price(),
                Optional.ofNullable(mainCourseDto.menuId())
        );

        MainCourse updatedMainCourse = mainCourseService.updateMainCourse(mainCourse);

        return MainCourseDto.fromDomain(updatedMainCourse);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mainCourseService.deleteMainCourse(id);
    }
}
