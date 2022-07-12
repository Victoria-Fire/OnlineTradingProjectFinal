package by.academy.it.reunova.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_history")
@Entity
public class OrderHistory implements Serializable {

    @Id
    @GenericGenerator(name = "one-one",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "lotInOrderHistory"))
    @GeneratedValue(generator = "one-one")
    @Column(name = "lot_id")
    private Integer id;

    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchaseOrderHistory;

    @Column(name = "current_price")
    private BigDecimal currentPriceOrderHistory;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_buyer")
    @ToString.Exclude
    private Buyer buyer;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    private Lot lotInOrderHistory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderHistory that = (OrderHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


