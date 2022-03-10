package pdp.uz.jparelationships.payload;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Data
@Component
public class FacultyDto {

    private String  name;
    private Integer universityId;
}
