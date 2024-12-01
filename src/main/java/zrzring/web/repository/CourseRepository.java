package zrzring.web.repository;

import zrzring.web.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findAllByOrderByIdAsc();
}
