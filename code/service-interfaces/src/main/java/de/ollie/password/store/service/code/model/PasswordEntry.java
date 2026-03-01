package de.ollie.password.store.service.code.model;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class PasswordEntry {

	private String label;
	private String password;
}
