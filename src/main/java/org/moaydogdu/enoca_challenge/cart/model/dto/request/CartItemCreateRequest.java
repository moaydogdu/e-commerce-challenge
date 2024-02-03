package org.moaydogdu.enoca_challenge.cart.model.dto.request;

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
public class CartItemCreateRequest {

    @Size(
            min = 36,
            max = 36
    )
    private String productId;

    @DecimalMin(
            value = "0.0001",
            message = "Amount must be bigger than 0"
    )
    private BigDecimal amount;

}
