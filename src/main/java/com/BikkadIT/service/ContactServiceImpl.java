package com.BikkadIT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.model.Contact;
import com.BikkadIT.repository.ContactRepo;

@Service
public class ContactServiceImpl implements ContactServiceI{
	
	@Autowired
	private ContactRepo contactRepo;

	@Override
	public boolean saveContact(Contact contact) {
	          
		Contact contact2 = contactRepo.save(contact);
		
		if(contact2 != null)
		{
			return true;
		}
		else
		{
		     return false;	
		}
	}

	@Override
	public List<Contact> getAllContact() {
		
		List<Contact> allContacts = contactRepo.findAll();
		return allContacts;
	}

	@Override
	public Contact getContactById(Integer contactId) {
		Contact contact = contactRepo.findById(contactId).get();
		return contact;
	}

	@Override
	public boolean deleteContactById(Integer contactId) {
		boolean existsById = contactRepo.existsById(contactId);
		if(existsById == true)
		{
			contactRepo.deleteById(contactId);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean updateContact(Contact contact) {
		Contact contact2 = contactRepo.save(contact);
		
		if (contact2 == null)
		{
			return false;
		}
		else
		{
		return true ;
		}
	}

}
