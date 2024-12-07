package zrzring.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDto {
    private Integer id;
    private String studentName;
    private String courseTitle;
    private BigDecimal grade;
}
