package org.moaydogdu.enoca_challenge.cart.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.moaydogdu.enoca_challenge.cart.model.CartItem;
import org.moaydogdu.enoca_challenge.common.model.entity.BaseEntity;
import org.moaydogdu.enoca_challenge.customer.model.entity.CustomerEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CART")
public class CartEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "IS_EMPTY")
    private Boolean isEmpty;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal cartTotalPrice;

    @Column(name = "TOTAL_PRODUCT_AMOUNT")
    private BigDecimal cartTotalProductAmount;

    @OneToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "CUSTOMER_ID",
            referencedColumnName = "ID"
    )
    private CustomerEntity customerEntity;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "cartEntity"
    )
    @Builder.Default
    private List<CartItemEntity> cartItemsEntities = new ArrayList<>();

}
