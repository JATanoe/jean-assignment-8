# Number Frequency Counter

## Overview
This Java application demonstrates concurrent programming concepts by processing a large dataset of numbers and counting their frequency. It uses Java's CompletableFuture and ExecutorService to efficiently process 1,000,000 numbers in parallel.

## Features
- Reads a large dataset of numbers from a text file
- Processes the data in batches of 1,000 numbers (simulating API calls)
- Uses asynchronous processing with CompletableFuture for improved performance
- Counts the frequency of each number in the dataset
- Displays the results sorted by number

## Requirements
- Java 8 or higher
- A text file named `output.txt` in the root directory containing the numbers to process

## Project Structure
- `src/com/coderscampus/Application.java`: The main entry point of the application
- `src/com/coderscampus/processor/NumberFrequencyProcessor.java`: Contains the logic for processing the numbers and counting their frequency
- `output.txt`: Contains 1,000,000 numbers (one per line) to be processed

## How to Run
1. Ensure you have the `output.txt` file in the root directory of the project
2. Compile the Java files
3. Run the Application class

```bash
javac src/com/coderscampus/Application.java src/com/coderscampus/processor/NumberFrequencyProcessor.java
java -cp src com.coderscampus.Application
```

## Input Format
The application expects a file named `output.txt` in the root directory. This file should contain one integer per line. The application is designed to process 1,000,000 numbers.

## Output
The application will print the frequency of each number in the format:
```
0=X, 1=Y, 2=Z, ...
```
Where X, Y, Z, etc. are the counts of how many times each number appears in the dataset.

## Implementation Details
- The application simulates API calls by processing the data in batches of 1,000 numbers
- Each batch retrieval includes a 500ms delay to simulate network latency
- 1,000 concurrent requests are made to retrieve all 1,000,000 numbers
- CompletableFuture is used to handle these requests asynchronously
- After all data is retrieved, the application counts the frequency of each number and displays the results

## Performance Considerations
The application demonstrates efficient handling of large datasets through:
- Parallel processing using CompletableFuture
- Asynchronous execution with ExecutorService
- Efficient stream operations for data aggregation and counting