package de.ollie.password.store.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
