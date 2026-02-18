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
		return List.of(
			new PasswordEntry()
				.setLabel("Account0")
				.setPassword("oXFcOPCT6NzJb/QLXLuf3F2B6RNGzoGa/wz8GvfLrzFZCb8tqW74HXRTs6z3vsxhAzg71Us="),
			new PasswordEntry()
				.setLabel("Account1")
				.setPassword("cwzGh5rRDhW3GCil+N18HByKpsaGswqtsOl0tO5/qgcYyEhoGracGmyIc+X2c5U379c9YOU=")
		);
	}
}
