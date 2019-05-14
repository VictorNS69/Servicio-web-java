import java.rmi.RemoteException;

import es.upm.fi.sos.t3.usermanagement.*;
import es.upm.fi.sos.t3.usermanagement.xsd.Course;
import es.upm.fi.sos.t3.usermanagement.xsd.User;

public class MainTester {

	public static void main(String[] args) throws RemoteException {
		System.out.println("MAIN TESTER");
		
		// UserManagementWSSkeleton init
		UserManagementWSSkeleton UM = new UserManagementWSSkeleton();
		
		// Admin
		User admin = new User();
		admin.setName("admin");
		admin.setPwd("admin");
		
		// User 1
		User u1 = new User();
		u1.setName("usuario1");
		u1.setPwd("u1");
		
		System.out.println("Login de " + admin.getName());
		LoginResponse lr = new LoginResponse();
		Login l = new Login();
		l.setArgs0(admin);
		lr = UM.login(l);
		System.out.println("Response: " + lr.get_return().getResponse());
		
		// User 1 is not in the system
		System.out.println("Login de " + u1.getName());
		l.setArgs0(u1);
		lr = UM.login(l);
		System.out.println("Response: " + lr.get_return().getResponse());

		// Adding User1
		System.out.println("Añadiendo a " + u1.getName());
		AddUser au = new AddUser();
		au.setArgs0(u1);
		AddUserResponse aur = new AddUserResponse();
		aur = UM.addUser(au);
		System.out.println("Response: " + aur.get_return().getResponse());

		// Logout admin
		System.out.println("Logout de admin");
		Logout lo = new Logout();
		UM.logout(lo);
		
		// User1 is not in the system
		System.out.println("Login de " + u1.getName());
		l.setArgs0(u1);
		lr = UM.login(l);
		System.out.println("Response: " + lr.get_return().getResponse());

		// Logout user1
		System.out.println("Logout de user1");
		UM.logout(lo);
		
		// Logout admin
		System.out.println("Logout de admin (no hace nada)");
		UM.logout(lo);
				
		// Logout user1
		System.out.println("Logout de user1 (no hace nada)");
		UM.logout(lo);
		
		// Not a real user (not the password it should be)
		User fake = new User();
		fake.setName("usuario1");
		fake.setPwd("WRONG pwd");
		
		// User1 is not in the system
		System.out.println("Login de " + fake.getName()+ " falso");
		l.setArgs0(fake);
		lr = UM.login(l);
		System.out.println("Response: " + lr.get_return().getResponse());
		
		// Login admin
		System.out.println("Login de " + admin.getName());
		l.setArgs0(admin);
		lr = UM.login(l);
		System.out.println("Response: " + lr.get_return().getResponse());
		
		// Show Courses 1
		ShowCoursesResponse scr = new ShowCoursesResponse();
		ShowCourses sc = new ShowCourses();
		Course c = new Course();
		c.setCourse(1);
		System.out.println("ShowCourses de " + c.getCourse());
		sc.setArgs0(c);
		scr = UM.showCourses(sc);
		System.out.println("Lista: ");
		String [] arr = scr.get_return().getCourseList();
		if (arr != null) {
			for(String i: arr) {
				System.out.println("\t" + i.toString());
			}
		}
		else {
			System.out.println("\tLista vacía");
		}
		System.out.println("Response: " + scr.get_return().getResult());
		
		// Logout admin
		System.out.println("Logout de admin");
		UM.logout(lo);
		
		// Show Courses 1
		c.setCourse(1);
		System.out.println("ShowCourses de " + c.getCourse());
		sc.setArgs0(c);
		scr = UM.showCourses(sc);
		System.out.println("Lista: ");
		arr = scr.get_return().getCourseList();
		if (arr != null) {
			for(String i: arr) {
				System.out.println("\t" + i.toString());
			}
		}
		else {
			System.out.println("\tLista vacía");
		}
		System.out.println("Response: " + scr.get_return().getResult());
		
		// Login admin
		System.out.println("Login de " + admin.getName() + " (ya estaba logeado)");
		l.setArgs0(admin);
		lr = UM.login(l);
		System.out.println("Response: " + lr.get_return().getResponse());
		
		// Login admin
		System.out.println("Login de " + admin.getName() + " (ya estaba logeado)");
		l.setArgs0(admin);
		lr = UM.login(l);
		System.out.println("Response: " + lr.get_return().getResponse());
		
		// Login admin
		System.out.println("Login de " + u1.getName() + " estando admin loggeado");
		l.setArgs0(u1);
		lr = UM.login(l);
		System.out.println("Response: " + lr.get_return().getResponse());
	}
}
