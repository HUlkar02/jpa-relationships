package pdp.uz.jparelationships.payload;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CountryDto {

    private String name;

    private Integer continentId;
}
