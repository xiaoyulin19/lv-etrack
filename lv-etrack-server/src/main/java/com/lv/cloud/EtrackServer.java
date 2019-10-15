package com.lv.cloud;

import com.lv.cloud.kafka.annotation.EnableLvKafka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableLvKafka
@ComponentScan(basePackages = {"com.lv.cloud.etrack"})
public class EtrackServer {
	
	public static void main(String[] args) {
        SpringApplication.run(EtrackServer.class,args);
    }

}
