package com.example.FlightBookingSystem.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FlightBookingSystem.Model.Notification;
import com.example.FlightBookingSystem.dao.NotificationRepository;

@Service
public class NotificationService {

	private NotificationRepository notificationRepo;

	public NotificationService() {
		super();
	}

	@Autowired
	public NotificationService(NotificationRepository notificationRepo) {
		super();
		this.notificationRepo = notificationRepo;
	}
	
	public void saveNotification(Notification notification) {
		notificationRepo.save(notification);
	}
	
	public List<Notification> getNotificationForUser(Long userId) {
		return notificationRepo.getNotificationForUser(userId);
	}
	
	public int unreadNotifications(List<Notification> notifications) {
		int unreadNotifications = 0;
		for(Notification notification: notifications) {
			if(!notification.isRead())
				unreadNotifications += 1;
		}
		return unreadNotifications;
	}
	
	public void changeToReadNotifications(List<Notification> notifications) {
		for(Notification notification: notifications) {
			notification.setRead(true);
			notificationRepo.save(notification);
		}
	}
	
	public String currentTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();   
		return dtf.format(now);
	}

	public void remove(Long id) {
		notificationRepo.deleteById(id);
		
	}
	
	
}
