package by.academy.it.reunova.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buyer")
@Entity
public class Buyer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_id")
    private Integer id;

    @Column(name = "name_buyer")
    private String nameBuyer;

    @Column(name = "surname_buyer")
    private String surnameBuyer;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<OrderHistory> orderHistories = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "basket",
            joinColumns = @JoinColumn(name = "id_buyer"),
            inverseJoinColumns = @JoinColumn(name = "id_lot"))
    @ToString.Exclude
    private List<Lot> lots;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return Objects.equals(id, buyer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

