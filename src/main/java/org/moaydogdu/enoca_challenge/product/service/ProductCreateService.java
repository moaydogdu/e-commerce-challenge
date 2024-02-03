package org.moaydogdu.enoca_challenge.product.service;

import org.moaydogdu.enoca_challenge.product.model.Product;
import org.moaydogdu.enoca_challenge.product.model.dto.request.ProductCreateRequest;

public interface ProductCreateService {
    Product createProduct(
            final ProductCreateRequest productCreateRequest
    );
}
