package io.ukids.generalmeetingmanagementsystem;

import io.ukids.generalmeetingmanagementsystem.admin.service.member.MemberInitService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class GeneralMeetingManagementSystemApplication {

	private final MemberInitService memberInitService;

	public static void main(String[] args) {
		SpringApplication.run(GeneralMeetingManagementSystemApplication.class, args);
	}

	@PostConstruct
	public void initAdmin() {
		memberInitService.init();
	}

}
