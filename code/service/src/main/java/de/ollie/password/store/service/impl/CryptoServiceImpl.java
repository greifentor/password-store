package de.ollie.password.store.service.impl;

import de.ollie.password.store.service.CryptoService;
import jakarta.inject.Named;

@Named
class CryptoServiceImpl implements CryptoService {

	@Override
	public String encrypt(String plaintext, String password) throws Exception {
		return new Encryptor(plaintext, password).encrypt();
	}

	@Override
	public String decrypt(String ciphertext, String password) throws Exception {
		return new Decryptor(ciphertext, password).decrypt();
	}
}
