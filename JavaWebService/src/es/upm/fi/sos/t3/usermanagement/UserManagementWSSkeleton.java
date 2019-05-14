
/**
 * UserManagementWSSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package es.upm.fi.sos.t3.usermanagement;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.axis2.AxisFault;

import es.upm.fi.sos.t3.usermanagement.xsd.CourseResponse;
import es.upm.fi.sos.t3.usermanagement.xsd.Response;
import es.upm.fi.sos.t3.usermanagement.xsd.User;
import es.upm.fi.sos.t3.usermanagement.UPMCoursesStub.*;
/**
 *  UserManagementWSSkeleton java skeleton for the axisService
 */
public class UserManagementWSSkeleton{

	private HashMap<String, User> users;
	private List<String> activeUsers;
	private User sessionUser;
	private Boolean isLogged;
	
	/**
	 * Initializes the server with "admin" user
	 */
	public UserManagementWSSkeleton() {
		this.users = new HashMap<String, User>();
		this.activeUsers = new ArrayList<>();
		if (this.users.isEmpty()){
			User user = new User();
			user.setName("admin");
			user.setPwd("admin");
			this.users.put("admin", user);
		}
		this.sessionUser = null;
		this.isLogged = false;
	}
	
	/**
	 * A registered and logged user can change its password
	 * 
	 * @param changePassword 
	 * @return changePasswordResponse 
	 */
	public es.upm.fi.sos.t3.usermanagement.ChangePasswordResponse changePassword
			(es.upm.fi.sos.t3.usermanagement.ChangePassword changePassword){
		 ChangePasswordResponse response = new ChangePasswordResponse();
	        Response r = new Response();
	        r.setResponse(false);
	        response.set_return(r);
	        if (this.isLogged || this.sessionUser != null) {
	            if(this.sessionUser.getPwd().equals(changePassword.localArgs0.getOldpwd())){
	                this.sessionUser.setPwd(changePassword.localArgs0.getNewpwd());
	                r.setResponse(true);
	                response.set_return(r);        
	            }    
	        }
	        return response;
	}


	/**
	 * Starts a new session for the user
	 * 
	 * @param login 
	 * @return loginResponse 
	 */
	public es.upm.fi.sos.t3.usermanagement.LoginResponse login(
			es.upm.fi.sos.t3.usermanagement.Login login){
		LoginResponse response = new LoginResponse();
		Response r = new Response();
		r.setResponse(false);	// False initialization 
		response.set_return(r);
		User user = login.getArgs0();
		
		// If user logins again while he is already logged
		if (this.sessionUser != null && user.getName().equals(
				this.sessionUser.getName()) && this.isLogged) {
			r.setResponse(true);
			return response; // true
		}
		// If user doesn't exist
		if (this.users.get(user.getName()) == null){
			return response; // false
		}
		// If user has entered the password well
		if (!user.getPwd().equals(this.users.get(user.getName()).getPwd())) {
			return response; // false
		}
		// Login the user
		if (!this.isLogged) {
			this.isLogged = true;
			this.activeUsers.add(user.getName());
			this.sessionUser = this.users.get(user.getName());
			r.setResponse(true);
			return response; // true
		}
		return response; // false
	}


	/**
	 * Closes a session 
	 * 
	 * @param logout 
	 * @return  
	 */
	public void logout(es.upm.fi.sos.t3.usermanagement.Logout logout){
		if(this.isLogged){
			this.isLogged = false;
			this.activeUsers.remove(this.sessionUser.getName());
			this.sessionUser = null;
		}
	}


	/**
	 * Auto generated method signature
	 * 
	 * @param showCourses 
	 * @return showCoursesResponse 
	 * @throws RemoteException 
	 */
	public es.upm.fi.sos.t3.usermanagement.ShowCoursesResponse showCourses(es.upm.fi.sos.t3.usermanagement.ShowCourses showCourses) throws RemoteException{
		ShowCoursesResponse response = new ShowCoursesResponse();
		CourseResponse cr = new CourseResponse();
		cr.setResult(false);
		response.set_return(cr);
		System.out.println(sessionUser.getName());
		if (!this.isLogged || this.sessionUser == null) {
			System.out.println("no estoy bien logueado");
			return response;
		}
		int cnum = 0;
		UPMCoursesStub stub = new UPMCoursesStub();
		UPMCoursesStub.ShowCourses sc = new UPMCoursesStub.ShowCourses();
		UPMCoursesStub.ShowCoursesResponse scr = new UPMCoursesStub.ShowCoursesResponse();
		cnum = showCourses.getArgs0().getCourse();
		System.out.println(cnum + "--------------");
		sc.setArgs0(cnum);
		scr = stub.showCourses(sc);
		cr.setCourseList(scr.get_return());
		cr.setResult(true);
		return response;
	}


	/**
	 * Auto generated method signature
	 * 
	 * @param showAllGrades 
	 * @return showAllGradesResponse 
	 */
	public es.upm.fi.sos.t3.usermanagement.ShowAllGradesResponse showAllGrades
			(es.upm.fi.sos.t3.usermanagement.ShowAllGrades showAllGrades){
		//TODO : fill this with the necessary business logic
		throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#showAllGrades");
	}


	/**
	 * Adds a new user
	 * 
	 * @param addUser 
	 * @return addUserResponse 
	 */
	public es.upm.fi.sos.t3.usermanagement.AddUserResponse addUser
			(es.upm.fi.sos.t3.usermanagement.AddUser addUser){
		AddUserResponse response = new AddUserResponse();
		Response r = new Response();
		r.setResponse(false);	// False initialization 
		response.set_return(r);
		User user = addUser.getArgs0();
		// Logged admin only
		if(!this.isLogged || !sessionUser.getName().equals("admin")) {
			return response;	// False
		}
		// User exist 
		if(this.users.containsKey(user.getName())){
			return response;	// False
		}
		// Add the user
		this.users.put(user.getName(), user);
		r.setResponse(true);
		return response;	// True
	}


	/**
	 * Auto generated method signature
	 * 
	 * @param removeUser 
	 * @return removeUserResponse 
	 */
	public es.upm.fi.sos.t3.usermanagement.RemoveUserResponse removeUser
			(es.upm.fi.sos.t3.usermanagement.RemoveUser removeUser){
		RemoveUserResponse response = new RemoveUserResponse();    
        Response r = new Response();
        r.setResponse(false);
        response.set_return(r);

        if(!this.isLogged)
            return response;
        if(users.get(sessionUser.getName())==null)
            return response;
        if(this.sessionUser.getName().equals("admin")) {
            users.remove(removeUser.getArgs0().getUsername());
            r.setResponse(true);
            response.set_return(r);
            return response;
        } 
        else {
            if(this.activeUsers.contains(removeUser.getArgs0().getUsername()))
                return response;
            
            if(!this.sessionUser.getName().equals(removeUser.getArgs0().getUsername()))
                return response;
            
            this.users.remove(removeUser.getArgs0().getUsername());
            r.setResponse(true);
            response.set_return(r);
            return response;
        }
	}


	/**
	 * Auto generated method signature
	 * 
	 * @param addCourseGrade 
	 * @return addCourseGradeResponse 
	 */
	public es.upm.fi.sos.t3.usermanagement.AddCourseGradeResponse addCourseGrade
			(es.upm.fi.sos.t3.usermanagement.AddCourseGrade addCourseGrade){
		//TODO : fill this with the necessary business logic
		throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#addCourseGrade");
	}

}
