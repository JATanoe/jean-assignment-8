package com.coderscampus.assignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Assignment8 {
	private List<Integer> numbers = null;
	private AtomicInteger i = new AtomicInteger(0);
	
	private List<CompletableFuture<List<Integer>>> futures = new ArrayList<>();
	private ExecutorService executor = Executors.newCachedThreadPool();

	public Assignment8() {
		try {
			// Make sure you download the output.txt file for Assignment 8
			// and place the file in the root of your Java project
			numbers = Files.readAllLines(Paths.get("output.txt")).stream().map(n -> Integer.parseInt(n))
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will return the numbers that you'll need to process from the list
	 * of Integers. However, it can only return 1000 records at a time. You will
	 * need to call this method 1,000 times in order to retrieve all 1,000,000
	 * numbers from the list
	 * 
	 * @return Integers from the parsed txt file, 1,000 numbers at a time
	 */
	public List<Integer> getNumbers() {
		int start, end;
		synchronized (i) {
			start = i.get();
			end = i.addAndGet(1000);

			System.out.println("Starting to fetch records " + start + " to " + (end));
		}
		// force thread to pause for half a second to simulate actual Http / API traffic
		// delay
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}

		List<Integer> newList = new ArrayList<>();
		IntStream.range(start, end).forEach(n -> {
			newList.add(numbers.get(n));
		});
		System.out.println("Done Fetching records " + start + " to " + (end));
		return newList;
	}

	public void getData() {
		for (int i = 0; i < 1000; i++) {
            CompletableFuture<List<Integer>> future = CompletableFuture.supplyAsync(() -> getNumbers(), executor);
            futures.add(future);
		}
	}
	
	public void countAndPrint() {
		System.out.println(
	        futures.stream()
	        .map(CompletableFuture::join)           
	        .flatMap(List::stream)                  
	        .collect(Collectors.groupingBy(n -> n, Collectors.counting())) 
	        .entrySet().stream().sorted(Map.Entry.comparingByKey())
	        .map(entry -> entry.getKey() + "=" + entry.getValue())
	        .collect(Collectors.joining(", "))
        );
	}

}