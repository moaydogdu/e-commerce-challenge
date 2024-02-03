package org.moaydogdu.enoca_challenge.admin.exception;

import java.io.Serial;

public class AdminAlreadyExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -6167983933328177800L;

    private static final String DEFAULT_MESSAGE = """
            Admin already exist!
            """;

    public AdminAlreadyExistException()
    {
        super(DEFAULT_MESSAGE);
    }

    public AdminAlreadyExistException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
