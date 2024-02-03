package org.moaydogdu.enoca_challenge.auth.exception;

import java.io.Serial;

public class UserStatusNotValidException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -1750784171230055743L;

    private static final String DEFAULT_MESSAGE = """
            User status is not valid!
            """;

    public UserStatusNotValidException()
    {
        super(DEFAULT_MESSAGE);
    }

    public UserStatusNotValidException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
