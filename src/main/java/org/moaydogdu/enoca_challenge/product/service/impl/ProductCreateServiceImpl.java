package org.moaydogdu.enoca_challenge.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.moaydogdu.enoca_challenge.product.exception.ProductAlreadyExistException;
import org.moaydogdu.enoca_challenge.product.model.Product;
import org.moaydogdu.enoca_challenge.product.model.dto.request.ProductCreateRequest;
import org.moaydogdu.enoca_challenge.product.model.entity.ProductEntity;
import org.moaydogdu.enoca_challenge.product.model.mapper.ProductDTOMapper;
import org.moaydogdu.enoca_challenge.product.model.mapper.ProductMapper;
import org.moaydogdu.enoca_challenge.product.repository.ProductRepository;
import org.moaydogdu.enoca_challenge.product.service.ProductCreateService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCreateServiceImpl implements ProductCreateService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(
            final ProductCreateRequest productCreateRequest
    ) {
        final ProductEntity productEntityToBeSave = ProductDTOMapper
                .mapForSaving(productCreateRequest);

        productRepository.save(productEntityToBeSave);

        return ProductMapper.toDomainModel(productEntityToBeSave);
    }

    private void checkUniquenessProductName(
            final String productName
    ) {
        if (productRepository.existsProductEntityByName(productName))
        {
            throw new ProductAlreadyExistException("There is another product with given name: " + productName);
        }
    }
}
