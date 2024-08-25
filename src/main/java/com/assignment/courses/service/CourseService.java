package com.assignment.courses.service;

import com.assignment.courses.model.Courses;
import com.assignment.courses.repository.CoursesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CoursesRepository coursesRepository;

    public List<Courses> getAllCourses() {
        List<Courses> courseList = new ArrayList<>();
        coursesRepository.findAll().forEach(courseList::add);
        return courseList;
    }

    public Courses getCourseById(Long courseId) {
        Optional<Courses> opt = coursesRepository.findById(courseId);
        return opt.isPresent() ? opt.get() : null;
    }

    public long addCourse(Courses course) throws Exception {
        Courses savedCourse = coursesRepository.save(course);
        return savedCourse.getId();
    }

    public void deleteCourses(Long courseId) {
        coursesRepository.deleteById(courseId);
    }
}
