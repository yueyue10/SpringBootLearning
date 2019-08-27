package com.forezp;

import com.forezp.service.Service;
import com.forezp.service.DataConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest("data.message=HelloS")
public class LibaryApplicationTests {

	@Autowired
	private Service service;

	@Test
	public void contextLoads() {
		System.out.println(service.message());
		assertThat(service.message()).isNotNull();
	}

	@SpringBootApplication
	@Import(DataConfiguration.class)
	static class TestConfiguration {
	}

}
