package com.assignment.courses.repository;

import com.assignment.courses.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses, Long> {

}
