package com.example.FlightBookingSystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.FlightBookingSystem.Service.NotificationService;

@Controller
public class NotificationController {

	private NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}
	
	@GetMapping("deleteNotification")
	public String deleteNotificationWithId(@RequestParam Long id) {
		notificationService.remove(id);
		return "redirect:/passengerProfile";
	}
}
