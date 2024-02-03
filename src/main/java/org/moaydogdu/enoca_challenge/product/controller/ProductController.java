package org.moaydogdu.enoca_challenge.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.moaydogdu.enoca_challenge.common.model.CustomPage;
import org.moaydogdu.enoca_challenge.common.model.dto.response.CustomPagingResponse;
import org.moaydogdu.enoca_challenge.common.model.dto.response.EnocaResponse;
import org.moaydogdu.enoca_challenge.product.model.Product;
import org.moaydogdu.enoca_challenge.product.model.dto.request.ProductCreateRequest;
import org.moaydogdu.enoca_challenge.product.model.dto.request.ProductPagingRequest;
import org.moaydogdu.enoca_challenge.product.model.dto.request.ProductUpdateRequest;
import org.moaydogdu.enoca_challenge.product.model.dto.response.ProductResponse;
import org.moaydogdu.enoca_challenge.product.model.mapper.ProductDTOMapper;
import org.moaydogdu.enoca_challenge.product.service.ProductCreateService;
import org.moaydogdu.enoca_challenge.product.service.ProductDeleteService;
import org.moaydogdu.enoca_challenge.product.service.ProductService;
import org.moaydogdu.enoca_challenge.product.service.ProductUpdateService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductCreateService productCreateService;
    private final ProductService productService;
    private final ProductUpdateService productUpdateService;
    private final ProductDeleteService productDeleteService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public EnocaResponse<String> createProduct(
            @RequestBody @Valid final ProductCreateRequest productCreateRequest
    ) {
        final Product createdProduct = productCreateService
                .createProduct(productCreateRequest);

        return EnocaResponse.successOf(createdProduct.getId());
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    public EnocaResponse<ProductResponse> getProductById(
            @PathVariable @UUID final String productId
    ) {
        final Product product = productService
                .getProductById(productId);

        final ProductResponse productResponse = ProductDTOMapper
                .toResponse(product);

        return EnocaResponse.successOf(productResponse);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    public EnocaResponse<CustomPagingResponse<ProductResponse>> getProducts(
            @RequestBody @Valid final ProductPagingRequest productPagingRequest
    ) {
        final CustomPage<Product> productPage = productService
                .getProducts(productPagingRequest);

        final CustomPagingResponse<ProductResponse> productPagingResponse =
                ProductDTOMapper.toPagingResponse(productPage);

        return EnocaResponse.successOf(productPagingResponse);
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public EnocaResponse<ProductResponse> updatedProductById(
            @RequestBody @Valid final ProductUpdateRequest productUpdateRequest,
            @PathVariable @UUID final String productId
    ) {
        final Product updatedProduct = productUpdateService
                .updateProductById(productUpdateRequest,productId);

        final ProductResponse productResponse = ProductDTOMapper
                .toResponse(updatedProduct);

        return EnocaResponse.successOf(productResponse);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public EnocaResponse<Void> deleteProductById(
            @PathVariable @UUID final String productId
    ) {
        productDeleteService.deleteProductById(productId);

        return EnocaResponse.SUCCESS;
    }

}
