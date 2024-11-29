package zrzring.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department", schema = "university")
public class Department {

    @Id
    @Column(name = "dept_name", nullable = false)
    private String deptName;

    @Column(name = "building", nullable = false)
    private String building;

    @Column(name = "budget", nullable = false)
    private Long budget;
}
