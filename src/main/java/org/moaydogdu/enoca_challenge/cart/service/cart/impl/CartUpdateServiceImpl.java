package org.moaydogdu.enoca_challenge.cart.service.cart.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.auth.model.EnocaIdentity;
import org.moaydogdu.enoca_challenge.cart.exception.CartNotFoundException;
import org.moaydogdu.enoca_challenge.cart.model.Cart;
import org.moaydogdu.enoca_challenge.cart.model.CartItem;
import org.moaydogdu.enoca_challenge.cart.model.dto.request.CartItemCreateRequest;
import org.moaydogdu.enoca_challenge.cart.model.entity.CartEntity;
import org.moaydogdu.enoca_challenge.cart.model.mapper.cart.CartMapper;
import org.moaydogdu.enoca_challenge.cart.model.mapper.cart_item.CartItemMapper;
import org.moaydogdu.enoca_challenge.cart.repository.CartRepository;
import org.moaydogdu.enoca_challenge.cart.service.cart.CartService;
import org.moaydogdu.enoca_challenge.cart.service.cart.CartUpdateService;
import org.moaydogdu.enoca_challenge.cart.service.cart_item.CartItemCreateService;
import org.moaydogdu.enoca_challenge.customer.model.Customer;
import org.moaydogdu.enoca_challenge.customer.model.mapper.CustomerMapper;
import org.moaydogdu.enoca_challenge.customer.service.customer.CustomerService;
import org.moaydogdu.enoca_challenge.product.exception.ProductNotEnoughAmountException;
import org.moaydogdu.enoca_challenge.product.model.Product;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartUpdateServiceImpl implements CartUpdateService {

    private final CartRepository cartRepository;
    private final CartService cartService;
    private final CustomerService customerService;
    private final CartItemCreateService cartItemCreateService;

    private final EnocaIdentity enocaIdentity;

    private String getCustomerId() {
        return enocaIdentity.getUserId();
    }

    @Override
    public Cart addItem(
            final Product product,
            final CartItemCreateRequest cartItemCreateRequest
    ) {
        this.checkProductAmountToAddChart(product, cartItemCreateRequest);

        final String customerId = getCustomerId();

        final Customer customer = customerService.getCustomerById(customerId);

        final CartEntity cartEntityByCustomer = cartRepository
                .findCartEntityByCustomerEntity(CustomerMapper.toEntity(customer))
                .orElseThrow(() -> new CartNotFoundException("Customer's cart deleted!"));

        final CartItem cartItem = cartItemCreateService
                    .createCartItem(
                            product,
                            CartMapper.toDomainModel(cartEntityByCustomer),
                            cartItemCreateRequest
                    );

        cartEntityByCustomer.getCartItemsEntities().add(CartItemMapper.toEntity(cartItem));

        this.calculateCartTotalPrice(cartEntityByCustomer, cartItem);

        this.calculateCartTotalProductAmount(cartEntityByCustomer, cartItem);

        cartRepository.save(cartEntityByCustomer);

        return CartMapper.toDomainModel(cartEntityByCustomer);

    }

    private void calculateCartTotalProductAmount(
            final CartEntity cartEntityToAddItem,
            final CartItem cartItem
    ) {
        cartEntityToAddItem.setCartTotalProductAmount(
                cartEntityToAddItem.getCartTotalProductAmount().add(cartItem.getAmount())
        );
    }

    private void calculateCartTotalPrice(
            final CartEntity cartEntityToAddItem,
            final CartItem cartItem
    ) {
        cartEntityToAddItem.setCartTotalPrice(
                cartEntityToAddItem.getCartTotalPrice().add(cartItem.getTotalPrice())
        );
    }

    private void checkProductAmountToAddChart(
            final Product product,
            final CartItemCreateRequest cartItemCreateRequest
    ) {
        if (product.getAmount().compareTo(cartItemCreateRequest.getAmount()) < 0) {
            throw new ProductNotEnoughAmountException("Current amount is " + product.getAmount()
                    + " but requested " + cartItemCreateRequest.getAmount());
        }
    }
}
