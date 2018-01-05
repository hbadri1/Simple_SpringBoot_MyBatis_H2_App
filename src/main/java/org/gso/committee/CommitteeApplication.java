package org.gso.committee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.gso.committee.mapper")
public class CommitteeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommitteeApplication.class, args);
	}
}
