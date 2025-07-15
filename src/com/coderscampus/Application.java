package com.coderscampus;

import com.coderscampus.processor.NumberFrequencyProcessor;

public class Application {

	public static void main(String[] args) {
		
        NumberFrequencyProcessor processor = new NumberFrequencyProcessor();
        processor.getData();
        processor.countAndPrint();

	}

}
