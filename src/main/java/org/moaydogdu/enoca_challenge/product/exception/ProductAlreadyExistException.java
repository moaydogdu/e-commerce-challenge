package org.moaydogdu.enoca_challenge.product.exception;

import java.io.Serial;

public class ProductAlreadyExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -8138340348762416963L;

    private static final String DEFAULT_MESSAGE = """
            Product already exist!
            """;

    public ProductAlreadyExistException()
    {
        super(DEFAULT_MESSAGE);
    }

    public ProductAlreadyExistException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
