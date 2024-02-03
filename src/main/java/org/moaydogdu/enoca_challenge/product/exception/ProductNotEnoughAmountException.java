package org.moaydogdu.enoca_challenge.product.exception;

import java.io.Serial;

public class ProductNotEnoughAmountException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 123L;

    private static final String DEFAULT_MESSAGE = """
            Not enough amount product!
            """;

    public ProductNotEnoughAmountException()
    {
        super(DEFAULT_MESSAGE);
    }

    public ProductNotEnoughAmountException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
