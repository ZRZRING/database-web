package zrzring.web.controller;

import org.springframework.web.bind.annotation.*;
import zrzring.web.entity.Department;
import zrzring.web.service.DeptService;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DeptController {
    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @GetMapping
    public List<Department> getAllDept() {
        return deptService.getAllDept();
    }

    @GetMapping("/{deptName}")
    public Department getDept(@PathVariable String deptName) {
        return deptService.getDeptByDeptName(deptName);
    }

    @PostMapping
    public Department addDept(@RequestBody Department dept) {
        return deptService.saveDept(dept);
    }

    @PutMapping("/{deptName}")
    public Department updateDept(@PathVariable String deptName, @RequestBody Department dept) {
        dept.setDeptName(deptName);
        return deptService.saveDept(dept);
    }

    @DeleteMapping("/{deptName}")
    public void deleteDept(@PathVariable String deptName) {
        deptService.deleteDept(deptName);
    }
}
