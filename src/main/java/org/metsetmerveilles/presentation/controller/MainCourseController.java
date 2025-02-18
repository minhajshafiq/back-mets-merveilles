package org.metsetmerveilles.presentation.controller;

import org.metsetmerveilles.domain.model.MainCourse;
import org.metsetmerveilles.domain.service.FirebaseStorageService;
import org.metsetmerveilles.domain.service.maincourse.IMainCourseService;
import org.metsetmerveilles.presentation.dto.MainCourseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://front-mets-merveilles.vercel.app")
@RequestMapping("/main-course")
public class MainCourseController {

    private final IMainCourseService mainCourseService;
    private final FirebaseStorageService firebaseStorageService;

    public MainCourseController(IMainCourseService mainCourseService, FirebaseStorageService firebaseStorageService) {
        this.mainCourseService = mainCourseService;
        this.firebaseStorageService = firebaseStorageService;
    }

    @GetMapping
    public List<MainCourseDto> list() {
        return mainCourseService.getAllMainCourses().stream()
                .map(MainCourseDto::fromDomain)
                .toList();
    }

    @PostMapping(consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public MainCourseDto create(@RequestPart("data") MainCourseDto mainCourseDto,
                                @RequestPart("file") MultipartFile file) throws IOException {

        // Uploader l'image vers Firebase Storage
        String imageUrl = firebaseStorageService.uploadImage(file, "main-courses");

        // Créer l'objet MainCourse
        MainCourse mainCourse = new MainCourse(
                mainCourseDto.id(),
                mainCourseDto.name(),
                mainCourseDto.description(),
                mainCourseDto.price(),
                imageUrl,
                Optional.ofNullable(mainCourseDto.menuId()));

        MainCourse createdMainCourse = mainCourseService.createMainCourse(mainCourse);

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
                mainCourseDto.imageUrl(),
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
