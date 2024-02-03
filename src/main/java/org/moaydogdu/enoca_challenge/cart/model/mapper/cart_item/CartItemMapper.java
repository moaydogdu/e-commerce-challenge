package org.moaydogdu.enoca_challenge.cart.model.mapper.cart_item;

import lombok.experimental.UtilityClass;
import org.moaydogdu.enoca_challenge.cart.model.CartItem;
import org.moaydogdu.enoca_challenge.cart.model.entity.CartEntity;
import org.moaydogdu.enoca_challenge.cart.model.entity.CartItemEntity;
import org.moaydogdu.enoca_challenge.product.model.entity.ProductEntity;

import java.util.List;

@UtilityClass
public class CartItemMapper {

    public static CartItem toDomainModel(
            final CartItemEntity cartItemEntity
    ) {
        return CartItem.builder()
                .id(cartItemEntity.getId())
                .cartId(cartItemEntity.getCartEntity().getId())
                .productId(cartItemEntity.getProductEntity().getId())
                .amount(cartItemEntity.getAmount())
                .totalPrice(cartItemEntity.getTotalPrice())
                .isOrdered(cartItemEntity.getIsOrdered())
                .createdAt(cartItemEntity.getCreatedAt())
                .createdBy(cartItemEntity.getCreatedBy())
                .updatedAt(cartItemEntity.getUpdatedAt())
                .updatedBy(cartItemEntity.getUpdatedBy())
                .build();
    }

    public static List<CartItem> toDomainModel(
            final List<CartItemEntity> cartItemEntities
    ) {
        return cartItemEntities.stream()
                .map(CartItemMapper::toDomainModel)
                .toList();
    }

    public static CartItemEntity toEntity(
            final CartItem cartItemDomainModel
    ) {
        return CartItemEntity.builder()
                .id(cartItemDomainModel.getId())
                .cartEntity(CartEntity.builder().id(cartItemDomainModel.getCartId()).build())
                .productEntity(ProductEntity.builder().id(cartItemDomainModel.getProductId()).build())
                .amount(cartItemDomainModel.getAmount())
                .totalPrice(cartItemDomainModel.getTotalPrice())
                .isOrdered(cartItemDomainModel.getIsOrdered())
                .createdAt(cartItemDomainModel.getCreatedAt())
                .createdBy(cartItemDomainModel.getCreatedBy())
                .updatedAt(cartItemDomainModel.getUpdatedAt())
                .updatedBy(cartItemDomainModel.getUpdatedBy())
                .build();
    }
}
