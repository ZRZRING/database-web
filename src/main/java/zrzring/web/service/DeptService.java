package zrzring.web.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import zrzring.web.entity.Department;
import zrzring.web.repository.DeptRepository;

import java.util.List;

@Service
public class DeptService {
    private final DeptRepository deptRepository;

    public DeptService(
            DeptRepository deptRepository
    ) {
        this.deptRepository = deptRepository;
    }

    public List<Department> getAllDept() {
        return deptRepository.findAll();
    }

    @Transactional
    public Department saveDept(Department dept) {
        if (!StringUtils.hasText(dept.getDeptName())) {
            throw new IllegalArgumentException("系名为空！");
        }
        return deptRepository.save(dept);
    }

    @Transactional
    public void deleteDept(String dept) {
        deptRepository.deleteById(dept);
    }

    public Department getDeptByDeptName(String name) {
        return deptRepository.findById(name).orElse(null);
    }
}
