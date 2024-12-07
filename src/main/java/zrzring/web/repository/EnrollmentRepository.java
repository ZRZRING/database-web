package zrzring.web.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zrzring.web.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import zrzring.web.entity.Failure;

import java.math.BigDecimal;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findAllByOrderByIdAsc();

    @Query(value =
            "SELECT COALESCE(SUM(c.credits), 0) FROM Enrollment e " +
            "JOIN Course c ON e.cid = c.id " +
            "WHERE e.sid = :studentId AND e.grade >= 60",
            nativeQuery = true)
    BigDecimal findTotalCreditsForStudent(@Param("studentId") Integer studentId);

    @Query("SELECT new zrzring.web.entity.Failure(c.title, COUNT(e.cid)) " +
            "FROM Enrollment e " +
            "JOIN Course c ON e.cid = c.id " +
            "WHERE e.grade is not null and e.grade < 60 " +
            "GROUP BY c.title")
    List<Failure> countFailures();
}
