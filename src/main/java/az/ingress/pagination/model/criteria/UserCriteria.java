package az.ingress.pagination.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCriteria {

    private Integer ageFrom;
    private Integer ageTo;
    private String birthplace;
    private LocalDateTime createdDate;
}
