package org.moaydogdu.enoca_challenge.product.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.moaydogdu.enoca_challenge.common.model.entity.BaseEntity;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(
            name = "AMOUNT",
            precision = 24,
            scale = 4
    )
    private BigDecimal amount;

    @Column(
            name = "UNIT_PRICE",
            precision = 24,
            scale = 4
    )
    private BigDecimal unitPrice;

}
