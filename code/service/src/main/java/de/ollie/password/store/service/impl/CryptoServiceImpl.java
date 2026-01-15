package de.ollie.password.store.service.impl;

import jakarta.inject.Named;

@Named
class CryptoServiceImpl {

	public String encrypt(String plaintext, String password) throws Exception {
		return new Encryptor(plaintext, password).encrypt();
	}

	public static String decrypt(String ciphertext, String password) throws Exception {
		return new Decryptor(ciphertext, password).decrypt();
	}
}
