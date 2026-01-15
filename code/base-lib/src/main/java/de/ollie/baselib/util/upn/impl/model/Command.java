package de.ollie.baselib.util.upn.impl.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Generated
@Getter
@ToString
public abstract class Command implements Word {

	private String token;
}
