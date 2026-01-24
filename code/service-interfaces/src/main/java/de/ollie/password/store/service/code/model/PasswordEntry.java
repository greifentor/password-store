package de.ollie.password.store.service.code.model;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Getter
public class PasswordEntry {

	private String label;
	private String password;
}
