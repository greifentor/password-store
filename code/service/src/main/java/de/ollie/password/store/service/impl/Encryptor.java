package de.ollie.password.store.service.impl;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class Encryptor {

	private static final int GCM_TAG_LENGTH = 128; // In bit.
	private static final int IV_LENGTH = 12; // Suggested for CGM.

	private final String password;
	private final String plaintext;
	private final SecureRandom random = new SecureRandom();

	Encryptor(String plaintext, String password) {
		this.password = password;
		this.plaintext = plaintext;
	}

	public String encrypt() throws Exception {
		byte[] salt = generateSalt();
		SecretKeySpec key = KeyFactory.createKey(password, salt);
		GCMParameterSpec gcmSpec = generateParameterSpec();
		Cipher cipher = createAndInitCipher(key, gcmSpec);
		byte[] encrypted = cipher.doFinal(plaintext.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(combineSaltIvAndCipherText(encrypted, salt, gcmSpec.getIV()));
	}

	private byte[] generateSalt() {
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}

	private GCMParameterSpec generateParameterSpec() {
		byte[] iv = new byte[IV_LENGTH];
		random.nextBytes(iv);
		return new GCMParameterSpec(GCM_TAG_LENGTH, iv);
	}

	private Cipher createAndInitCipher(SecretKeySpec key, GCMParameterSpec gcmSpec)
		throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);
		return cipher;
	}

	private byte[] combineSaltIvAndCipherText(byte[] encrypted, byte[] salt, byte[] iv) {
		byte[] combined = new byte[salt.length + iv.length + encrypted.length];
		System.arraycopy(salt, 0, combined, 0, salt.length);
		System.arraycopy(iv, 0, combined, salt.length, iv.length);
		System.arraycopy(encrypted, 0, combined, salt.length + iv.length, encrypted.length);
		return combined;
	}
}
