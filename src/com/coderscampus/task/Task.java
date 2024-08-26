package com.coderscampus.task;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.coderscampus.assignment.Assignment8;

public class Task {

	ConcurrentHashMap<Integer, Integer> frequencyMap = new ConcurrentHashMap<>();

	public void getData() {

		ExecutorService cpuBoundTask = Executors.newSingleThreadExecutor();
		ExecutorService ioBoundTask = Executors.newCachedThreadPool();

		for (int i = 0; i < 1000; i++) {
			CompletableFuture.supplyAsync(() -> new Assignment8(), ioBoundTask)
					.thenApplyAsync(task -> task.getNumbers(), cpuBoundTask)
					.thenApplyAsync(list -> list.stream()
									.collect(Collectors.groupingBy(n -> n, Collectors.counting())),
							ioBoundTask)
					.thenAcceptAsync(numbersMap -> numbersMap
									.entrySet().stream()
									.sorted(Map.Entry.comparingByKey())
									.forEach(entry -> System.out.println(entry.getKey() + "=" + entry.getValue())),
							ioBoundTask);
		}

	}

}
