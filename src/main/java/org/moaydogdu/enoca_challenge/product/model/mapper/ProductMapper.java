package org.moaydogdu.enoca_challenge.product.model.mapper;

import lombok.experimental.UtilityClass;
import org.moaydogdu.enoca_challenge.product.model.Product;
import org.moaydogdu.enoca_challenge.product.model.entity.ProductEntity;

import java.util.List;

@UtilityClass
public class ProductMapper {

    public static Product toDomainModel(
            final ProductEntity productEntity
    ) {
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .amount(productEntity.getAmount())
                .unitPrice(productEntity.getUnitPrice())
                .createdAt(productEntity.getCreatedAt())
                .createdBy(productEntity.getCreatedBy())
                .updatedAt(productEntity.getUpdatedAt())
                .updatedBy(productEntity.getUpdatedBy())
                .build();
    }

    public static List<Product> toDomainModel(
            final List<ProductEntity> productEntities
    ) {
        return productEntities.stream()
                .map(ProductMapper::toDomainModel)
                .toList();
    }

    public static ProductEntity toEntity(
            final Product productDomainModel
    ) {
        return ProductEntity.builder()
                .id(productDomainModel.getId())
                .name(productDomainModel.getName())
                .amount(productDomainModel.getAmount())
                .unitPrice(productDomainModel.getUnitPrice())
                .createdAt(productDomainModel.getCreatedAt())
                .createdBy(productDomainModel.getCreatedBy())
                .updatedAt(productDomainModel.getUpdatedAt())
                .updatedBy(productDomainModel.getUpdatedBy())
                .build();
    }
}
