package com.load.balancer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.load.balancer.provider.Provider;

public abstract class AbstractLoadBalancer implements LoadBalancer {

	protected final List<Provider> registeredProviders;
	protected final List<Provider> activeProviders = Collections.synchronizedList(new ArrayList<>());
	protected final Map<Provider, Integer> failCountMap = new HashMap<>();

	public AbstractLoadBalancer(final List<Provider> providers, final ScheduledExecutorService executorService) {
		this.registeredProviders = List.copyOf(providers);
		providers.forEach(p -> activeProviders.add(p));
		providers.forEach(p -> failCountMap.put(p, 0));

		final Runnable checkHealth = () -> {
			Iterator<Provider> iter = registeredProviders.iterator();
			while (iter.hasNext()) {
				Provider p = iter.next();
				if (!p.check()) {
					int prevCount = failCountMap.get(p);
					if (prevCount == 0) {
						exclude(p);
					}
					if (prevCount < 2) {
						failCountMap.put(p, prevCount + 1);
					}
				} else {
					int prevCount = failCountMap.get(p);
					if (prevCount == 1) {
						failCountMap.put(p, 0);
						include(p);
					} else if (prevCount > 1) {
						failCountMap.put(p, prevCount - 1);
					}
				}
			}
			System.out.println("active providers: " + activeProviders);
		};
		executorService.scheduleAtFixedRate(checkHealth, 0, 1, TimeUnit.SECONDS);
	}

	@Override
	public void include(Provider p) {
		activeProviders.add(p);
	}

	@Override
	public void exclude(Provider p) {
		activeProviders.remove(p);
	}

}
