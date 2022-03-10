package pdp.uz.jparelationships.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
//Address, corpName, directorName
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(nullable = false)
    private  String corpName;

    @Column(nullable = false)
    private  String  directorName;

    @OneToOne
    private  Address address;
}
