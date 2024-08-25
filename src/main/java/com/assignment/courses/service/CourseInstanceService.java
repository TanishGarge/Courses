package com.assignment.courses.service;

import com.assignment.courses.model.CourseInstance;
import com.assignment.courses.model.Courses;
import com.assignment.courses.repository.CourseInstancesRepository;
import com.assignment.courses.repository.CoursesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseInstanceService {

    @Autowired
    private CourseInstancesRepository courseInstancesRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    public List<CourseInstance> getCourseInstancesByYearAndSemester(int year, int semester) {
        return courseInstancesRepository.findByYearAndSemester(year, semester);
    }

    public Optional<CourseInstance> getCourseInstanceByYearAndSemesterAndCourseId(int year, int semester, Long courseId) {
        return courseInstancesRepository.findByYearAndSemesterAndCourseId(year, semester, courseId);
    }

    public CourseInstance createCourseInstance(Long courseId, int year, int semester) {
        Courses course = coursesRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course Not Found"));
        CourseInstance courseInstance = new CourseInstance();
        courseInstance.setCourse(course);
        courseInstance.setYear(year);
        courseInstance.setSemester(semester);
        return courseInstancesRepository.save(courseInstance);
    }
    @Transactional
    public void deleteCourseInstanceByYearAndSemesterAndCourseId(int year, int semester, Long courseId) {
        CourseInstance courseInstance = courseInstancesRepository.findByYearAndSemesterAndCourseId(year, semester, courseId).orElseThrow(() -> new RuntimeException("Instance not found"));
        courseInstancesRepository.delete(courseInstance);
    }
}
