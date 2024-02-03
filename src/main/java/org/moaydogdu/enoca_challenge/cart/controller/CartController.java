package org.moaydogdu.enoca_challenge.cart.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.cart.model.Cart;
import org.moaydogdu.enoca_challenge.cart.model.dto.request.CartItemCreateRequest;
import org.moaydogdu.enoca_challenge.cart.service.cart.CartService;
import org.moaydogdu.enoca_challenge.cart.service.cart.CartUpdateService;
import org.moaydogdu.enoca_challenge.common.model.dto.response.EnocaResponse;
import org.moaydogdu.enoca_challenge.product.model.Product;
import org.moaydogdu.enoca_challenge.product.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@Validated
public class CartController {

    // TODO : EmptyChart -> Tüm sepetin boşaltıldığı akış.
    // TODO : RemoveItem -> Sepetten ürün çıkarılan akış.
    // TODO : UpdateItemAmoun -> Sepetteki ürünün miktarının güncellenmesi.



    private final ProductService productService;
    private final CartUpdateService cartUpdateService;
    private final CartService cartService;

    @PostMapping("/add-product")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public EnocaResponse<Void> addProduct(
            @RequestBody @Valid final CartItemCreateRequest cartItemCreateRequest
    ) {
        final Product productToAddCart = productService
                .getProductById(cartItemCreateRequest.getProductId());

        cartUpdateService.addItem(
                productToAddCart,
                cartItemCreateRequest
        );

        return EnocaResponse.SUCCESS;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public EnocaResponse<Cart> getCartByCustomer()
    {
        final Cart cartFromCustomer = cartService
                .getCartOfRequestOwnerCustomer();

        return EnocaResponse.successOf(cartFromCustomer);
    }



}
