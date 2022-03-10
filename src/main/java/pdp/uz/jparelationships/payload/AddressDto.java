package pdp.uz.jparelationships.payload;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AddressDto {
    private String street;
    private Integer homeNumber;
    private Integer regionId;
}
