package org.moaydogdu.enoca_challenge.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.product.exception.ProductAlreadyExistException;
import org.moaydogdu.enoca_challenge.product.exception.ProductNotFoundException;
import org.moaydogdu.enoca_challenge.product.model.Product;
import org.moaydogdu.enoca_challenge.product.model.dto.request.ProductUpdateRequest;
import org.moaydogdu.enoca_challenge.product.model.entity.ProductEntity;
import org.moaydogdu.enoca_challenge.product.model.mapper.ProductDTOMapper;
import org.moaydogdu.enoca_challenge.product.model.mapper.ProductMapper;
import org.moaydogdu.enoca_challenge.product.repository.ProductRepository;
import org.moaydogdu.enoca_challenge.product.service.ProductUpdateService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductUpdateServiceImpl implements ProductUpdateService {

    private final ProductRepository productRepository;

    @Override
    public Product updateProductById(
            final ProductUpdateRequest productUpdateRequest,
            final String productId
    ) {
        final ProductEntity productEntityToBeUpdate = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("With given productID = " + productId));

        if (Boolean.FALSE.equals(productEntityToBeUpdate.getName().equals(productUpdateRequest.getName())))
        {
            this.checkProductNameUniqueness(productUpdateRequest.getName());
        }

        ProductDTOMapper.mapForUpdating(productEntityToBeUpdate,productUpdateRequest);

        productRepository.save(productEntityToBeUpdate);

        return ProductMapper.toDomainModel(productEntityToBeUpdate);
    }

    private void checkProductNameUniqueness(
            final String productName
    ) {
        if (productRepository.existsProductEntityByName(productName))
        {
            throw new ProductAlreadyExistException("With given product name = " + productName);
        }
    }
}
