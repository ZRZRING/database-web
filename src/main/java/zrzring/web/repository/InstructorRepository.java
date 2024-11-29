package zrzring.web.repository;

import zrzring.web.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    List<Instructor> findAllByOrderByIdAsc();
}
