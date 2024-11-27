package zrzring.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "department", schema = "university")
public class Department {
    @Id
    @Column(name = "dept_name", nullable = false)
    private String deptName;

    @Column(name = "building", nullable = false)
    private String building;

    @Column(name = "budget", nullable = false)
    private Long budget;

    public Department() {
        super();
    }

    public Department(String deptName, String building, Long budget) {
        this.deptName = deptName;
        this.building = building;
        this.budget = budget;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }
}
