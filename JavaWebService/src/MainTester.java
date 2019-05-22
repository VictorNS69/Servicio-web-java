import java.rmi.RemoteException;

import es.upm.fi.sos.t3.usermanagement.*;
import es.upm.fi.sos.t3.usermanagement.xsd.Course;
import es.upm.fi.sos.t3.usermanagement.xsd.CourseGrade;
import es.upm.fi.sos.t3.usermanagement.xsd.PasswordPair;
import es.upm.fi.sos.t3.usermanagement.xsd.User;
import es.upm.fi.sos.t3.usermanagement.xsd.Username;

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
		
		AddUser au = new AddUser();
		AddUserResponse aur = new AddUserResponse();
		au.setArgs0(u1);
		aur = UM.addUser(au);
		System.out.println(aur);
		
		Logout lo = new Logout();
		UM.logout(lo);
		System.out.println("Deslogueando admin");
		
		System.out.println("Login de " + u1.getName());
		lr = new LoginResponse();
		l = new Login();
		l.setArgs0(u1);
		lr = UM.login(l);
		System.out.println("Response: " + lr.get_return().getResponse());
		
		CourseGrade cg = new CourseGrade();
		AddCourseGrade acg = new AddCourseGrade();
		System.out.println("Añadiendo notas a asignaturas:");
		cg.setCourse("LOGICA");
		cg.setGrade(4);
		acg.setArgs0(cg);
		System.out.println("\tResponse de añadir un 4 en LOGICA: " + UM.addCourseGrade(acg).get_return().getResponse());
		cg.setCourse("CALCULO");
		cg.setGrade(1);
		acg.setArgs0(cg);
		System.out.println("\tResponse de añadir un 1 en CALCULO: " + UM.addCourseGrade(acg).get_return().getResponse());
		cg.setCourse("PROGRAMACION I");
		cg.setGrade(9);
		acg.setArgs0(cg);
		System.out.println("\tResponse de añadir un 9 en PROGRAMACION I: " + UM.addCourseGrade(acg).get_return().getResponse());
		cg.setCourse("SISTEMAS DIGITALES");
		cg.setGrade(7);
		acg.setArgs0(cg);
		System.out.println("\tResponse de añadir un 7 en SISTEMAS DIGITALES: " + UM.addCourseGrade(acg).get_return().getResponse());
		

		
		ShowAllGradesResponse sar = new ShowAllGradesResponse();
		ShowAllGrades sag = new ShowAllGrades();
		sar = UM.showAllGrades(sag);
		String [] courses = sar.get_return().getCourses();
		double [] grades = sar.get_return().getGrades();
		System.out.println("Lista de asignaturas y notas:");
		
		for (int i = 0;i < courses.length;i++) {
			System.out.println(courses[i] + ": " + grades[i]);
		}
		System.out.println("Response lista de notas: " + sar.get_return().getResult());
		System.out.println(grades[0] + " "+grades[1]+ " "+grades[2]+ " "+grades[3]);
		}
	
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
}
