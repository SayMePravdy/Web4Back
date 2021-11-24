package se.ifmo.labs.s311723.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Builder
@Data
@Table(name = "t_hit")
@NoArgsConstructor
public class Hit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double x;

    private double y;

    private int r;

    @Column(name = "ishit")
    private boolean isHit;

    @Column(name = "exectime")
    private double execTime;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Hit(double x, double y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}
