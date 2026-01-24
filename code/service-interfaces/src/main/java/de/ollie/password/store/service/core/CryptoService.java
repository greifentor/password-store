package de.ollie.password.store.service.core;

public interface CryptoService {
	String encrypt(String plaintext, String password) throws Exception;

	String decrypt(String ciphertext, String password) throws Exception;
}
