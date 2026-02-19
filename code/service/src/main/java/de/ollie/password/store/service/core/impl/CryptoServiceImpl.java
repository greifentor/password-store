package de.ollie.password.store.service.core.impl;

import de.ollie.password.store.service.code.exception.CryptoException;
import de.ollie.password.store.service.core.CryptoService;
import jakarta.inject.Named;

@Named
class CryptoServiceImpl implements CryptoService {

	@Override
	public String encrypt(String plaintext, String password) throws CryptoException {
		try {
			return new Encryptor(plaintext, password).encrypt();
		} catch (Exception e) {
			throw new CryptoException("Something went wrong while encrypting", e);
		}
	}

	@Override
	public String decrypt(String ciphertext, String password) throws CryptoException {
		try {
			return new Decryptor(ciphertext, password).decrypt();
		} catch (Exception e) {
			throw new CryptoException("Something went wrong while encrypting", e);
		}
	}
}
