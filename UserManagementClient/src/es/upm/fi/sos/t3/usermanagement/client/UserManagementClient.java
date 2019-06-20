package es.upm.fi.sos.t3.usermanagement.client;

import java.rmi.RemoteException;
import es.upm.fi.sos.t3.usermanagement.client.UserManagementWSStub.*;

public class UserManagementClient {
	
	/** Compares 2 arrays of Double's
	 * @param c1: array1
	 * @param c2: array2
	 * @return boolean
	 */
	private static boolean compareDoubleArrays(double [] c1, double [] c2) {
		if (c1 == null && c2 == null) 
			return true;
			
		if (c1 == null || c2 == null) 
			return false;
		
		
		if (c1.length != c2.length)
			return false;
		
		for (int i = 0; i < c1.length ; i++) {
			if (!(c1[i]==c2[i])) 
				return false;
		}
		return true;
	}
	
	/** Compares 2 arrays of String's
	 * @param c1: array1
	 * @param c2: array2
	 * @return boolean
	 */
	private static boolean compareCourses(String [] c1, String [] c2) {
		if (c1 == null && c2 == null) 
			return true;
			
		if (c1 == null || c2 == null) 
			return false;
		
		
		if (c1.length != c2.length)
			return false;
		
		for (int i = 0; i < c1.length ; i++) {
			if (!c1[i].equals(c2[i])) 
				return false;
		}
		return true;
	}
	/** Main client
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		// All the objects and stuff we need
		// First stub (client)
		UserManagementWSStub st = new UserManagementWSStub();
		st._getServiceClient().engageModule("addressing");
		st._getServiceClient().getOptions().setManageSession(true);
		// Second stub (client)
		UserManagementWSStub st2 = new UserManagementWSStub();
		st2._getServiceClient().engageModule("addressing");
		st2._getServiceClient().getOptions().setManageSession(true);
		
		int test = 1;
		Login login = new Login();
		LoginResponse lr = new LoginResponse();
		Logout logout = new Logout();
		AddUser au = new AddUser();
		AddUserResponse aur = new AddUserResponse();
		ShowCoursesResponse scr = new ShowCoursesResponse();
		ShowCourses sc = new ShowCourses();
		Course c = new Course();
		String response;
		ChangePassword cp = new ChangePassword();
		ChangePasswordResponse cpr = new ChangePasswordResponse();
		PasswordPair pw = new PasswordPair();
		RemoveUser ru = new RemoveUser();
		RemoveUserResponse rur = new RemoveUserResponse();
		Username un = new Username();
		CourseGrade cg = new CourseGrade();
		AddCourseGrade acg = new AddCourseGrade();
		AddCourseGradeResponse acgr = new AddCourseGradeResponse();
		ShowAllGradesResponse sar = new ShowAllGradesResponse();
		ShowAllGrades sag = new ShowAllGrades();
		
		System.out.println("-------------- TESTS WITH ONE CLIENT (STUB) --------------");

		// User admin
		User admin = new User();
		admin.setName("admin");
		admin.setPwd("admin");
		
		// User fake admin
		User adminFake = new User();
		adminFake.setName("admin");
		adminFake.setPwd("notValidOne");
		
		
		System.out.println("Test "+ test++ + ": Invalid login (fake admin)");	
		login.setArgs0(adminFake);
		lr = st.login(login);
		response = lr.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ lr.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		// User 1
		User user1 = new User();
		user1.setName("user1");
		user1.setPwd("user1");
		
		System.out.println("Test "+ test++ + ": Invalid login (unexisting user in the system)");	
		login.setArgs0(user1);
		lr = st.login(login);
		response = lr.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ lr.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Valid login (admin)");	
		login.setArgs0(admin);
		lr = st.login(login);
		response = lr.get_return().getResponse() == true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ lr.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		// List of the courses
		final String [] EMPTYCOURSE = null;
		c.setCourse(1);
		sc.setArgs0(c);
		scr = st.showCourses(sc);
		final String [] FIRSTCOURSE = scr.get_return().getCourseList();
		c.setCourse(2);
		sc.setArgs0(c);
		scr = st.showCourses(sc);
		final String [] SECONDCOURSE = scr.get_return().getCourseList();
		c.setCourse(3);
		sc.setArgs0(c);
		scr = st.showCourses(sc);
		final String [] THIRDCOURSE = scr.get_return().getCourseList();
		c.setCourse(4);
		sc.setArgs0(c);
		scr = st.showCourses(sc);
		final String [] FORTHCOURSE = scr.get_return().getCourseList();
			
		System.out.println("Test "+ test++ + ": Add user1 (admin logged)");	
		au.setArgs0(user1);
		aur = st.addUser(au);
		response = aur.get_return().getResponse() == true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ aur.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Add existing user (admin logged)");	
		au.setArgs0(user1);
		aur = st.addUser(au);
		response = aur.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ aur.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Logout (admin)");	
		st.logout(logout);
		System.out.println("\tResponse: Void");
		System.out.println("\tExpected: Void");
		System.out.println("\tTest: ---> OK");
		
		System.out.println("Test "+ test++ + ": Logout (admin again)");	
		st.logout(logout);
		System.out.println("\tResponse: Void");
		System.out.println("\tExpected: Void");
		System.out.println("\tTest: ---> OK");
		
		System.out.println("Test "+ test++ + ": Logout (user1 not logged)");	
		st.logout(logout);
		System.out.println("\tResponse: Void");
		System.out.println("\tExpected: Void");
		System.out.println("\tTest: ---> OK");
		
		System.out.println("Test "+ test++ + ": Invalid login (user1 wrong password)");	
		user1.setPwd("err");
		login.setArgs0(user1);
		lr = st.login(login);
		response = lr.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ lr.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);

		// reset user1 password
		user1.setPwd("user1");
		
		System.out.println("Test "+ test++ + ": Valid login (user1)");	
		login.setArgs0(user1);
		lr = st.login(login);
		response = lr.get_return().getResponse() == true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ lr.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
				
		// User 2
		User user2 = new User();
		user2.setName("user2");
		user2.setPwd("user2");
		
		System.out.println("Test "+ test++ + ": Add user2 (user1 logged)");	
		au.setArgs0(user2);
		aur = st.addUser(au);
		response = aur.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ aur.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Change user1 password (user1 logged)");
		pw.setOldpwd("user1");
		pw.setNewpwd("new");
		cp.setArgs0(pw);
		cpr = st.changePassword(cp);
		response = cpr.get_return().getResponse() == true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ cpr.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);

		System.out.println("Test "+ test++ + ": Change user1 password (user1 logged but wrong old password)");
		pw.setOldpwd("user1");
		pw.setNewpwd("new");
		cp.setArgs0(pw);
		cpr = st.changePassword(cp);
		response = cpr.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ cpr.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Change user1 password (user1 logged and oldPassword = newPassword)");
		pw.setOldpwd("new");
		pw.setNewpwd("new");
		cp.setArgs0(pw);
		cpr = st.changePassword(cp);
		response = cpr.get_return().getResponse() == true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ cpr.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Remove user1 (user1 logged)");
		un.setUsername(user1.getName());
		ru.setArgs0(un);
		rur = st.removeUser(ru);
		response = rur.get_return().getResponse() == true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ rur.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ":: Remove user2 (user1 logged)");
		un.setUsername(user2.getName());
		ru.setArgs0(un);
		rur = st.removeUser(ru);
		response = rur.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ rur.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		// logout user1
		st.logout(logout);
		
		// login admin
		login.setArgs0(admin);
		lr = st.login(login);
		
		System.out.println("Test "+ test++ + ": Remove admin (admin logged)");
		un.setUsername(admin.getName());
		ru.setArgs0(un);
		rur = st.removeUser(ru);
		response = rur.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ rur.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Add user1 (admin logged)");	
		au.setArgs0(user1);
		aur = st.addUser(au);
		response = aur.get_return().getResponse() == true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ aur.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Remove user1 (admin logged)");
		un.setUsername(user1.getName());
		ru.setArgs0(un);
		rur = st.removeUser(ru);
		response = rur.get_return().getResponse() == true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ rur.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		// Added User2
		au.setArgs0(user1);
		aur = st.addUser(au);
		
		// logout admin
		st.logout(logout);
		
		System.out.println("Test "+ test++ + ": Remove user2 (nobody logged)");
		un.setUsername(user1.getName());
		ru.setArgs0(un);
		rur = st.removeUser(ru);
		response = rur.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ rur.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Show Courses 1 (nobody logged)");
		c.setCourse(1);
		sc.setArgs0(c);
		scr = st.showCourses(sc);
		response = scr.get_return().getResult()== false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ scr.get_return().getResult());
		System.out.println("\tExpected: false");
		System.out.println("\tSame response list: "+ compareCourses(EMPTYCOURSE, scr.get_return().getCourseList()));
		System.out.println("\tTest: ---> " + response);
		
		// login admin
		login.setArgs0(admin);
		lr = st.login(login);
		
		System.out.println("Test "+ test++ + ": Show Courses 1 (admin logged)");
		c.setCourse(1);
		sc.setArgs0(c);
		scr = st.showCourses(sc);
		response = scr.get_return().getResult()== true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ scr.get_return().getResult());
		System.out.println("\tExpected: true");
		System.out.println("\tSame response list: "+ compareCourses(FIRSTCOURSE, scr.get_return().getCourseList()));
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Show Courses 2 (admin logged)");
		c.setCourse(2);
		sc.setArgs0(c);
		scr = st.showCourses(sc);
		response = scr.get_return().getResult()== true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ scr.get_return().getResult());
		System.out.println("\tExpected: true");
		System.out.println("\tSame response list: "+ compareCourses(SECONDCOURSE, scr.get_return().getCourseList()));
		System.out.println("\tTest: ---> " + response);

		// Added user1
		au.setArgs0(user1);
		aur = st.addUser(au);
		
		// logout admin
		st.logout(logout);
		
		// login user1
		login.setArgs0(user1);
		st.login(login);
		
		System.out.println("Test "+ test++ + ": Show Courses 3 (user1 logged)");
		c.setCourse(3);
		sc.setArgs0(c);
		scr = st.showCourses(sc);
		response = scr.get_return().getResult()== true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ scr.get_return().getResult());
		System.out.println("\tExpected: true");
		System.out.println("\tSame response list: "+ compareCourses(THIRDCOURSE, scr.get_return().getCourseList()));
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Show Courses 4 (user1 logged)");
		c.setCourse(4);
		sc.setArgs0(c);
		scr = st.showCourses(sc);
		response = scr.get_return().getResult()== true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ scr.get_return().getResult());
		System.out.println("\tExpected: true");
		System.out.println("\tSame response list: "+ compareCourses(FORTHCOURSE, scr.get_return().getCourseList()));
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Show Courses 0 (user1 logged)");
		c.setCourse(0);
		sc.setArgs0(c);
		scr = st.showCourses(sc);
		response = scr.get_return().getResult()== false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ scr.get_return().getResult());
		System.out.println("\tExpected: false");
		System.out.println("\tSame response list: "+ compareCourses(EMPTYCOURSE, scr.get_return().getCourseList()));
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Add Course Grade 1 (user1 logged)");
		cg.setCourse("LOGICA");
		cg.setGrade(4);
		acg.setArgs0(cg);
		acgr = st.addCourseGrade(acg);
		response = acgr.get_return().getResponse()== true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ acgr.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Add Course Grade 2 (user1 logged)");
		cg.setCourse("CALCULO");
		cg.setGrade(1);
		acg.setArgs0(cg);
		acgr = st.addCourseGrade(acg);
		response = acgr.get_return().getResponse()== true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ acgr.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Add Course Grade 3 (user1 logged)");
		cg.setCourse("PROGRAMACION I");
		cg.setGrade(9);
		acg.setArgs0(cg);
		acgr = st.addCourseGrade(acg);
		response = acgr.get_return().getResponse()== true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ acgr.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Add Course Grade 4 (user1 logged)");
		cg.setCourse("SISTEMAS DIGITALES");
		cg.setGrade(7);
		acg.setArgs0(cg);
		acgr = st.addCourseGrade(acg);
		response = acgr.get_return().getResponse()== true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ acgr.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		// logout user1
		st.logout(logout);
		
		System.out.println("Test "+ test++ + ": Add Course Grade 5 (nobody logged)");
		cg.setCourse("SISTEMAS DIGITALES");
		cg.setGrade(7);
		acg.setArgs0(cg);
		acgr = st.addCourseGrade(acg);
		response = acgr.get_return().getResponse()== false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ acgr.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Show All Grades 1 (nobody logged)");
		sar = st.showAllGrades(sag);
		String [] expectedCourses = {"PROGRAMACION I", "SISTEMAS DIGITALES", "LOGICA", "CALCULO"};
		double [] expectedGrades = {9.0,7.0,4.0,1.0};
		response = sar.get_return().getResult()== false ? "OK":"Failure";
		System.out.println("\tResponse: "+ sar.get_return().getResult());
		System.out.println("\tExpected: false");
		System.out.println("\tSame response list (Courses): "+ compareCourses(expectedCourses, sar.get_return().getCourses()));
		System.out.println("\tSame response list (Grades): "+ compareDoubleArrays(expectedGrades, sar.get_return().getGrades()));
		System.out.println("\tTest: ---> " + response);
		
		// login user1
		login.setArgs0(user1);
		st.login(login);
		
		System.out.println("Test "+ test++ + ": Show All Grades 2 (user1 logged)");
		sar = st.showAllGrades(sag);
		response = sar.get_return().getResult()== true ? "OK":"Failure";
		System.out.println("\tResponse: "+ sar.get_return().getResult());
		System.out.println("\tExpected: true");
		System.out.println("\tSame response list (Courses): "+ compareCourses(expectedCourses, sar.get_return().getCourses()));
		System.out.println("\tSame response list (Grades): "+ compareDoubleArrays(expectedGrades, sar.get_return().getGrades()));
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Show Courses 6 (user1 logged)");
		c.setCourse(6);
		sc.setArgs0(c);
		scr = st.showCourses(sc);
		response = scr.get_return().getResult()== false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ scr.get_return().getResult());
		System.out.println("\tExpected: false");
		System.out.println("\tSame response list: "+ compareCourses(EMPTYCOURSE, scr.get_return().getCourseList()));
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("\n-------------- TESTS WITH SEVERAL CLIENTS (STUBS) --------------");
			
		System.out.println("Test "+ test++ + ": Valid login (user1) - Stub 2");	
		login.setArgs0(user1);
		lr = st2.login(login);
		response = lr.get_return().getResponse() == true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ lr.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Show All Grades 1 (nobody logged) - Stub 2");
		sar = st2.showAllGrades(sag);
		response = sar.get_return().getResult()== true ? "OK":"Failure";
		System.out.println("\tResponse: "+ sar.get_return().getResult());
		System.out.println("\tExpected: true");
		System.out.println("\tSame response list (Courses): "+ compareCourses(expectedCourses, sar.get_return().getCourses()));
		System.out.println("\tSame response list (Grades): "+ compareDoubleArrays(expectedGrades, sar.get_return().getGrades()));
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Remove admin (user1 logged) - Stub 2");
		un.setUsername(admin.getName());
		ru.setArgs0(un);
		rur = st2.removeUser(ru);
		response = rur.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ rur.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		// logout user1 (stub 1)
		st2.logout(logout);
		st2.logout(logout);
		
		System.out.println("Test "+ test++ + ": Show Courses 3 (nobody logged) - Stub 2");
		c.setCourse(3);
		sc.setArgs0(c);
		scr = st2.showCourses(sc);
		response = scr.get_return().getResult()== false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ scr.get_return().getResult());
		System.out.println("\tExpected: false");
		System.out.println("\tSame response list: "+ compareCourses(EMPTYCOURSE, scr.get_return().getCourseList()));
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Invalid login (admin) (user1 logged) - Stub 1");	
		login.setArgs0(admin);
		lr = st.login(login);
		response = lr.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ lr.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Valid login (admin) - Stub 2");	
		login.setArgs0(admin);
		lr = st2.login(login);
		response = lr.get_return().getResponse() == true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ lr.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Logout (admin) - Stub 1");	
		st.logout(logout);
		System.out.println("\tResponse: Void");
		System.out.println("\tExpected: Void");
		System.out.println("\tTest: ---> OK");
		
		System.out.println("Test "+ test++ + ": Logout (admin) - Stub 2");	
		st.logout(logout);
		System.out.println("\tResponse: Void");
		System.out.println("\tExpected: Void");
		System.out.println("\tTest: ---> OK");
		
		st.logout(logout);
		st.logout(logout);
		st2.logout(logout);
		st2.logout(logout);
		// At this point, nobody is logged in st and st2
		
		// Login of user1 in both clients
		login.setArgs0(user1);
		lr = st.login(login);
		lr = st2.login(login);
		
		System.out.println("Test "+ test++ + ": Change user1 password (user1 logged) - Stub 1");
		pw.setOldpwd("user1");
		pw.setNewpwd("new");
		cp.setArgs0(pw);
		cpr = st.changePassword(cp);
		response = cpr.get_return().getResponse() == true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ cpr.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Change user1 password (user1 logged) (wrong password) - Stub 2");
		pw.setOldpwd("user1");
		pw.setNewpwd("new");
		cp.setArgs0(pw);
		cpr = st.changePassword(cp);
		response = cpr.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ cpr.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Remove user1 (user1 logged) - Stub 1");
		un.setUsername(user1.getName());
		ru.setArgs0(un);
		rur = st.removeUser(ru);
		response = rur.get_return().getResponse() == true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ rur.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Show Courses 3 (user1 removed in stub 1) - Stub 2");
		c.setCourse(3);
		sc.setArgs0(c);
		scr = st2.showCourses(sc);
		response = scr.get_return().getResult()== false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ scr.get_return().getResult());
		System.out.println("\tExpected: false");
		System.out.println("\tSame response list: "+ compareCourses(EMPTYCOURSE, scr.get_return().getCourseList()));
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Logout (user1 removed) - Stub 1");	
		st.logout(logout);
		System.out.println("\tResponse: Void");
		System.out.println("\tExpected: Void");
		System.out.println("\tTest: ---> OK");
		
		System.out.println("Test "+ test++ + ": Logout (user1 removed) - Stub 2");	
		st2.logout(logout);
		System.out.println("\tResponse: Void");
		System.out.println("\tExpected: Void");
		System.out.println("\tTest: ---> OK");
		
		System.out.println("Test "+ test++ + ": Invalid login (unexisting user in the system, user1 removed in stub1) - Stub 1");	
		login.setArgs0(user1);
		lr = st.login(login);
		response = lr.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ lr.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Invalid login (unexisting user in the system, user1 removed in stub1) - Stub 2");	
		login.setArgs0(user1);
		lr = st2.login(login);
		response = lr.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ lr.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		// Login of admin in both clients
		login.setArgs0(admin);
		lr = st.login(login);
		lr = st2.login(login);
		
		System.out.println("Test "+ test++ + ": Add user1 (admin logged) - Stub 2");	
		au.setArgs0(user1);
		aur = st2.addUser(au);
		response = aur.get_return().getResponse() == true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ aur.get_return().getResponse());
		System.out.println("\tExpected: true");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Add user1 (admin logged) (user1 already exist) - Stub 1");	
		au.setArgs0(user1);
		aur = st.addUser(au);
		response = aur.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ aur.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		// Admin logout in stub 1
		st.logout(logout);
		//st.logout(logout);


		System.out.println("Test "+ test++ + ": Add user1 (nobody logged) - Stub 1");	
		au.setArgs0(user1);
		aur = st.addUser(au);
		response = aur.get_return().getResponse() == false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ aur.get_return().getResponse());
		System.out.println("\tExpected: false");
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Show Courses 4 (nobody logged) - Stub 1");
		c.setCourse(4);
		sc.setArgs0(c);
		scr = st.showCourses(sc);
		response = scr.get_return().getResult()== false ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ scr.get_return().getResult());
		System.out.println("\tExpected: false");
		System.out.println("\tSame response list: "+ compareCourses(EMPTYCOURSE, scr.get_return().getCourseList()));
		System.out.println("\tTest: ---> " + response);
		
		System.out.println("Test "+ test++ + ": Show Courses 4 (admin logged) - Stub 2");
		c.setCourse(4);
		sc.setArgs0(c);
		scr = st2.showCourses(sc);
		response = scr.get_return().getResult()== true ? "OK":"Failure"; 
		System.out.println("\tResponse: "+ scr.get_return().getResult());
		System.out.println("\tExpected: true");
		System.out.println("\tSame response list: "+ compareCourses(EMPTYCOURSE, scr.get_return().getCourseList()));
		System.out.println("\tTest: ---> " + response);
	}
}