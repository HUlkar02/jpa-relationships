package pdp.uz.jparelationships.payload;


import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class StudentDto {
    private String fio;
    private Integer classes;
    private Integer groupId;
}
