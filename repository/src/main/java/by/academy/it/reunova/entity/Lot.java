package by.academy.it.reunova.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lot")
@Entity
public class Lot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id", unique = true)
    private Integer id;

    @Column(name = "name_lot")
    private String nameLot;

    @Column(name = "description")
    private String descriptionLot;

    @Column(name = "start_date")
    private LocalDate startDateLot;

    @Column(name = "end_date")
    private LocalDate endDateLot;

    @Column(name = "start_price")
    private BigDecimal startPriceLot;

    @Column(name = "end_price")
    private BigDecimal endPriceLot;

    @Column(name = "status")
    private Boolean statusLot;

    @OneToOne(mappedBy = "lotInOrderHistory",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @ToString.Exclude
    private OrderHistory orderHistory;

    @ManyToMany(mappedBy = "lots")
    @ToString.Exclude
    private List<Buyer> buyers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lot lot = (Lot) o;
        return Objects.equals(id, lot.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


