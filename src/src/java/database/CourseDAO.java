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

	public static ArrayList<Course> getLesCourses(Connection connection) {
		ArrayList<Course> lesCourses = new ArrayList<Course>();
		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM course");

			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Course avec les résultats de la requête
			while (rs.next()) {
				Course uneCourse = new Course();
				uneCourse.setId(rs.getInt("id"));
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
			PreparedStatement requete = connection.prepareStatement("INSERT INTO course (nom, date, ville)\n"
					+ "VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
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

	public static Course getCourse(Connection connection, int idCourse) {
		Course uneCourse = new Course();
		try {
			//preparation de la requete 
			PreparedStatement requete = connection.prepareStatement(" SELECT * FROM course WHERE id = ?; ");
			requete.setInt(1, idCourse);
			/* Exécution de la requête */
			//executer la requete
			ResultSet rs = requete.executeQuery();

			while (rs.next()) {
				uneCourse.setId(idCourse);
				uneCourse.setNom(rs.getString("nom"));
				uneCourse.setDate(rs.getString("date"));
				uneCourse.setVille(rs.getString("ville"));
			}
			//System.out.println("requete " +requete);
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return uneCourse;
	}

	public static Course modifierCourse(Connection connection, Course uneCourse, int idCourse) {

		try {
			//preparation de la requete 
			PreparedStatement requete = connection.prepareStatement("UPDATE course SET nom = ?, date = ?, ville = ? WHERE id = ?;");

			requete.setString(1, uneCourse.getNom());
			requete.setString(2, uneCourse.getDate());
			requete.setString(3, uneCourse.getVille());
			requete.setInt(4, idCourse);
			System.out.println(requete);
			/* Exécution de la requête */
			requete.executeUpdate();

			//System.out.println("requete " +requete);
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return uneCourse;
	}

}
