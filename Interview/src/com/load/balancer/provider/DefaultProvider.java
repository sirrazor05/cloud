package com.load.balancer.provider;

import java.util.Objects;
import java.util.Random;

public class DefaultProvider implements Provider {

	private final String id;

	public DefaultProvider(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public String get() {
		return getId();
	}

	@Override
	public Boolean check() {
		Random rand = new Random();
		int randomNbr = rand.nextInt(2);
		if (randomNbr == 0) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		DefaultProvider provider = (DefaultProvider) o;
		return Objects.equals(id, provider.getId());
	}

	@Override
	public String toString() {
		return getId();
	}

}
