package zrzring.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student", schema = "university")
public class Student {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "dept_name", nullable = false, length = 100)
    private String deptName;

    @Column(name = "tot_cred", nullable = false)
    private Integer totalCredits;

    public Student() {
        super();
    }

    public Student(Integer id, String name, String deptName, Integer totalCredits) {
        this.id = id;
        this.name = name;
        this.deptName = deptName;
        this.totalCredits = totalCredits;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }
}
