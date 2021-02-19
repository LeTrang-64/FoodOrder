package org.trang.test.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

	private int user_id;		
	private String user_email;
	private String password;
	private String first_name;
	private String last_name;
	private String phone_number;
	private String shipping_address;
	private int isManager;
	private int isActive;
	
	
	public User() {  //Phai co
		
	}


	public User(int user_id, String user_email, String password, String first_name, String last_name,
			String phone_number, String shipping_address, int isManager,int isActive) {
		super();
		this.user_id = user_id;
		this.user_email = user_email;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone_number = phone_number;
		this.shipping_address = shipping_address;
		this.isManager = isManager;
		this.isActive=isActive;
	}
	


	public User(String user_email, String password, String first_name, String last_name, String phone_number,
			String shipping_address, int isManager,int isActive) {
		super();
		this.user_email = user_email;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone_number = phone_number;
		this.shipping_address = shipping_address;
		this.isManager = isManager;
		this.isActive=isActive;
	}
	



	public User(int user_id, String user_email, String first_name, String last_name, String phone_number,
			String shipping_address) {
		super();
		this.user_id = user_id;
		this.user_email = user_email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone_number = phone_number;
		this.shipping_address = shipping_address;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}


	public String getShipping_address() {
		return shipping_address;
	}


	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}


	public int getIsManager() {
		return isManager;
	}


	public void setIsManager(int isManager) {
		this.isManager = isManager;
	}


	public int getIsActive() {
		return isActive;
	}


	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	


	
	
		
	    

}
