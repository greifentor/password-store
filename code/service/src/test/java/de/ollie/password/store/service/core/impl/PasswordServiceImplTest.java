package de.ollie.password.store.service.core.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PasswordServiceImplTest {

	@InjectMocks
	private PasswordServiceImpl unitUnderTest;

	@Nested
	class findAllEntries {

		@Test
		void returnsAnEmptyList() {
			assertTrue(unitUnderTest.findAllEntries().isEmpty());
		}
	}
}
