package zrzring.web.repository;

import zrzring.web.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByName(String name);
    List<Student> findAllByOrderByIdAsc();
}
