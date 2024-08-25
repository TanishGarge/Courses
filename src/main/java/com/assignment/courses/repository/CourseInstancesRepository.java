package com.assignment.courses.repository;

import com.assignment.courses.model.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseInstancesRepository extends JpaRepository<CourseInstance, Long> {

    List<CourseInstance> findByYearAndSemester(int year, int semester);
    Optional<CourseInstance> findByYearAndSemesterAndCourseId(int year, int semester, Long courseId);
}
