package de.ollie.password.store.service.core.exception;

public class FileSystemException extends RuntimeException {

	public FileSystemException(String message, Exception cause) {
		super(message, cause);
	}
}
