package com.faith.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.faith.dao.IVendorContactDao;
import com.faith.model.VendorContact;

@RestController
public class VendorContactController {
	
	    @Autowired
	    IVendorContactDao dao;
	    
	    //insert  vendor+contact details
		@RequestMapping(value = "/api/insertvendordetails", method = 
				RequestMethod.POST)
		public void insertDetails(@RequestBody VendorContact vendorContact)
		{
				dao.insertVendorContactDetails(vendorContact);
		}
		
		//update vendor+contact details
		@RequestMapping(value = "/api/updatevendordetails", method = RequestMethod.PUT)
		public void updateDetails(@RequestBody VendorContact vendorContact)
		{
		
				dao.updateVendorDetails(vendorContact);
		}
		
		
		//disable 
		@RequestMapping(value = "/api/disablevendor/{vId}", method = RequestMethod.PUT)
		void disableVendor(@PathVariable("vId") int vId) {
			dao.disableVendor(vId);
		}
		
		//search+ get all
		@SuppressWarnings("rawtypes")
		@RequestMapping(value = "/api/vendordetails/{searchString}", method = RequestMethod.GET)
		public List getVendorDetails( @PathVariable("searchString") String searchString) {
			List list;
			if (searchString.equals("null")) {
				list = dao.getAllDetails();
			} else {
				list = dao.searchVendorDetails(searchString);
			}
			return list;
		}
		
		//get by id
		@RequestMapping(value = "/api/vendorbyid/{vId}", method = RequestMethod.GET)
		public VendorContact getStaffById(@PathVariable("vId") int vId) {
			VendorContact vendorContact =dao.getVendorById(vId);
			return vendorContact;
		}
    
	}
