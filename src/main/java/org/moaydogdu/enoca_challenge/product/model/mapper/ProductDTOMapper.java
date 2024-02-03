package org.moaydogdu.enoca_challenge.product.model.mapper;

import lombok.experimental.UtilityClass;
import org.moaydogdu.enoca_challenge.common.model.CustomPage;
import org.moaydogdu.enoca_challenge.common.model.dto.response.CustomPagingResponse;
import org.moaydogdu.enoca_challenge.product.model.Product;
import org.moaydogdu.enoca_challenge.product.model.dto.request.ProductCreateRequest;
import org.moaydogdu.enoca_challenge.product.model.dto.request.ProductUpdateRequest;
import org.moaydogdu.enoca_challenge.product.model.dto.response.ProductResponse;
import org.moaydogdu.enoca_challenge.product.model.entity.ProductEntity;

@UtilityClass
public class ProductDTOMapper {

    public static ProductEntity mapForSaving(
            final ProductCreateRequest productCreateRequest
    ) {
        return ProductEntity.builder()
                .name(productCreateRequest.getName())
                .amount(productCreateRequest.getAmount())
                .unitPrice(productCreateRequest.getUnitPrice())
                .build();
    }

    public static ProductResponse toResponse(
            final Product product
    ) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .amount(product.getAmount())
                .unitPrice(product.getUnitPrice())
                .build();
    }

    public static CustomPagingResponse<ProductResponse> toPagingResponse(
            final CustomPage<Product> productPage
    ) {
        return CustomPagingResponse.<ProductResponse>builder()
                .of(productPage)
                .content(
                        productPage.getContent() == null ? null
                                : productPage.getContent().stream()
                                .map(ProductDTOMapper::toResponse)
                                .toList()
                )
                .build();
    }

    public static void mapForUpdating(
            final ProductEntity productEntityToBeUpdate,
            final ProductUpdateRequest productUpdateRequest
    ) {
        productEntityToBeUpdate.setName(productUpdateRequest.getName());
        productEntityToBeUpdate.setAmount(productUpdateRequest.getAmount());
        productEntityToBeUpdate.setUnitPrice(productUpdateRequest.getUnitPrice());
    }
}
