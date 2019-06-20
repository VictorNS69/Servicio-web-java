
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
import java.util.Set;

import es.upm.fi.sos.t3.usermanagement.xsd.CourseResponse;
import es.upm.fi.sos.t3.usermanagement.xsd.GradesResponse;
import es.upm.fi.sos.t3.usermanagement.xsd.Response;
import es.upm.fi.sos.t3.usermanagement.xsd.User;
import es.upm.fi.sos.t3.usermanagement.UPMCoursesStub.*;
/**
 *  UserManagementWSSkeleton java skeleton for the axisService
 */
public class UserManagementWSSkeleton{

	private static HashMap<String, User> users  = new HashMap<String, User>();
	private static HashMap<String, HashMap<String, Double>> userGrades = new HashMap<String, HashMap<String, Double>>();
	private static List<String> activeUsers = new ArrayList<>();
	private User sessionUser;
	private Boolean isLogged;
	
	/**
	 * Initializes the server with "admin" user
	 */
	public UserManagementWSSkeleton() {
		if (UserManagementWSSkeleton.users.isEmpty()){
			User user = new User();
			user.setName("admin");
			user.setPwd("admin");
			UserManagementWSSkeleton.users.put("admin", user);
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

		System.out.println("Users: " + UserManagementWSSkeleton.users.toString());
		System.out.println("User name: " + user.getName());
		// If user logins again while he is already logged
		if (this.sessionUser != null && user.getName().equals(
				this.sessionUser.getName()) && this.isLogged) {
			System.out.println("Already logged");
			r.setResponse(true);
			return response; // true
		}
		// If user doesn't exist
		if (UserManagementWSSkeleton.users.get(user.getName()) == null){
			System.out.println("User doesnt exist");
			return response; // false
		}
		// If user has entered the password well
		if (!user.getPwd().equals(UserManagementWSSkeleton.users.get(user.getName()).getPwd())) {
			System.out.println("Wrong pwd");
			return response; // false
		}
		// Login the user
		if (!this.isLogged) {
			this.isLogged = true;
			UserManagementWSSkeleton.activeUsers.add(user.getName());
			this.sessionUser = UserManagementWSSkeleton.users.get(user.getName());
			r.setResponse(true);
			System.out.println("Loggin succes");
			return response; // true
		}
		System.out.println("Active user: " + this.sessionUser);
		System.out.println("Loggin Else ");
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
			UserManagementWSSkeleton.activeUsers.remove(this.sessionUser.getName());
			this.sessionUser = null;
		}
	}

	/**
	 * Show the subjects of a Course
	 * 
	 * @param showCourses 
	 * @return showCoursesResponse 
	 * @throws RemoteException 
	 */
	public es.upm.fi.sos.t3.usermanagement.ShowCoursesResponse showCourses
			(es.upm.fi.sos.t3.usermanagement.ShowCourses showCourses) throws RemoteException{
		ShowCoursesResponse response = new ShowCoursesResponse();
		CourseResponse cr = new CourseResponse();
		cr.setResult(false);
		response.set_return(cr);
		if (!this.isLogged || this.sessionUser == null) {
			return response;
		}
		System.out.println("Is logged: " + this.isLogged);
		System.out.println("SessionUser: " + this.sessionUser.getName());
		UPMCoursesStub upc = new UPMCoursesStub();
		UPMCoursesStub.ShowCourses sc = new UPMCoursesStub.ShowCourses();
		UPMCoursesStub.ShowCoursesResponse scr = new UPMCoursesStub.ShowCoursesResponse();
		sc.setArgs0(showCourses.getArgs0().getCourse());
		scr = upc.showCourses(sc);
		cr.setCourseList(scr.get_return());
		cr.setResult(true);
		return response;
	}

	/**
	 * Shows user's all grades
	 * 
	 * @param showAllGrades 
	 * @return showAllGradesResponse 
	 */
	public es.upm.fi.sos.t3.usermanagement.ShowAllGradesResponse showAllGrades
			(es.upm.fi.sos.t3.usermanagement.ShowAllGrades showAllGrades){
		ShowAllGradesResponse response = new ShowAllGradesResponse();
		GradesResponse r = new GradesResponse();
		r.setResult(false);
		response.set_return(r);
		if(!this.isLogged)
			return response;
		HashMap<String, Double> grades = userGrades.get(sessionUser.getName());
		Set<String> courses = grades.keySet();
		ArrayList<String> coursesList = new ArrayList<>(); 
		ArrayList<Double> gradesList = new ArrayList<>();
		
		for (String k : courses) {
			Double act = grades.get(k);
			if (gradesList.isEmpty()) { // Si array vacio inserto al principio
				gradesList.add(act);
				coursesList.add(k);
			} else {
				boolean found = false;
				int size = gradesList.size();
				for(int i=0;i<size && !found;i++) {
					if (act > gradesList.get(i)) {
						gradesList.add(i, act);
						coursesList.add(i, k);
						found = true;
					} else if(i==gradesList.size()-1){
						gradesList.add(act);
						coursesList.add(k);
					}
				}
			}		
		}
		String[] coursesArray = new String[courses.size()];
		double[] gradesArray = new double[courses.size()];
		for (int i=0;i<gradesList.size();i++) {
			gradesArray[i] = gradesList.get(i);
			coursesArray[i] = coursesList.get(i);
		}
		r.setCourses(coursesArray);
		r.setGrades(gradesArray);
		r.setResult(true);
		return response;
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
		if(!this.isLogged || !this.sessionUser.getName().equals("admin")) {
			return response;	// False
		}
		// User exist 
		if(UserManagementWSSkeleton.users.containsKey(user.getName())){
			return response;	// False
		}
		// Add the user
		UserManagementWSSkeleton.users.put(user.getName(), user);
		r.setResponse(true);
		return response;	// True
	}


	/**
	 * Removes a user
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
        System.out.println("Active users: " + UserManagementWSSkeleton.activeUsers.toString());
        if(!this.isLogged || UserManagementWSSkeleton.users.get(this.sessionUser.getName()) == null) {
            System.out.println("En el if 1");
        	return response;
        }
        // Nobody can remove the admin
        if (removeUser.getArgs0().getUsername().equals("admin")) {
        	System.out.println("En el if 2");
        	return response;
        }
        if(this.sessionUser.getName().equals("admin")) {
        	System.out.println("En el if 3");
        	UserManagementWSSkeleton.users.remove(removeUser.getArgs0().getUsername());
            r.setResponse(true);
            return response;
        } 
        else {
            if(UserManagementWSSkeleton.activeUsers.contains(removeUser.getArgs0().getUsername())) {
            	System.out.println("En el if 4");
            	return response;
            }
            if(!this.sessionUser.getName().equals(removeUser.getArgs0().getUsername())) {
            	System.out.println("En el if 5");
            	return response;
            }
            System.out.println("En el else");
            UserManagementWSSkeleton.users.remove(removeUser.getArgs0().getUsername());
            r.setResponse(true);
            return response;
        }
	}

	/**
	 * Adds a grade to a subject
	 * 
	 * @param addCourseGrade 
	 * @return addCourseGradeResponse 
	 * @throws RemoteException 
	 */
	public es.upm.fi.sos.t3.usermanagement.AddCourseGradeResponse addCourseGrade
			(es.upm.fi.sos.t3.usermanagement.AddCourseGrade addCourseGrade) throws RemoteException{
		AddCourseGradeResponse response = new AddCourseGradeResponse();
		Response r = new Response();
		r.setResponse(false);
		response.set_return(r);
		
		if(!this.isLogged) {
			return response;
		}
		if(users.get(sessionUser.getName())==null)
            return response;
		
		UPMCoursesStub upc = new UPMCoursesStub();
		CheckCourse chk = new CheckCourse();
		chk.setArgs0(addCourseGrade.getArgs0().getCourse());
		if(!upc.checkCourse(chk).get_return()) {
			return response;
		}
		String course = addCourseGrade.getArgs0().getCourse(); // Asignatura a cambiar y nota que se le quiere poner
		Double grade = addCourseGrade.getArgs0().getGrade();
		HashMap<String, Double> hm = new HashMap<String, Double>(); // Creo un HashMap y le asigno las asignaturas del usuario actual
		if(userGrades.get(sessionUser.getName()) != null) 
			hm = userGrades.get(sessionUser.getName());
		hm.put(course, grade); // Introduzco la asignatura y su nota
		userGrades.put(sessionUser.getName(), hm); // Guardo todas las notas del usuario en la lista global
		r.setResponse(true);
		return response;
	}

}










