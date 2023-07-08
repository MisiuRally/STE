package Ultima.infrastructure.database.entity;

import Ultima.domain.Competitor;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "")
@ToString(of = {""})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "time_result")
public class TimeResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_result_id")
    private Integer timeResultId;

    @Column(name = "result")
    private String time;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competitor_id")
    private Competitor competitor;

}
