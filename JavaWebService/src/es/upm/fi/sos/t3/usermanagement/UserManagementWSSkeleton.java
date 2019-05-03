
/**
 * UserManagementWSSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package es.upm.fi.sos.t3.usermanagement;
/**
 *  UserManagementWSSkeleton java skeleton for the axisService
 */
public class UserManagementWSSkeleton{


	/**
	 * un usuario (superuser incluido) ya registrado y que ha iniciado sesión pueda
     * cambiar su contraseña
	 * 
	 * @param changePassword 
	 * @return changePasswordResponse 
	 */

	public es.upm.fi.sos.t3.usermanagement.ChangePasswordResponse changePassword
	(
			es.upm.fi.sos.t3.usermanagement.ChangePassword changePassword
			)
	{
		//TODO : fill this with the necessary business logic
		throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#changePassword");
	}


	/**
	 * comienza una nueva sesión para un usuario (user)
	 * 
	 * @param login 
	 * @return loginResponse 
	 */

	public es.upm.fi.sos.t3.usermanagement.LoginResponse login
	(
			es.upm.fi.sos.t3.usermanagement.Login login
			)
	{
		//TODO : fill this with the necessary business logic
		throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#login");
	}


	/**
	 * cierra la sesión de un usuario
	 * 
	 * @param logout 
	 * @return  
	 */

	public void logout
	(
			es.upm.fi.sos.t3.usermanagement.Logout logout
			)
	{
		//TODO : fill this with the necessary business logic

	}


	/**

	 * consultar la lista de asignaturas
	 * de un curso determinado
	 * 
	 * @param showCourses 
	 * @return showCoursesResponse 
	 */

	public es.upm.fi.sos.t3.usermanagement.ShowCoursesResponse showCourses
	(
			es.upm.fi.sos.t3.usermanagement.ShowCourses showCourses
			)
	{
		//TODO : fill this with the necessary business logic
		throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#showCourses");
	}


	/**
	 * devuelve la lista de asignaturas y notas (CourseGrade) del usuario
	 * 
	 * @param showAllGrades 
	 * @return showAllGradesResponse 
	 */

	public es.upm.fi.sos.t3.usermanagement.ShowAllGradesResponse showAllGrades
	(
			es.upm.fi.sos.t3.usermanagement.ShowAllGrades showAllGrades
			)
	{
		//TODO : fill this with the necessary business logic
		throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#showAllGrades");
	}


	/**
	 *  añade un usuario al sistema
	 * 
	 * @param addUser 
	 * @return addUserResponse 
	 */

	public es.upm.fi.sos.t3.usermanagement.AddUserResponse addUser
	(
			es.upm.fi.sos.t3.usermanagement.AddUser addUser
			)
	{
		//TODO : fill this with the necessary business logic
		throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#addUser");
	}


	/**
	 * elimina un usuario del sistema
	 * 
	 * @param removeUser 
	 * @return removeUserResponse 
	 */

	public es.upm.fi.sos.t3.usermanagement.RemoveUserResponse removeUser
	(
			es.upm.fi.sos.t3.usermanagement.RemoveUser removeUser
			)
	{
		//TODO : fill this with the necessary business logic
		throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#removeUser");
	}


	/**
	 * agregar una nota a una asignatura existente
	 * 
	 * @param addCourseGrade 
	 * @return addCourseGradeResponse 
	 */

	public es.upm.fi.sos.t3.usermanagement.AddCourseGradeResponse addCourseGrade
	(
			es.upm.fi.sos.t3.usermanagement.AddCourseGrade addCourseGrade
			)
	{
		//TODO : fill this with the necessary business logic
		throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#addCourseGrade");
	}

}
