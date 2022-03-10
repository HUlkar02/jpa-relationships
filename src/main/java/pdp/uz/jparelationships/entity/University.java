package pdp.uz.jparelationships.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(nullable = false)
    private String name;


    @OneToOne(fetch = FetchType.LAZY,optional = false)
    private Address address;

    public University(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
