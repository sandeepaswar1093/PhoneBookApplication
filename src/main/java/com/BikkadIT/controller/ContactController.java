package com.BikkadIT.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.model.Contact;
import com.BikkadIT.service.ContactServiceI;
import com.BikkadIT.util.AppConstants;
import com.BikkadIT.util.AppProps;

@RestController
public class ContactController {

	@Autowired
	private ContactServiceI contactServiceI;
	
	@Autowired
	private AppProps appProps;

	@PostMapping(value = "/saveContact", consumes = "application/json")
	public ResponseEntity<String> saveContact(@RequestBody Contact contact) {
		boolean savedContact = contactServiceI.saveContact(contact);
		
		Map<String, String> messages = appProps.getMessages();

		if (savedContact == true) {
			String msg = messages.get(AppConstants.SAVE_SUCCESS) + contact.getContactId();
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} else {
			String msg2 = messages.get(AppConstants.SAVE_FAIL);
			return new ResponseEntity<String>(msg2, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/getAllContacts", produces = "application/json")
	public ResponseEntity<List<Contact>> getAllContacts() {
		List<Contact> allContact = contactServiceI.getAllContact();
		if (allContact == null) {
			String msg = "Data Not Found";
			return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Contact>>(allContact, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getContactById/{cid}", produces = "application/json")
	public ResponseEntity<Contact> getContactById(@PathVariable Integer cid) {
		Contact contact = contactServiceI.getContactById(cid);
		if (contact != null) {
			return new ResponseEntity<Contact>(contact, HttpStatus.OK);
		}
		String msg = "Data Not Found";
		return new ResponseEntity(msg, HttpStatus.NOT_FOUND);

	}

	@PutMapping(value = "/updateContact" , consumes = "application/json")
	public ResponseEntity<String> updateContact(@RequestBody Contact contact)
	{
		
		boolean updateContact = contactServiceI.updateContact(contact);
		
		Map<String, String> messages = appProps.getMessages();
		
		if(updateContact == true)
		{
			String msg = messages.get(AppConstants.UPDATE_SUCCESS);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		else
		{
			String msg = messages.get(AppConstants.UPDATE_FAIL);
		    return new ResponseEntity<String>(msg,HttpStatus.BAD_REQUEST);
		}
		
		
	}

	@DeleteMapping(value = "/deleteContactById/{cid}")
	public ResponseEntity<String> deleteContactById(@PathVariable Integer cid) {
		boolean deletedContact = contactServiceI.deleteContactById(cid);
		
		Map<String, String> messages = appProps.getMessages();
		
		if (deletedContact == true) {
			String msg = messages.get(AppConstants.DELETE_SUCCESS) + cid;
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} else {
			String msg = messages.get(AppConstants.DELETE_FAIL);
			return new ResponseEntity<String>(msg, HttpStatus.NO_CONTENT);
		}

	}

}
