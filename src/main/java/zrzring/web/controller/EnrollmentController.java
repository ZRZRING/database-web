package zrzring.web.controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;
import zrzring.web.entity.EnrollmentDto;
import zrzring.web.entity.Enrollment;
import zrzring.web.service.EnrollmentService;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public List<EnrollmentDto> getEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("{id}")
    public EnrollmentDto getEnrollmentDto(@PathVariable Integer id) {
        return enrollmentService.getEnrollmentDtoById(id);
    }

    @PostMapping
    public Enrollment addEnrollment(@RequestBody EnrollmentDto enrollmentDto) {
        return enrollmentService.saveEnrollment(enrollmentDto);
    }

    @PutMapping("/{id}")
    public Enrollment updateEnrollment(@PathVariable Integer id, @RequestBody EnrollmentDto enrollmentDto) {
        return enrollmentService.saveEnrollment(enrollmentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Integer id) {
        enrollmentService.deleteEnrollment(id);
    }
}
