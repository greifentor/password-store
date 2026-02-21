package de.ollie.password.store.service.core.configuration;

import lombok.Generated;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Generated
@Getter
public class PasswordServiceConfiguration {

	@Value("${password.store.configuration.password-entries.file-name}")
	private String passwordEntryFileName;
}
