package pdp.uz.jparelationships.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(nullable = false)
    private Integer lesson;


    @Column(nullable = false)
    private Time startAt;

    @Column(nullable = false)
    private Time endAt;

    @JoinColumn(nullable = false)
    @OneToOne
    private Group groups;
}
