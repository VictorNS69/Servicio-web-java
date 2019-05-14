package es.upm.fi.sos.t3.usermanagement.client;

import java.rmi.RemoteException;

import es.upm.fi.sos.t3.usermanagement.client.UserManagementWSStub.*;

public class UserManagementClient {

	public static void main(String[] args) throws RemoteException {
		UserManagementWSStub st = new UserManagementWSStub();
		st._getServiceClient().engageModule("addressing");
		st._getServiceClient().getOptions().setManageSession(true);
		
		User admin = new User();
		admin.setName("admin");
		admin.setPwd("admin");
		Login log = new Login();
		log.setArgs0(admin);
		
		LoginResponse lr = new LoginResponse();
		lr = st.login(log);
		System.out.println("El valor de login de admin es " + lr.get_return().getResponse());
		
		
		ShowCourses sc = new ShowCourses();
		Course c = new Course();
		c.setCourse(1);
		sc.setArgs0(c);
		ShowCoursesResponse scr = new ShowCoursesResponse();
		scr = st.showCourses(sc);
		System.out.println(scr.get_return().getCourseList());
		System.out.println(scr.get_return().getResult());
		
	}

}
