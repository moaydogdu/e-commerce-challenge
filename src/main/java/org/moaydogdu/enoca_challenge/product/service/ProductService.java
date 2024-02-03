package org.moaydogdu.enoca_challenge.product.service;

import org.moaydogdu.enoca_challenge.common.model.CustomPage;
import org.moaydogdu.enoca_challenge.product.model.Product;
import org.moaydogdu.enoca_challenge.product.model.dto.request.ProductPagingRequest;

public interface ProductService {
    Product getProductById(
            final String productId
    );

    CustomPage<Product> getProducts(
            final ProductPagingRequest productPagingRequest
    );
}
