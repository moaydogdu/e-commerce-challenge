package org.moaydogdu.enoca_challenge.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.common.model.CustomPage;
import org.moaydogdu.enoca_challenge.product.exception.ProductNotFoundException;
import org.moaydogdu.enoca_challenge.product.model.Product;
import org.moaydogdu.enoca_challenge.product.model.dto.request.ProductPagingRequest;
import org.moaydogdu.enoca_challenge.product.model.entity.ProductEntity;
import org.moaydogdu.enoca_challenge.product.model.mapper.ProductMapper;
import org.moaydogdu.enoca_challenge.product.repository.ProductRepository;
import org.moaydogdu.enoca_challenge.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getProductById(
            final String productId
    ) {
        final ProductEntity productEntityFromDB = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("With given productID = " + productId));

        return ProductMapper.toDomainModel(productEntityFromDB);
    }

    @Override
    public CustomPage<Product> getProducts(
            final ProductPagingRequest productPagingRequest
    ) {
        final Page<ProductEntity> productEntityPage = productRepository
                .findAll(productPagingRequest.toPageable());

        if (productEntityPage.getContent().isEmpty()) {
            throw new ProductNotFoundException("Couldn't find any Product");
        }

        final List<Product> productDomainModels = ProductMapper
                .toDomainModel(productEntityPage.getContent());

        return CustomPage.of(
                productDomainModels,
                productEntityPage
        );
    }

}
