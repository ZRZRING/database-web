package zrzring.web.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import zrzring.web.entity.Instructor;
import zrzring.web.repository.DeptRepository;
import zrzring.web.repository.InstructorRepository;

import java.util.List;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final DeptRepository deptRepository;

    public InstructorService(
            InstructorRepository instructorRepository,
            DeptRepository deptRepository
    ) {
        this.instructorRepository = instructorRepository;
        this.deptRepository = deptRepository;
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(Integer id) {
        return instructorRepository.findById(id).orElse(null);
    }

    @Transactional
    public Instructor saveInstructor(Instructor instructor) {
        if (instructor.getId() == null) {
            Integer newId = findNextAvailableId();
            instructor.setId(newId);
        }
        if (!deptRepository.existsByDeptName(instructor.getDeptName())) {
            throw new IllegalArgumentException(instructor.getDeptName() + " 不存在于部门管理中");
        }
        return instructorRepository.save(instructor);
    }

    @Transactional
    public void deleteInstructor(Integer id) {
        instructorRepository.deleteById(id);
    }

    private Integer findNextAvailableId() {
        List<Instructor> Instructors = instructorRepository.findAllByOrderByIdAsc();

        int nextId = 1;
        for (Instructor x : Instructors) {
            if (x.getId() != nextId) {
                break;
            }
            nextId++;
        }
        return nextId;
    }
}
