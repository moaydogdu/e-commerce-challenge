package org.moaydogdu.enoca_challenge.cart.exception;

import java.io.Serial;

public class CartNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 2730265834702386253L;

    private static final String DEFAULT_MESSAGE = """
            Cart not found!
            """;

    public CartNotFoundException(){
        super(DEFAULT_MESSAGE);
    }

    public CartNotFoundException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
