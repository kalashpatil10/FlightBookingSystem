package com.example.FlightBookingSystem.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.FlightBookingSystem.Model.Notification;
import com.example.FlightBookingSystem.Model.Passenger;
import com.example.FlightBookingSystem.Service.NotificationService;
import com.example.FlightBookingSystem.Service.PassengerService;

@Controller
public class PassengerController {
	
	private PassengerService passengerService;
	
	private NotificationService notificationService;
	
	private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

	@Autowired
	public PassengerController(PassengerService passengerService, NotificationService notificationService) {
		super();
		this.passengerService = passengerService;
		this.notificationService = notificationService;
	}
	
	/**
	 * This method is used to open the homepage first time when the program loads
	 * @return the url of the homepage
	 */
	@GetMapping
	public String getHomePage() {
		return "home";
	}
	
	/**
	 * This is for the signup page in the program
	 * @param model to pass new passenger object using thymeleaf
	 * @return the url of the registerPassenger page
	 */
	@GetMapping("registerPassenger")
	public String getToSignUpPage(Model model) {
		model.addAttribute("passenger", new Passenger());
		return "registerPassenger";
	}
	
	/**
	 * This method is used to save the details of the passenger that we get from registerPassenger page into the database
	 * @param passenger data that we get from the registerPassenger page in the web
	 * @return redirected url of the homepage
	 */
	@PostMapping("savePassenger")
	public String saveUserFromForm(@ModelAttribute Passenger passenger) {
		logger.info("New user saved");
		passengerService.savePassenger(passenger);
		return "redirect:/";
	}
	
	/**
	 * This method is used to setup the loginpage
	 * @param model to pass the new passenber object using thymeleaf
	 * @return url for the loginpage
	 */
	@GetMapping("login")
	public String getLoginPage(Model model, HttpSession httpSession) {
		if(httpSession.getAttribute("passenger") != null)
			return "passengerProfile";
		model.addAttribute("passenger", new Passenger());
		return "login";
	}
	
	/**
	 * This method is used to check whether the username and password match with the database and then initiate the session based on the result
	 * @param httpSession to store the data of the passenger into session for later use
	 * @param passenger which has the details of the username and password
	 * @return url of the passenger profile or redirect to failedLogin page based on the search result of username and password
	 */
	@PostMapping("validateLogin")
	public String validateLoginProcess(HttpSession httpSession, @ModelAttribute Passenger passenger) {
		Passenger loggedInPassenger = passengerService.checkLogin(passenger.getUserName(), passenger.getPassword());
		if(loggedInPassenger != null) {
			httpSession.setAttribute("passenger", loggedInPassenger);
			logger.info("Login Successful");
			return "passengerProfile";
		}
		else
			return "redirect:/failedLogin";
	}
	
	/**
	 * This is executed when user has input wrong username or password
	 * @param model for passing empty passenger data
	 * @return the url for the failedLogin page
	 */
	@GetMapping("failedLogin")
	public String failedLoginView(Model model) {
		model.addAttribute("passenger", new Passenger());
		logger.info("Failed Login");
		return "/failedLogin";
	}
	
	/**
	 * This method is used when home button is pressed anywhere in the website
	 * @return the url for the homepage
	 */
	@GetMapping("home")
	public String getHome() {
		return "redirect:/";
	}
	
	/**
	 * This method send the user to their profile page
	 * @return the url of user's profile page
	 */
	@GetMapping("passengerProfile")
	public String getPassengerProfile(HttpSession httpSession, Model model) {
		if(httpSession.getAttribute("passenger") == null)
			return "redirect:/login";
		else {
		Passenger passenger = (Passenger)httpSession.getAttribute("passenger");
		List<Notification> userNotifications = notificationService.getNotificationForUser(passenger.getId());
		int size = notificationService.unreadNotifications(userNotifications);
		model.addAttribute("unreadNotifications", size);
		return "passengerProfile";
		}
	}
	
	/**
	 * This method is used to end the session when user logs out of their profile
	 * @param httpSession to get the session object
	 * @return the url of the homepage after logging out
	 */
	@GetMapping("logout")
	public String endingSessionToLogout(HttpSession httpSession) {
		logger.info("Logout Successful");
		httpSession.removeAttribute("passenger");
		return "redirect:/";
	}
	
	/**
	 * This method sets up the edit passenger page
	 * @param model to pass data into the view
	 * @param id of the passenger to be edited
	 * @return the url of the editPassenger page
	 */
	@GetMapping("editPassenger")
	public String editingViewForPassenger(Model model, @RequestParam long id) {
		Passenger passenger = passengerService.findById(id);
		model.addAttribute("passenger", passenger);
		return "editPassenger";
	}
	
	/**
	 * This method saves the edit data of passenger into the database
	 * @param passenger object with edited data
	 * @param httpSession
	 * @return the url of the passenger profile
	 */
	@PostMapping("editToUpdatePassenger")
	public String updatePassengerDetails(@ModelAttribute Passenger passenger, HttpSession httpSession) {
		passengerService.savePassenger(passenger);
		httpSession.setAttribute("passenger", passenger);
		logger.info("User with id " + passenger.getId() + " details updated");
		return "passengerProfile";
	}
	
	/**
	 * This method gives the confirmation page for deletion of account
	 * @return
	 */
	@GetMapping("deleteAccountConfirmationPage")
	public String confirmationForDeleteAccount() {
		return "deleteAccountConfirmationPage";
	}
	
	/**
	 * This method is used to delete user as well as remove the user from session
	 * @param httpSession
	 * @return
	 */
	@GetMapping("deleteAccount")
	public String deleteLoggedInAccount(HttpSession httpSession) {
		Passenger passenger = (Passenger) httpSession.getAttribute("passenger");
		passengerService.removeById(passenger.getId());
		httpSession.removeAttribute("passenger");
		logger.info("User with id " + passenger.getId() + " deleted");
		return "redirect:/";
	}
	
	@GetMapping("viewNotifications")
	public String viewAllUnreadNotifications(HttpSession httpSession, Model model) {
		Passenger passenger = (Passenger)httpSession.getAttribute("passenger");
		List<Notification> userNotifications = notificationService.getNotificationForUser(passenger.getId());
		model.addAttribute("notifications", userNotifications);
		notificationService.changeToReadNotifications(userNotifications);
		return "viewAllNotifications";
	}
	
	
}
