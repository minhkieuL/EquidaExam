package formulaires;

import database.Utilitaire;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import modele.Compte;

/**
 *
 * @author MartinJ
 */
public class CompteForm extends Form {
    
	public CompteForm() {
		
	}
	
    public Compte getCompte( HttpServletRequest request ) {
        Compte compte  = new Compte();
         
        String login = getDataForm(request, "login");
        String passwd = getDataForm(request, "passwd");
        
		compte.setLogin(login);
		
		try {
			compte.setMdp(Utilitaire.hashToSHA256(passwd));
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(CompteForm.class.getName()).log(Level.SEVERE, null, ex);
			compte.setMdp("");
		}
               
        return compte;
    }
}
