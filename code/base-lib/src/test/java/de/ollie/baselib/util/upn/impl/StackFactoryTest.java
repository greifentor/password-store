package de.ollie.baselib.util.upn.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StackFactoryTest {

	@InjectMocks
	private StackFactory unitUnderTest;

	@Nested
	class create {

		@Test
		void returnsAnObject() {
			assertNotNull(unitUnderTest.create());
		}

		@Test
		void returnsANewObject_inEachCall() {
			assertNotSame(unitUnderTest.create(), unitUnderTest.create());
		}
	}
}
