package tests;

import modele.Cheval;
import modele.TypeCheval;

public class TypeChevalTest {
    
    public static void main(String[] args) {
        TypeCheval tc = new TypeCheval();
        tc.setId(0);
        tc.setLibelle("libelle");
        tc.setDesc("desc");
        tc.addCheval(new Cheval());
        
        System.out.println("id : " + tc.getId());
        System.out.println("libelle : " + tc.getLibelle());
        System.out.println("desc : " + tc.getDesc());
        System.out.println("cheval : " + tc.getChevaux().get(0));
    }

}
