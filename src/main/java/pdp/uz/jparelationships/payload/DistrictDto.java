package pdp.uz.jparelationships.payload;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DistrictDto {
    private String name;
    private Integer regionId;
}
