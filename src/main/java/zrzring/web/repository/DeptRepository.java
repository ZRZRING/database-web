package zrzring.web.repository;

import zrzring.web.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Department, String> {
    boolean existsByDeptName(String deptName);
}
