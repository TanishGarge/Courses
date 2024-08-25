package com.assignment.courses.controller;

import com.assignment.courses.model.CourseInstance;
import com.assignment.courses.service.CourseInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/instances")
public class CourseInstanceController {

    @Autowired
    CourseInstanceService courseInstanceService;

    @GetMapping("/{year}/{semester}")
    public ResponseEntity<List<CourseInstance>> getCourseInstancesByYearAndSemester(@PathVariable int year, @PathVariable int semester) {
        return ResponseEntity.ok(courseInstanceService.getCourseInstancesByYearAndSemester(year, semester));
    }

    @GetMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<CourseInstance> getCourseInstancesByYearAndSemesterAndCourseId(@PathVariable int year, @PathVariable int semester, @PathVariable Long courseId) {
        CourseInstance courseInstance = courseInstanceService.getCourseInstanceByYearAndSemesterAndCourseId(year, semester, courseId).orElseThrow(() -> new RuntimeException("Instance Not Found"));
        return ResponseEntity.ok(courseInstance);
    }

    @PostMapping
    public ResponseEntity<CourseInstance> createCourseInstance(@RequestParam Long courseId, @RequestParam int year, @RequestParam int semester) {
        CourseInstance courseInstance = courseInstanceService.createCourseInstance(courseId, year, semester);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseInstance);
    }

    @DeleteMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<Void> deleteCourseInstance(@PathVariable int year, @PathVariable int semester, @PathVariable Long courseId) {
        courseInstanceService.deleteCourseInstanceByYearAndSemesterAndCourseId(year, semester, courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
