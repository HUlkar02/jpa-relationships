package pdp.uz.jparelationships.payload;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Data
@Component
public class TimeTableDto {
    private Time startTime;
    private Time endTime;
    private Integer lessonStep;
    private Integer groupId;

}
