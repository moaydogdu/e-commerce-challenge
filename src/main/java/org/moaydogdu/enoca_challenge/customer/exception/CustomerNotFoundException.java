package org.moaydogdu.enoca_challenge.customer.exception;

import java.io.Serial;

public class CustomerNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 3782268882698990529L;

    private static final String DEFAULT_MESSAGE = """
            Specified Customer not found!
            """;

    public CustomerNotFoundException()
    {
        super(DEFAULT_MESSAGE);
    }

    public CustomerNotFoundException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
