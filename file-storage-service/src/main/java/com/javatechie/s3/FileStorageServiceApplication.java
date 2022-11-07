package com.javatechie.s3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        exclude = {
                org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration.class
        }
)
public class FileStorageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileStorageServiceApplication.class, args);
	}

}
