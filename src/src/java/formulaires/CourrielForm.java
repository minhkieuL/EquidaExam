package formulaires;

import static formulaires.Form.getDataForm;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.CharBuffer;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import modele.Courriel;
import modele.Vente;

/**
 * Document : CategorieForm 
 * Created on : 12 oct. 2018, 09:25:00 
 * Author : MartinJ
 */
public class CourrielForm extends Form {

	public Courriel getCourriel(HttpServletRequest request) {
		Courriel courriel = new Courriel();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
		Date date = new Date();
		
		String objet = getDataForm(request, "objet");
		int venteId = Integer.parseInt(getDataForm(request, "vente"));
		String corps = getDataForm(request, "corps");
		
		try {
			List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());

			for (Part filePart : fileParts) {
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				
				File outputFile = new File("upload/mail/"+dateFormat.format(date)+"/"+timeFormat.format(date)+"_"+fileName);
				outputFile.getParentFile().mkdirs();
				outputFile.createNewFile();
				
				BufferedInputStream fileContent = new BufferedInputStream(filePart.getInputStream());
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
				
				byte[] b = new byte[8];
				while(fileContent.read(b) != -1) {
					outputStream.write(b);
				}
				
				outputStream.close();
				fileContent.close();
			}
		} catch(IOException e) {
			ajouterErreur("file", "Erreur lors du téléchargement des fichiers");
			e.printStackTrace();
		} catch (ServletException e) {
			ajouterErreur("file", "Erreur lors du téléchargement des fichiers");
		}
		
		Vente v = new Vente();
		v.setId(venteId);
		
		courriel.setObjet(objet);
		courriel.setCorps(corps);
		courriel.setVente(v);
		courriel.setDate(dateFormat.format(date));
		
		return courriel;
	}
}
