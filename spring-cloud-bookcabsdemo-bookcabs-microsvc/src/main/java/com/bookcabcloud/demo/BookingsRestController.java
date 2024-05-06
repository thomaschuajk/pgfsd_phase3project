package com.bookcabcloud.demo;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bookings")
public class BookingsRestController {
	
	@Autowired
	IBookingsRepository bookingsRepository;
	
	@Autowired
	ICabsRepository cabsRepository;
		
	Trip trip;

	
	@GetMapping("/all")
	public ResponseEntity<List<Booking>> getAllBooking(){
		try {
			List<Booking> allBookings = bookingsRepository.findAll();
			if(allBookings.isEmpty() || allBookings.size()==0) {
				return new ResponseEntity<List<Booking>>(HttpStatus.NO_CONTENT);
			}
			ResponseEntity<List<Booking>> responseEntityGetAllBookings = new ResponseEntity<>(allBookings,HttpStatus.OK);
			return responseEntityGetAllBookings;
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<Booking>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/showtrip")
	public ResponseEntity<Trip> ShowTripDetail(@RequestParam(name="fromLoc") String fromLoc, @RequestParam(name="toLoc") String toLoc, @RequestParam(name="seaters") int seaters){
		try {
			
			trip = new Trip(fromLoc,toLoc,seaters,CalculateFare());
			
			ResponseEntity<Trip> responseEntityShowTripDetail = new ResponseEntity<>(trip,HttpStatus.OK);
			
			return responseEntityShowTripDetail; 
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/addbooking")
	public ResponseEntity<Booking> AddBooking(){
		try {
			Cab selectedCab;
			
			String fromLoc = trip.getFromLoc();
			String toLoc = trip.getToLoc();
			int seaters = trip.getSeaters();
			String fare = trip.getFare();
			
			List<Cab> availableCabsList = cabsRepository.findByCabStatus("available");			
			
			if(availableCabsList.size()==0) {
				
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}			
			
			selectedCab = GetCabWithRequiredSeaters(availableCabsList,trip.getSeaters());
			
			if(selectedCab!=null) {
				
				Booking newBooking = new Booking(
							selectedCab.getCabId(),
							fromLoc,
							toLoc,
							fare
						);	
				
				Booking savedBooking = bookingsRepository.save(newBooking);
								
				selectedCab.setCabStatus("booked");
				selectedCab.setFromLoc(fromLoc);
				selectedCab.setToLoc(toLoc);
				selectedCab.setBookingId(newBooking.getBookingId());
				
				cabsRepository.save(selectedCab);
				
				ResponseEntity<Booking> responseEntityAddBooking = new ResponseEntity<>(newBooking,HttpStatus.OK);
				
				return responseEntityAddBooking;
				
			}					
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

	private String CalculateFare() {
		
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.UP);
		Random rand = new Random();
		double minFare = 10.00d;
		double fareInDouble = minFare+rand.nextDouble(5.00d, 30.00d);
		String fareInString = df.format(fareInDouble);
		return fareInString;
	}

	private Cab GetCabWithRequiredSeaters(List<Cab> availableCabsList, int seaters) {
				
		for(Cab cab : availableCabsList) {
			if(cab.getSeatersCap()==seaters) {		
				return cab;				
			}			
		}
		return null;
		
	}

	
}
