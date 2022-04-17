package com.fullcycle.catalogo.video.domain.exception;

public class NotEmptyException extends DomainException {
    public NotEmptyException() {
        super();
    }

    public NotEmptyException(String message) {
        super(message);
    }
}
