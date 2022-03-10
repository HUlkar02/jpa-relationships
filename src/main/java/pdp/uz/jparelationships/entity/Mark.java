package pdp.uz.jparelationships.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer mark;

    @Column(nullable = false)
    @CreatedDate
    private Timestamp markedTime = new Timestamp(System.currentTimeMillis());

    @OneToOne
    private Student student;

    @OneToOne
    private Subject subject;


}
