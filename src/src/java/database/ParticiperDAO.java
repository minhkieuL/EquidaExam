package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Participer;

/**
 *
 * @author slam
 */
public class ParticiperDAO {

	public static void renseignerCourseCheval(Connection connection, Participer uneParticipation) {
		try {
			PreparedStatement requete = connection.prepareStatement("INSERT INTO participer (idCheval, idCourse, classement)\n"
					+ "VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setInt(1, uneParticipation.getCheval().getId());
			requete.setInt(2, uneParticipation.getCourse().getId());
			requete.setInt(3, uneParticipation.getPlace());

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
	}

	public static ArrayList<Participer> getLesParticipationsCoursesCheval(Connection connection, int idCheval) {
		ArrayList<Participer> lesParticipations = new ArrayList<Participer>();
		try {
			//preparation de la requete     
			PreparedStatement requete = connection.prepareStatement("SELECT * FROM participer WHERE idCheval=?");
			requete.setInt(1, idCheval);

			//executer la requete
			ResultSet rs = requete.executeQuery();

			//On hydrate l'objet métier Participer avec les résultats de la requête
			while (rs.next()) {
				Participer uneParticipation = new Participer();
				uneParticipation.setCourse(CourseDAO.getCourse(connection, rs.getInt("idCourse")));
				uneParticipation.setPlace(rs.getInt("classement"));
				uneParticipation.setCheval(ChevalDAO.getCheval(connection, rs.getInt("idCheval")));
				lesParticipations.add(uneParticipation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
		return lesParticipations;
	}
	
	public static void supprimerChevalVente(Connection connection, int idCheval, int idCourse) {
		try {
			PreparedStatement requete = connection.prepareStatement("DELETE FROM participer WHERE participer.idCheval = ? AND participer.idCourse = ?;");
			requete.setInt(1, idCheval);
			requete.setInt(2, idCourse);
			
			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//out.println("Erreur lors de l’établissement de la connexion");
		}
	}
}
