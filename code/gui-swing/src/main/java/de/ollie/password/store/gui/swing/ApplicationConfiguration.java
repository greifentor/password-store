package de.ollie.password.store.gui.swing;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
class ApplicationConfiguration {

	@Value("${app.version}")
	private String version;
}
