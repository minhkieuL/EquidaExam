/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Course;
/**
 *
 * @author slam
 */
public class CourseDAO {
	
	Connection connection = null;
	static PreparedStatement requete = null;
	static ResultSet rs = null;

	public static ArrayList<Course> getLesCourses(Connection connection) {
		ArrayList<Course> lesCourses = new ArrayList<Course>();
		try {
			//preparation de la requete     
			requete = connection.prepareStatement("SELECT * FROM course");

			//executer la requete
			rs = requete.executeQuery();

			//On hydrate l'objet métier Client avec les résultats de la requête
			while (rs.next()) {
				Course uneCourse = new Course();
				uneCourse.setNom(rs.getString("nom"));
				uneCourse.setDate(rs.getString("date"));
				uneCourse.setVille(rs.getString("ville"));
				lesCourses.add(uneCourse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return lesCourses;
	}

	public static Course ajouterCourse(Connection connection, Course uneCourse) {

		try {
			requete = connection.prepareStatement("INSERT INTO course (nom, date, ville)\n"
					+ "VALUES (?,?,?)", requete.RETURN_GENERATED_KEYS);
			requete.setString(1, uneCourse.getNom());
			requete.setString(2, uneCourse.getDate());
			requete.setString(3, uneCourse.getVille());

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return uneCourse;
	}
	
}
