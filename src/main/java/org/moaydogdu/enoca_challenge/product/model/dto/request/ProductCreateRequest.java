package org.moaydogdu.enoca_challenge.product.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {

    @Size(
            min = 1,
            message = "Product name can't be blank."
    )
    private String name;

    @DecimalMin(
            value = "0.0001",
            message = "Amount must be bigger than 0"
    )
    private BigDecimal amount;

    @DecimalMin(
            value = "0.0001",
            message = "Unit Price must be bigger than 0"
    )
    private BigDecimal unitPrice;
}
