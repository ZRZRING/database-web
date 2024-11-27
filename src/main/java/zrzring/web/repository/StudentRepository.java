package zrzring.web.repository;

import zrzring.web.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByOrderByIdAsc();
}
