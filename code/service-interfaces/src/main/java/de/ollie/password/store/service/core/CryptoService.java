package de.ollie.password.store.service.core;

import de.ollie.password.store.service.code.exception.CryptoException;

public interface CryptoService {
	String encrypt(String plaintext, String password) throws CryptoException;

	String decrypt(String ciphertext, String password) throws CryptoException;
}
