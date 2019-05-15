package es.upm.fi.sos.t3.usermanagement.client;

import java.rmi.RemoteException;

import es.upm.fi.sos.t3.usermanagement.client.UserManagementWSStub.*;

public class UserManagementClient {
	
	private static void printCourses(String [] arr) {
		if (arr != null) {
			System.out.print("\tLista: ");
			for(String i: arr) {
				System.out.print(i.toString() + " ");
			}
		}
		else {
			System.out.println("\tLista vacía");
		}
	}
	
	public static void main(String[] args) throws RemoteException {
		UserManagementWSStub st = new UserManagementWSStub();
		st._getServiceClient().engageModule("addressing");
		st._getServiceClient().getOptions().setManageSession(true);
		//st._getServiceClient().getOptions().setUseSeparateListener(true);
		
		User admin = new User();
		admin.setName("admin");
		admin.setPwd("admin");
		Login log = new Login();
		log.setArgs0(admin);
		
		LoginResponse lr = new LoginResponse();
		lr = st.login(log);
		System.out.println("El valor de login de admin es " + lr.get_return().getResponse());
	
		// User 1
		User u1 = new User();
		u1.setName("usuario1");
		u1.setPwd("u1");
	
		// Adding User1
		System.out.println("Añadiendo a " + u1.getName());
		AddUser au = new AddUser();
		au.setArgs0(u1);
		AddUserResponse aur = new AddUserResponse();
		aur = st.addUser(au);
		System.out.println("Response: " + aur.get_return().getResponse());
		/*
		ShowCourses sc = new ShowCourses();
		Course c = new Course();
		c.setCourse(1);
		sc.setArgs0(c);
		ShowCoursesResponse scr = new ShowCoursesResponse();
		scr = st.showCourses(sc);
		*/
		ShowCoursesResponse scr = new ShowCoursesResponse();
		ShowCourses sc = new ShowCourses();
		Course c = new Course();
		c.setCourse(1);
		System.out.println("ShowCourses de " + c.getCourse());
		sc.setArgs0(c);
		
		scr = st.showCourses(sc);
		System.out.println(scr.get_return().getCourseList());
		printCourses(scr.get_return().getCourseList());
		System.out.println(scr.get_return().getResult());
		
	}

}
