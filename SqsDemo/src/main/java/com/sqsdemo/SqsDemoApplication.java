package com.sqsdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication( exclude = {
        org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
        org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration.class,
        org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration.class
})
@RestController
public class SqsDemoApplication {
	
	Logger logger= LoggerFactory.getLogger(SqsDemoApplication.class);
	
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;
	
    //@Value("${cloud.aws.end-point.uri}")
	private String endpoint="https://sqs.ap-south-1.amazonaws.com/052565394086/Swati-queue";
	
  
	
	@GetMapping("/send/{message}")
    public void sendMessageToQueue(@PathVariable String message) {
        queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(message).build());
    }

    @SqsListener("Swati-queue")
    public void loadMessageFromSQS(String message)  {
        logger.info("message from SQS Queue {}",message);
    }
	public static void main(String[] args) {
		SpringApplication.run(SqsDemoApplication.class, args);
	}

}
