package org.moaydogdu.enoca_challenge.cart.service.cart;

import org.moaydogdu.enoca_challenge.cart.model.Cart;
import org.moaydogdu.enoca_challenge.cart.model.dto.request.CartItemCreateRequest;
import org.moaydogdu.enoca_challenge.product.model.Product;

public interface CartUpdateService {
    Cart addItem(
            final Product product,
            final CartItemCreateRequest cartItemCreateRequest
    );
}
