package zrzring.web.service;

import org.springframework.stereotype.Service;
import zrzring.web.entity.*;
import zrzring.web.repository.CourseRepository;
import zrzring.web.repository.EnrollmentRepository;
import zrzring.web.repository.StudentRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            CourseRepository courseRepository,
            StudentRepository studentRepository,
            StudentService studentService) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    public EnrollmentDto toDto(Enrollment enrollment) {
        Integer id = enrollment.getId();
        String studentName = studentRepository.findById(enrollment.getSid())
                .map(Student::getName)
                .orElse("未知学生");
        String courseTitle = courseRepository.findById(enrollment.getCid())
                .map(Course::getTitle)
                .orElse("未知课程");
        if (studentName.equals("未知学生") || courseTitle.equals("未知课程")) {
            throw new IllegalArgumentException("toDto error, 未找到对应的学生或课程");
        }
        return new EnrollmentDto(id, studentName, courseTitle, enrollment.getGrade());
    }

    public Enrollment toEnrollment(EnrollmentDto enrollmentDto) {
        Integer id = enrollmentDto.getId();
        System.out.println(enrollmentDto);
        Integer sid = studentRepository.findByName(enrollmentDto.getStudentName())
              .map(Student::getId)
              .orElse(null);
        Integer cid = courseRepository.findByTitle(enrollmentDto.getCourseTitle())
            .map(Course::getId)
            .orElse(null);
        System.out.println(new Enrollment(id, sid, cid, enrollmentDto.getGrade()));
        if (sid == null || cid == null) {
            throw new IllegalArgumentException("toEnrollment error, 未找到对应的学生或课程");
        }
        return new Enrollment(id, sid, cid, enrollmentDto.getGrade());
    }

    public List<EnrollmentDto> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        List<EnrollmentDto> result = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            result.add(toDto(enrollment));
        }
        return result;
    }

    public EnrollmentDto getEnrollmentDtoById(int id) {
        Enrollment enrollment = enrollmentRepository.findById(id).orElse(null);
        return toDto(enrollment);
    }

    public Enrollment saveEnrollment(EnrollmentDto enrollmentDto) {
        if (enrollmentDto.getId() == null) {
            enrollmentDto.setId(findNextAvailableId());
        }
        Enrollment enrollment = toEnrollment(enrollmentDto);
        Student student = studentRepository.findById(enrollment.getSid()).orElse(null);
        studentService.updateTotalCredits(student);
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Integer id) {
        enrollmentRepository.deleteById(id);
    }

    public List<Failure> calculateFailures() {
        return enrollmentRepository.countFailures();
    }

    private Integer findNextAvailableId() {
        List<Enrollment> enrollments = enrollmentRepository.findAllByOrderByIdAsc();

        int nextId = 1;
        for (Enrollment x : enrollments) {
            if (x.getId() != nextId) {
                break;
            }
            nextId++;
        }
        return nextId;
    }
}
