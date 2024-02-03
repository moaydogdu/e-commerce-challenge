package org.moaydogdu.enoca_challenge.cart.model.mapper.cart;

import lombok.experimental.UtilityClass;
import org.moaydogdu.enoca_challenge.cart.model.Cart;
import org.moaydogdu.enoca_challenge.cart.model.entity.CartEntity;
import org.moaydogdu.enoca_challenge.cart.model.mapper.cart_item.CartItemMapper;

import java.util.List;

@UtilityClass
public class CartMapper {

    public static Cart toDomainModel(
            final CartEntity cartEntity
    ) {
        return Cart.builder()
                .id(cartEntity.getId())
                .isEmpty(cartEntity.getIsEmpty())
                .cartTotalPrice(cartEntity.getCartTotalPrice())
                .cartTotalProductAmount(cartEntity.getCartTotalProductAmount())
                .customerId(cartEntity.getCustomerEntity().getId())
                .cartItems(
                        cartEntity.getCartItemsEntities() == null ? null :
                                cartEntity.getCartItemsEntities().stream()
                                        .map(CartItemMapper::toDomainModel)
                                        .toList()
                )
                .createdAt(cartEntity.getCreatedAt())
                .createdBy(cartEntity.getCreatedBy())
                .updatedAt(cartEntity.getUpdatedAt())
                .updatedBy(cartEntity.getUpdatedBy())
                .build();
    }

    public static List<Cart> toDomainModel(
            final List<CartEntity> cartEntities
    ) {
        return cartEntities.stream()
                .map(CartMapper::toDomainModel)
                .toList();
    }

    public static CartEntity toEntity(
            final Cart cartDomainModel
    ) {
        return CartEntity.builder()
                .id(cartDomainModel.getId())
                .isEmpty(cartDomainModel.getIsEmpty())
                .cartTotalPrice(cartDomainModel.getCartTotalPrice())
                .cartTotalProductAmount(cartDomainModel.getCartTotalProductAmount())
                .customerEntity(null)
                .createdAt(cartDomainModel.getCreatedAt())
                .createdBy(cartDomainModel.getCreatedBy())
                .updatedAt(cartDomainModel.getUpdatedAt())
                .updatedBy(cartDomainModel.getUpdatedBy())
                .build();
    }
}
