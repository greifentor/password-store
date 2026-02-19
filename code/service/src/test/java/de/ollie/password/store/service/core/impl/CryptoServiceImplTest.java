package de.ollie.password.store.service.core.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.ollie.password.store.service.code.exception.CryptoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CryptoServiceImplTest {

	private static final String PASSWORD = "password";
	private static final String TEXT_TO_CRYPT = "A text with $%&§_ :o)";

	@InjectMocks
	private CryptoServiceImpl unitUnderTest;

	@Test
	void encryptsAndDecryptsAStringCorrectly() throws Exception {
		assertEquals(TEXT_TO_CRYPT, unitUnderTest.decrypt(unitUnderTest.encrypt(TEXT_TO_CRYPT, PASSWORD), PASSWORD));
	}

	@Test
	void throwsACryptoException_whenAnExceptionIsThrown_whileDecryption() {
		assertThrows(CryptoException.class, () -> unitUnderTest.decrypt(TEXT_TO_CRYPT, PASSWORD));
	}

	@Test
	void throwsACryptoException_whenAnExceptionIsThrown_whileEncryption() {
		assertThrows(CryptoException.class, () -> unitUnderTest.encrypt(TEXT_TO_CRYPT, null));
	}
}
