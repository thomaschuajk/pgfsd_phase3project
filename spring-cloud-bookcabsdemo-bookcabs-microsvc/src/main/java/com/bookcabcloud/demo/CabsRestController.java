package com.bookcabcloud.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/cabs")
public class CabsRestController {
	
	@Autowired
	ICabsRepository cabsRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Cab>> getAllCabs(){
		try {
			
			List<Cab> cabs = cabsRepository.findAll();
			
			if(cabs.isEmpty() || cabs.size()==0) {
				
				return new ResponseEntity<List<Cab>>(HttpStatus.NO_CONTENT);
			}
			ResponseEntity<List<Cab>> responseEntityGetAllCabs = new ResponseEntity<>(cabs,HttpStatus.OK);
			return responseEntityGetAllCabs;
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/*
	@GetMapping("/all")
	public List<Cab> getAllCabs(){
		try {
			
			List<Cab> cabs = cabsRepository.findAll();
			
			if(cabs.isEmpty() || cabs.size()==0) {
				
				return null;
			}
			
			return cabs;
			
		} catch (Exception e) {
			
			return null;
		}
	}
	*/
	
	@PostMapping("addcab")
	public ResponseEntity<Cab> AddCab(@RequestBody Cab cab){
		try {
			
			Cab savedCab = cabsRepository.save(cab);
			
			ResponseEntity<Cab> responseEntityAddCab = new ResponseEntity<>(savedCab,HttpStatus.CREATED);
			
			return responseEntityAddCab;
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@GetMapping("/{cabid}")
	public ResponseEntity<Cab> getCab(@PathVariable("cabid") int cabid){
		
		Optional<Cab> retrievedCab = cabsRepository.findById(cabid);
		
		if(retrievedCab.isEmpty()) {
			return new ResponseEntity<Cab>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cab>(retrievedCab.get(),HttpStatus.OK);
		
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<Cab>> getAvailableCabs(){
			try {
						
				List<Cab> availCabs = new ArrayList<Cab>();
				
				cabsRepository.findByCabStatus("available").forEach(availCabs::add);
				
				if(availCabs.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				
				ResponseEntity<List<Cab>> responseEntityGetAvailCabs = new ResponseEntity<>(availCabs,HttpStatus.OK);
				return responseEntityGetAvailCabs;
			}
			catch (Exception e) {
				
				return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
		} 
	
	
	@PutMapping("/updateCabStatus/{cabid}")
	public ResponseEntity<Cab> updateCabStatus(@PathVariable("cabid") int cabid){
		
		try {
			Cab cabToUpdate = cabsRepository.getReferenceById(cabid);
			if(cabToUpdate.getCabStatus().equalsIgnoreCase("booked")) {
				cabToUpdate.setCabStatus("available");
			}else {
				cabToUpdate.setCabStatus("booked");
			}
		
			ResponseEntity<Cab> updatedCab = new ResponseEntity<Cab>(cabsRepository.save(cabToUpdate),HttpStatus.OK);
			
			return updatedCab;
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Cab>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping("/{cabid}")
	public ResponseEntity<Cab> deleteCab(@PathVariable("cabid") int cabid){
		try {
			Optional<Cab> deleteCab = cabsRepository.findById(cabid);
			if(deleteCab.isPresent()) {
				cabsRepository.delete(deleteCab.get());
			}
			return new ResponseEntity<Cab>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Cab>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
