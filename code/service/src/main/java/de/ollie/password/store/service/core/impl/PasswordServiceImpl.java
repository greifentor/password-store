package de.ollie.password.store.service.core.impl;

import de.ollie.password.store.service.code.model.PasswordEntry;
import de.ollie.password.store.service.core.PasswordService;
import jakarta.inject.Named;
import java.util.List;

@Named
class PasswordServiceImpl implements PasswordService {

	@Override
	public List<PasswordEntry> findAllEntries() {
		// TODO Auto-generated method stub
		return List.of(new PasswordEntry().setLabel("Account0"), new PasswordEntry().setLabel("Account1"));
	}
}
