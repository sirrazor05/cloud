package com.load.balancer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.load.balancer.provider.DefaultProvider;
import com.load.balancer.provider.Provider;

public class RandomLoadBalancer extends AbstractLoadBalancer {

	public RandomLoadBalancer(final List<Provider> providersList, final ScheduledExecutorService executorService) {
		super(providersList, executorService);
	}

	@Override
	public String get() {
		Random rand = new Random();
		synchronized (activeProviders) {
			int upperbound = activeProviders.size();
			if (upperbound == 0) {
				System.err.println("No providers available to handle the request!");
				return "-1";
			}
			int randomIndex = rand.nextInt(upperbound);
			return activeProviders.get(randomIndex).get();
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
		final LoadBalancer loadBalancer = new RandomLoadBalancer(List.of(p1, p2, p3, p4, p5, p6, p7, p8),
				executorService);

		for (int i = 0; i < 25000; i++) {
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
