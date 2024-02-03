package org.moaydogdu.enoca_challenge.cart.model.mapper.cart;

import lombok.experimental.UtilityClass;
import org.moaydogdu.enoca_challenge.cart.model.entity.CartEntity;
import org.moaydogdu.enoca_challenge.customer.model.Customer;
import org.moaydogdu.enoca_challenge.customer.model.mapper.CustomerMapper;

import java.math.BigDecimal;

@UtilityClass
public class CartDTOMapper {

    public static CartEntity mapForEmptyCreating(
            final Customer customer
    ) {
        return CartEntity.builder()
                .isEmpty(true)
                .cartTotalPrice(BigDecimal.ZERO)
                .cartTotalProductAmount(BigDecimal.ZERO)
                .customerEntity(CustomerMapper.toEntity(customer))
                .build();
    }
}
