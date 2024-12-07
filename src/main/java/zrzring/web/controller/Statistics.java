package zrzring.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zrzring.web.service.EnrollmentService;
import zrzring.web.entity.Failure;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class Statistics {
    private final EnrollmentService enrollmentService;

    public Statistics(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/failures")
    public List<Failure> getFailures() {
        return enrollmentService.calculateFailures();
    }
}
