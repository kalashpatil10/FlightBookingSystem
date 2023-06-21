//package com.example.FlightBookingSystem.ControllerTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import javax.servlet.http.HttpSession;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.ui.Model;
//
//import com.example.FlightBookingSystem.Controller.FlightController;
//import com.example.FlightBookingSystem.Controller.PassengerController;
//import com.example.FlightBookingSystem.Model.Passenger;
//import com.example.FlightBookingSystem.Service.FlightService;
//import com.example.FlightBookingSystem.Service.PassengerService;
//import com.example.FlightBookingSystem.Service.TicketService;
//
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest
//class PassengerControllerTest {
//	
//	@Autowired
//	private MockMvc mvc;
//	
//	@MockBean
//	private Model mockModel;
//	
//	@MockBean
//	private HttpSession mockHttpSession;
//	
//	private PassengerController passengerController;
//	
//	private FlightController flightController;
//	
//	@MockBean
//	private PassengerService mockPassengerService;
//	
//	@MockBean
//	private FlightService mockFlightService;
//	
//	@MockBean
//	private TicketService mockTicketService;
//
//	@BeforeEach
//	void setUp() throws Exception {
//		passengerController = new PassengerController(mockPassengerService);
//		//flightController = new FlightController(mockFlightService, mockPassengerService, mockTicketService);
//	}
//	
//	@Test
//	void getHomePage_test() throws Exception {
//		mvc.perform(MockMvcRequestBuilders.get("/"))
//		.andExpect(MockMvcResultMatchers.view().name("home"));
//	}
//	
//	@Test
//	void getToSignUpPage_test() throws Exception{
//		mvc.perform(MockMvcRequestBuilders.get("/registerPassenger"))
//		.andExpect(MockMvcResultMatchers.view().name("registerPassenger"));
//	}
//	
//	@Test
//	void saveUserFromForm_test() throws Exception {
//		Passenger passenger = new Passenger();
//		passengerController.saveUserFromForm(passenger);
//		
//		verify(mockPassengerService).savePassenger(passenger);
//		
//		mvc.perform(MockMvcRequestBuilders.post("/savePassenger"))
//		.andExpect(MockMvcResultMatchers.view().name("redirect:/"));
//	}
//	
//	@Test
//	void getLoginPage_test() throws Exception{
//		mvc.perform(MockMvcRequestBuilders.get("/login"))
//		.andExpect(MockMvcResultMatchers.view().name("login"));
//	}
//	
//	@Test
//	void validateLoginProcess_pass_test() throws Exception{
//		Passenger passenger = new Passenger("Avisek", "avsk", "12345", "adhikariavisek@gmail.com", 1234567L, 1234567L);
//		
//		when(mockPassengerService.checkLogin(passenger.getUserName(), passenger.getPassword())).thenReturn(passenger);
//		
//		passengerController.validateLoginProcess(mockHttpSession, passenger);
//		
//		verify(mockPassengerService).checkLogin(passenger.getUserName(), passenger.getPassword());
//		
//		verify(mockHttpSession).setAttribute("passenger", passenger);
//		
//		assertNotNull(mockPassengerService.checkLogin(passenger.getUserName(), passenger.getPassword()));
//		
//		String test = "passengerProfile";
//		
//		assertEquals(test, passengerController.validateLoginProcess(mockHttpSession, passenger));
//		
//	}
//	
//	@Test
//	void validateLoginProcess_fail_test() throws Exception{
//		Passenger passenger = new Passenger();
//		
//		passengerController.validateLoginProcess(mockHttpSession, passenger);
//		String test = "redirect:/failedLogin";
//		
//		assertEquals(test, passengerController.validateLoginProcess(mockHttpSession, passenger));
//		
//	}
//	
//	@Test
//	void failedLoginView_test() throws Exception{
//		Passenger passenger = new Passenger();
//		
//		passengerController.failedLoginView(mockModel);
//		mvc.perform(MockMvcRequestBuilders.get("/failedLogin"))
//		.andExpect(MockMvcResultMatchers.view().name("/failedLogin"));		
//	}
//	
//	@Test
//	void getHome_test() throws Exception{
//		mvc.perform(MockMvcRequestBuilders.get("/home"))
//		.andExpect(MockMvcResultMatchers.view().name("redirect:/"));	
//	}
//	
//	@Test
//	void getPassengerProfile_pass_test() {
//		Passenger passenger = new Passenger("Avisek", "avsk", "12345", "adhikariavisek@gmail.com", 1234567L, 1234567L);
//		when(mockHttpSession.getAttribute("passenger")).thenReturn(passenger);
//		
//		passengerController.getPassengerProfile(mockHttpSession);
//		
//		verify(mockHttpSession).getAttribute("passenger");
//		
//		String test = "passengerProfile";
//		
//		assertEquals(test, passengerController.getPassengerProfile(mockHttpSession));
//	}
//	
//	@Test
//	void getPassengerProfile_fail_test() {
//		when(mockHttpSession.getAttribute("passenger")).thenReturn(null);
//		
//		String test = "redirect:/login";
//		
//		assertEquals(test, passengerController.getPassengerProfile(mockHttpSession));
//	}
//	
//	@Test
//	void endingSessionToLogout() throws Exception {
//		passengerController.endingSessionToLogout(mockHttpSession);
//		
//		verify(mockHttpSession).removeAttribute("passenger");
//		
//		mvc.perform(MockMvcRequestBuilders.get("/home"))
//		.andExpect(MockMvcResultMatchers.view().name("redirect:/"));
//	}
//	
//	@Test
//	void editingViewForPassenger() throws Exception {
//		long id = 1L;
//		Passenger passenger = new Passenger();
//		
//		when(mockPassengerService.findById(id)).thenReturn(passenger);
//		
//		passengerController.editingViewForPassenger(mockModel, id);
//		
//		verify(mockPassengerService).findById(id);
//		
//		verify(mockModel).addAttribute("passenger", passenger);
//		
//		mvc.perform(MockMvcRequestBuilders.get("/editPassenger?id={id}", (long)id))
//		.andExpect(MockMvcResultMatchers.view().name("editPassenger"));
//	}
//	
//	@Test
//	void updatePassengerDetails() throws Exception {
//		Passenger passenger = new Passenger();
//		
//		passengerController.updatePassengerDetails(passenger, mockHttpSession);
//		
//		verify(mockPassengerService).savePassenger(passenger);
//		
//		verify(mockHttpSession).setAttribute("passenger", passenger);
//		
//		mvc.perform(MockMvcRequestBuilders.post("/editToUpdatePassenger"))
//		.andExpect(MockMvcResultMatchers.view().name("passengerProfile"));
//	}
//	
//	@Test
//	void confirmationForDeleteAccount() throws Exception {
//		mvc.perform(MockMvcRequestBuilders.get("/deleteAccountConfirmationPage"))
//		.andExpect(MockMvcResultMatchers.view().name("deleteAccountConfirmationPage"));
//	}
//	
//	@Test
//	void deleteLoggedInAccount() throws Exception {
//		Passenger passenger = new Passenger();
//		
//		when(mockHttpSession.getAttribute("passenger")).thenReturn(passenger);
//		
//		passengerController.deleteLoggedInAccount(mockHttpSession);
//		
//		verify(mockPassengerService).removeById(passenger.getId());
//		
//		verify(mockHttpSession).removeAttribute("passenger");
//	}
//	
//
//}
