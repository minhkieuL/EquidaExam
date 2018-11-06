package database;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Zakina
 */
public class Utilitaire {

	/**
	 * *********************************************************
	 * Méthode permettant de fermer correctement un resultset
	 * *********************************************************
	 */
	public static void fermerConnexion(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("Erreur lors de la fermeture d’une connexion dans fermerConnexion(ResultSet)");
			}
		}
	}

	/**
	 * *********************************************************
	 * fermer correctement un statement
	 * *********************************************************
	 */
	public static void fermerConnexion(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				System.out.println("Erreur lors de la fermeture d’une connexion dans fermerConnexion(Statement)");
			}
		}
	}

	/**
	 * *********************************************************
	 * fermer correctement une connection
	 * *********************************************************
	 */
	public static void fermerConnexion(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println("Erreur lors de la fermeture d’une connexion dans fermerConnexion(Connection)");
			}
		}
	}

	public static String hashToSHA256(String toHash) throws NoSuchAlgorithmException {
		toHash = (toHash == null) ? "" : toHash;

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(toHash.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		return String.format("%064x", new BigInteger(1, digest));
	}
}
