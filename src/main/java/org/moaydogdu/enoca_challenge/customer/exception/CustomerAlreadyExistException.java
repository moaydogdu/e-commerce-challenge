package org.moaydogdu.enoca_challenge.customer.exception;

import java.io.Serial;

public class CustomerAlreadyExistException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1923347727545593905L;

    private static final String DEFAULT_MESSAGE = """
            Customer already exist!
            """;

    public CustomerAlreadyExistException()
    {
        super(DEFAULT_MESSAGE);
    }

    public CustomerAlreadyExistException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
