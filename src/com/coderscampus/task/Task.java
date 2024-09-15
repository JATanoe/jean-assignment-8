package com.coderscampus.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.coderscampus.assignment.Assignment8;

public class Task {

	public void getData() {

		ExecutorService cpuBoundTask = Executors.newSingleThreadExecutor();
		ExecutorService ioBoundTask = Executors.newCachedThreadPool();

		for (int i = 0; i < 1000; i++) {
			CompletableFuture.supplyAsync(() -> new Assignment8(), cpuBoundTask)
					.thenApplyAsync(task -> task.getNumbers(), ioBoundTask)
					.thenApplyAsync(taskList -> taskList.stream()
							.collect(Collectors.groupingBy(n -> n, Collectors.counting())),
							ioBoundTask)
					.thenApplyAsync(numbersMap -> numbersMap.entrySet()
							.stream()
							.sorted(Map.Entry.comparingByKey())
							.map(entry -> entry.getKey() + "=" + entry.getValue())
							.collect(Collectors.joining(", ")),
							ioBoundTask)
					.thenAcceptAsync(System.out::println, cpuBoundTask);
		}
		
	}

}
