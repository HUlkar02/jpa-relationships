package pdp.uz.jparelationships.payload;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Data
@Component
public class MarkDto {
    private Integer mark;
    private Timestamp marked;
    private Integer studentId;
    private Integer subjectId;
}
