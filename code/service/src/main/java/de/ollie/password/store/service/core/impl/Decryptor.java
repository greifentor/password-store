package de.ollie.password.store.service.core.impl;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class Decryptor {

	private static final int GCM_TAG_LENGTH = 128; // In bit.
	private static final int IV_LENGTH = 12; // Suggested for CGM.

	private final String password;
	private final String ciphertext;
	private final SecureRandom random = new SecureRandom();

	Decryptor(String ciphertext, String password) {
		this.password = password;
		this.ciphertext = ciphertext;
	}

	public String decrypt() throws Exception {
		byte[] combined = Base64.getDecoder().decode(ciphertext);
		byte[] salt = new byte[16];
		byte[] iv = new byte[IV_LENGTH];
		byte[] encrypted = new byte[combined.length - salt.length - iv.length];
		System.arraycopy(combined, 0, salt, 0, salt.length);
		System.arraycopy(combined, salt.length, iv, 0, iv.length);
		System.arraycopy(combined, salt.length + iv.length, encrypted, 0, encrypted.length);
		SecretKeySpec key = KeyFactory.createKey(password, salt);
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(GCM_TAG_LENGTH, iv));
		byte[] decrypted = cipher.doFinal(encrypted);
		return new String(decrypted, "UTF-8");
	}
}
