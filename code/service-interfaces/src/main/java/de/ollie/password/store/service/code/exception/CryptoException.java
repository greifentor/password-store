package de.ollie.password.store.service.code.exception;

public class CryptoException extends RuntimeException {

	public CryptoException(String message, Exception exception) {
		super(message, exception);
	}
}
