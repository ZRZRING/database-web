package zrzring.web.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import zrzring.web.entity.Course;
import zrzring.web.repository.DeptRepository;
import zrzring.web.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final DeptRepository deptRepository;

    public CourseService(
            CourseRepository courseRepository,
            DeptRepository deptRepository
    ) {
        this.courseRepository = courseRepository;
        this.deptRepository = deptRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Transactional
    public Course saveCourse(Course course) {
        if (course.getId() == null) {
            Integer newId = findNextAvailableId();
            course.setId(newId);
        }
        if (!deptRepository.existsByDeptName(course.getDeptName())) {
            throw new IllegalArgumentException(course.getDeptName() + " 不存在于部门管理中");
        }
        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    private Integer findNextAvailableId() {
        List<Course> Courses = courseRepository.findAllByOrderByIdAsc();

        int nextId = 1;
        for (Course x : Courses) {
            if (x.getId() != nextId) {
                break;
            }
            nextId++;
        }
        return nextId;
    }
}
