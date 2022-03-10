package pdp.uz.jparelationships.payload;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TeacherDto {
    private String fio;
    private Integer groupId;
    private Integer subjectId;
}
