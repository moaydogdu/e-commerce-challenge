package org.moaydogdu.enoca_challenge.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.product.exception.ProductNotFoundException;
import org.moaydogdu.enoca_challenge.product.model.entity.ProductEntity;
import org.moaydogdu.enoca_challenge.product.repository.ProductRepository;
import org.moaydogdu.enoca_challenge.product.service.ProductDeleteService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDeleteServiceImpl implements ProductDeleteService {

    private final ProductRepository productRepository;

    @Override
    public void deleteProductById(
            final String productId
    ) {
        final ProductEntity productEntityToBeDelete = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("With given productID = " + productId));

        productRepository.delete(productEntityToBeDelete);
    }
}
