package com.load.balancer;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.load.balancer.provider.DefaultProvider;
import com.load.balancer.provider.Provider;

public class RoundRobinLoadBalancer extends AbstractLoadBalancer {

	public RoundRobinLoadBalancer(final List<Provider> providersList, final ScheduledExecutorService executorService) {
		super(providersList, executorService);
	}

	private volatile int nextIndex = 0;

	@Override
	public String get() {
		synchronized (activeProviders) {
			if (activeProviders.size() == 0) {
				nextIndex = 0;
				System.err.println("No providers available to handle the request!");
				return "-1";
			}
			if (nextIndex < activeProviders.size()) {
				nextIndex = nextIndex + 1;
				return activeProviders.get(nextIndex - 1).get();
			} else {
				nextIndex = 1;
				return activeProviders.get(nextIndex - 1).get();
			}
		}
	}

	public static void main(String[] args) throws IllegalAccessException, InterruptedException {
		Provider p1 = new DefaultProvider("1");
		Provider p2 = new DefaultProvider("2");
		Provider p3 = new DefaultProvider("3");
		Provider p4 = new DefaultProvider("4");
		Provider p5 = new DefaultProvider("5");
		Provider p6 = new DefaultProvider("6");
		Provider p7 = new DefaultProvider("7");
		Provider p8 = new DefaultProvider("8");

		final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		final LoadBalancer loadBalancer = new RoundRobinLoadBalancer(List.of(p1, p2, p3, p4, p5, p6, p7, p8),
				executorService);

		for (int i = 0; i < 2500; i++) {
			Thread.sleep(1000);
			System.out.println(loadBalancer.get());
			System.out.println(loadBalancer.get());
			System.out.println(loadBalancer.get());
			System.out.println(loadBalancer.get());
			System.out.println(loadBalancer.get());
			System.out.println(loadBalancer.get());
			System.out.println(loadBalancer.get());
			System.out.println();
		}

		executorService.shutdown();
	}

}
