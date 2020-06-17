package com.load.balancer;

import com.load.balancer.provider.Provider;

public interface LoadBalancer {

	void include(final Provider p);

	void exclude(final Provider p);

	String get();

}
