package com.faith.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.faith.model.VendorContact;

public class VendorContactDao implements IVendorContactDao {
	
	    //setting Jdbc template
		JdbcTemplate template;
		public void setTemplate(JdbcTemplate template) {
		this.template = template;
		}
		
/*--------------------insert vendor+ its one contact details--------------------------------------*/
		
		
		@Override
		public int insertVendorContactDetails(VendorContact vendorContact)
		{
			
			//inserting vendor details 
			String sqlOne="insert into f_vendorTable(vName,vAddress,vLocation,vService, "
					+ "pincode,isActive) values('"+
					vendorContact.getvName()+"','"+
					vendorContact.getvAddress()+"','"+
					vendorContact.getvLocation()+"','"+
					vendorContact.getvService()+"',"+
					vendorContact.getPincode()+",'yes') ";
			template.update(sqlOne);
			
			//get maximum of vendor id from vendor table
			Integer maxVId=getMaxOfVendorId();
			System.out.println(maxVId);
			
			//inserting into contact details table
			String sqlTwo="insert into f_contactTable(cName,department,email,phone,vId) "
					+ "values('"+vendorContact.getcName()+"','"+
					vendorContact.getDepartment()+"','"+
					vendorContact.getEmail()+"','"+
					vendorContact.getPhone()+"',? )";
			return template.update(sqlTwo,new Object[] {maxVId});		
		}
		
		//to get the maximum value of vendor id from vendor table
		private Integer getMaxOfVendorId() {
			
			String sql = "select MAX(vId)from f_vendorTable";
			Integer seq = template.queryForObject(sql, new Object[] {}, Integer.class);
			return seq;
		}

/*--------------------------get vendor+contact details---------------------------------*/
		
	   
	    @Override
		public List<VendorContact> getAllDetails()
	    {
	    	return template.query("select v.vId,v.vName,v.vLocation,v.vService,c.cName,"
	    			+ "c.department,c.phone from f_vendorTable v join f_contactTable c"
	    			+ " on(v.vId=c.vId) where isActive='yes' order by v.vId desc",
							new RowMapper<VendorContact>() {
								public VendorContact mapRow(ResultSet rs, int row)
										throws SQLException {
									VendorContact vendorContact = new VendorContact();
									vendorContact.setvId(rs.getInt(1));
									vendorContact.setvName(rs.getString(2));
									vendorContact.setvLocation(rs.getString(3));
									vendorContact.setvService(rs.getString(4));
									vendorContact.setcName(rs.getString(5));
									vendorContact.setDepartment(rs.getString(6));
									vendorContact.setPhone(rs.getString(7));
									return vendorContact;
								}
							});
	    }
	    
	    //get by id
	    public VendorContact getVendorById(int vId) {
			String sql = "select v.vId,v.vName,v.vLocation,v.vService,v.vAddress,v.pincode,c.cName,"
	    			+ "c.department,c.phone,c.email from f_vendorTable v join f_contactTable c"
	    			+ " on(v.vId=c.vId) where isActive='yes' and v.vId=?";
			return template.queryForObject(sql, new Object[] {vId},
					new BeanPropertyRowMapper<VendorContact>(VendorContact.class));
		}
	    
/*------------------------------Disable vendor----------------------------------*/
	    
        @Override
		public int disableVendor(int vId) {
			
			String sql = "update f_vendorTable set isActive='no' where vId=?";
			return template.update(sql,new Object[] { vId });
		}

/*--------------------------update vendor + contact details-----------------------*/
        
        @Override
		public int updateVendorDetails(VendorContact vendorContact)
        {
        	//to update vendor table
        	String sqlOne="update f_vendorTable set vName='"+vendorContact.getvName()
        			      +"',vAddress='"+vendorContact.getvAddress()+"',vLocation='"
        			      +vendorContact.getvLocation()+"',vService='"
        			      +vendorContact.getvService()+"',pincode="+
        			      vendorContact.getPincode()+" where vId=?";
        	template.update(sqlOne,new Object[] {vendorContact.getvId()});
        	
        	//to update contact table
        	String sqlTwo="update f_contactTable set cName='"+vendorContact.getcName()
        			      +"',department='"+vendorContact.getDepartment()
        			      +"',email='"+vendorContact.getEmail()+"',phone='"
        			      +vendorContact.getPhone()+"' where vId=?";
        	
        	return template.update(sqlTwo,new Object[] {vendorContact.getvId()});

       }
        
 /*---------------------------------SEARCH--------------------------------------------------------------*/
 
    	@Override
		public List<VendorContact> searchVendorDetails(String searchString) {
			return template.query("select v.vId,v.vName,v.vLocation,v.vService,c.cName,"
	    			+ "c.department,c.phone from f_vendorTable v join f_contactTable c"
	    			+ " on(v.vId=c.vId) where isActive='yes' and v.vName='"+searchString
	    			+"' or v.vLocation='"+searchString+"' or v.vService='"+searchString+"'",
							new RowMapper<VendorContact>() {
								public VendorContact mapRow(ResultSet rs, int row)
										throws SQLException {
									VendorContact  vendorContact = new VendorContact();
									vendorContact.setvId(rs.getInt(1));
									vendorContact.setvName(rs.getString(2));
									vendorContact.setvLocation(rs.getString(3));
									vendorContact.setvService(rs.getString(4));
									vendorContact.setcName(rs.getString(5));
									vendorContact.setDepartment(rs.getString(6));
									vendorContact.setPhone(rs.getString(7));
									return vendorContact;
								}
							});
		}	
}
