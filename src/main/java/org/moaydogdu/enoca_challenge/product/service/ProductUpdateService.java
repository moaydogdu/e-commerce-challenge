package org.moaydogdu.enoca_challenge.product.service;

import org.moaydogdu.enoca_challenge.product.model.Product;
import org.moaydogdu.enoca_challenge.product.model.dto.request.ProductUpdateRequest;

public interface ProductUpdateService {
    Product updateProductById(
            final ProductUpdateRequest productUpdateRequest,
            final String productId
    );
}
