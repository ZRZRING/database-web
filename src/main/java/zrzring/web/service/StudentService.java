package zrzring.web.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import zrzring.web.entity.Student;
import zrzring.web.repository.DeptRepository;
import zrzring.web.repository.EnrollmentRepository;
import zrzring.web.repository.StudentRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DeptRepository deptRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentService(
            StudentRepository studentRepository,
            DeptRepository deptRepository,
            EnrollmentRepository enrollmentRepository
    ) {
        this.studentRepository = studentRepository;
        this.deptRepository = deptRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            updateTotalCredits(student);
        }
        return students;
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Transactional
    public Student saveStudent(Student student) {
        if (student.getTotalCredits() == null) {
            student.setTotalCredits(new BigDecimal("0.0"));
        }
        if (student.getId() == null) {
            Integer newId = findNextAvailableId();
            student.setId(newId);
        }
        if (!deptRepository.existsByDeptName(student.getDeptName())) {
            throw new IllegalArgumentException(student.getDeptName() + " 不存在于部门管理中");
        }
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    public void updateTotalCredits(Student student) {
        BigDecimal newTotalCredits;
        if (student != null) {
            newTotalCredits = enrollmentRepository.findTotalCreditsForStudent(student.getId());
        } else {
            throw new IllegalArgumentException("saveEnrollment error, 未找到对应的学生");
        }
        if (newTotalCredits != null) {
            student.setTotalCredits(newTotalCredits);
        } else {
            throw new IllegalArgumentException("saveEnrollment error, 学分同步失败");
        }
        studentRepository.save(student);
    }

    private Integer findNextAvailableId() {
        List<Student> Students = studentRepository.findAllByOrderByIdAsc();

        int nextId = 1;
        for (Student x : Students) {
            if (x.getId() != nextId) {
                break;
            }
            nextId++;
        }
        return nextId;
    }
}
