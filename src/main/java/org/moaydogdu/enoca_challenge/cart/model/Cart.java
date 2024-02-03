package org.moaydogdu.enoca_challenge.cart.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.moaydogdu.enoca_challenge.common.model.BaseDomainModel;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseDomainModel {

    private String id;
    private Boolean isEmpty;
    private BigDecimal cartTotalPrice;
    private BigDecimal cartTotalProductAmount;
    private List<CartItem> cartItems;
    private String customerId;
}
