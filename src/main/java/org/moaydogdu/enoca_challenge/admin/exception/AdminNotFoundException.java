package org.moaydogdu.enoca_challenge.admin.exception;

import java.io.Serial;

public class AdminNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -6167983933328177800L;

    private static final String DEFAULT_MESSAGE = """
            Admin not found!
            """;

    public AdminNotFoundException()
    {
        super(DEFAULT_MESSAGE);
    }

    public AdminNotFoundException(
            final String message
    ) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
