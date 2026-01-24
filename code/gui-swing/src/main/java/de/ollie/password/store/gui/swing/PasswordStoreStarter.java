package de.ollie.password.store.gui.swing;

import javax.swing.SwingUtilities;
import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.password.store")
// @EntityScan("de.ollie.password.store.persistence.jpa.dbo")
// @EnableJpaRepositories(basePackages = "de.ollie.password.store.persistence.jpa.repository")
public class PasswordStoreStarter {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PasswordStoreStarter.class);
		app.setHeadless(false); // GUI erlauben
		ConfigurableApplicationContext context = app.run(args);
		PasswordStoreMainFrame mainFrame = context.getBean(PasswordStoreMainFrame.class);
		SwingUtilities.invokeLater(mainFrame::showFrame);
	}
}
