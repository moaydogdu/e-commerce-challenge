package org.moaydogdu.enoca_challenge.cart.service.cart_item;

import org.moaydogdu.enoca_challenge.cart.model.Cart;
import org.moaydogdu.enoca_challenge.cart.model.CartItem;
import org.moaydogdu.enoca_challenge.cart.model.dto.request.CartItemCreateRequest;
import org.moaydogdu.enoca_challenge.product.model.Product;

public interface CartItemCreateService {
    CartItem createCartItem(
            final Product product,
            final Cart cart,
            final CartItemCreateRequest cartItemCreateRequest
    );
}
