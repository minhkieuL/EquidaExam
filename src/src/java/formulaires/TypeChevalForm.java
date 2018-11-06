/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import static formulaires.Form.getDataForm;
import javax.servlet.http.HttpServletRequest;
import modele.TypeCheval;

/**
 *
 * @author slam
 */
public class TypeChevalForm extends Form {
    
    
    public TypeCheval getTypeCheval( HttpServletRequest request ) {
        TypeCheval unTypeCheval  = new TypeCheval();
         
        String libelle = getDataForm( request, "libelle" );
        String desc= getDataForm( request, "description");
        
        unTypeCheval.setLibelle(libelle);
        unTypeCheval.setDesc(desc);
        
               
        return unTypeCheval ;
    }
}