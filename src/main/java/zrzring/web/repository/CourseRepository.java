package zrzring.web.repository;

import zrzring.web.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByTitle(String title);
    List<Course> findAllByOrderByIdAsc();
}
