package com.example.samplecommerce;

import org.springframework.boot.SpringApplication;

public class TestSampleCommerceApplication {
	public static void main(String[] args) {
		SpringApplication.from(SampleCommerceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}
}
