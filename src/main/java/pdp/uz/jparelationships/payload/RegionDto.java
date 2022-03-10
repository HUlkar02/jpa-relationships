package pdp.uz.jparelationships.payload;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegionDto {
    private String name;
    private Integer countryId;
}
