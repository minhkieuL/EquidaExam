package outils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author MartinJ
 */
public class EnvoieFichier {

	public static ArrayList<File> sauvegarderFichiers(HttpServletRequest request, String fieldName, String path) throws IOException, ServletException {
		ArrayList<File> paths = new ArrayList<>();
		ArrayList<Part> fileParts = new ArrayList<>(request.getParts());
		fileParts = removeUnnecessaryPart(fileParts, fieldName);
		
		for (Part filePart : fileParts) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

			//Création du dossier et du fichier
			//On selectionne le fichier là où se trouve le projet
			File outputFile = new File(request.getServletContext().getRealPath(File.separator) + path + File.separator + fileName);
			outputFile.getParentFile().mkdirs();
			outputFile.createNewFile();
			
			//Ecriture du fichier
			BufferedInputStream inputStream = new BufferedInputStream(filePart.getInputStream());
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));

			byte[] b = new byte[8];
			while (inputStream.read(b) != -1) {
				outputStream.write(b);
			}

			outputStream.close();
			inputStream.close();
			
			//On retourne le lien en chemin relatif
			paths.add(new File(request.getContextPath() + File.separator + path + File.separator + fileName));
		}
		
		return paths;
	}

	private static ArrayList<Part> removeUnnecessaryPart(ArrayList<Part> fileParts, String fieldName) {
		ArrayList<Part> filterParts = new ArrayList<>();
		for(Part p : fileParts) {
			if(p.getName().equals(fieldName)) {
				filterParts.add(p);
			}
		}
		
		return filterParts;
	}

}
