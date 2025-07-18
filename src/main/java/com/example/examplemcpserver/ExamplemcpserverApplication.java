package com.example.examplemcpserver;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExamplemcpserverApplication {
	
	@Autowired
	private BookTool bookTool;

	public static void main(String[] args) {
		SpringApplication.run(ExamplemcpserverApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider mathTools() {
		return MethodToolCallbackProvider.builder()
				.toolObjects(new MathTool())
				.build();
	}

	@Bean
	public ToolCallbackProvider dateTools() {
		return MethodToolCallbackProvider.builder()
				.toolObjects(new DateTool())
				.build();
	}
	
	@Bean
	public ToolCallbackProvider bookTools() {
		return MethodToolCallbackProvider.builder()
				.toolObjects(bookTool)
				.build();
	}
}
