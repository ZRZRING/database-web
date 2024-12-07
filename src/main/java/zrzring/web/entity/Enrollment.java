package zrzring.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enrollment", schema = "university")
public class Enrollment {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "sid", nullable = false)
    private Integer sid;

    @Column(name = "cid", nullable = false)
    private Integer cid;

    @Column(name = "grade")
    private BigDecimal grade;
}
