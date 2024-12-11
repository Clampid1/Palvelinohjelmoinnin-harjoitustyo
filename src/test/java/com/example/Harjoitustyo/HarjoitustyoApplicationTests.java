package com.example.Harjoitustyo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
class HarjoitustyoApplicationTests {

	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private TaskerRepository taskerRepository;
	@Autowired
	private TaskRepository taskRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void addTasker() {
		Tasker tasker = new Tasker();
		tasker.setUsername("Valle");
		tasker.setPassword("Password");
		taskerRepository.save(tasker);

		Tasker tasker1 = taskerRepository.findByUsername("Valle");

		assert tasker1 != null;
		assert tasker1.getUsername().equals("Valle");
		assert tasker1.getPassword().equals("Password");

	}

	@Test
	void addStatuses() {
		Status status1 = new Status();
		status1.setStatus("Waiting");
		statusRepository.save(status1);
		Status status2 = new Status();
		status2.setStatus("Doing");
		statusRepository.save(status2);
		Status status3 = new Status();
		status3.setStatus("Reviewing");
		statusRepository.save(status3);
		Status status4 = new Status();
		status4.setStatus("Complete");
		statusRepository.save(status4);

		List<Status> statuses = statusRepository.findAll();
		assert !statuses.isEmpty();
		assert statuses.get(0).getStatus().equals("Waiting");
		assert statuses.get(1).getStatus().equals("Doing");
		assert statuses.get(2).getStatus().equals("Reviewing");
		assert statuses.get(3).getStatus().equals("Complete");


	}
}
