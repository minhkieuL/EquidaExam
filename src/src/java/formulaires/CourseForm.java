/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import static formulaires.Form.getDataForm;
import javax.servlet.http.HttpServletRequest;
import modele.Course;

/**
 *
 * @author slam
 */
public class CourseForm extends Form {

	public Course getCourse(HttpServletRequest request) {
		Course uneCourse = new Course();

		String nom = getDataForm(request, "nom");
		String date = getDataForm(request, "date");
		String ville = getDataForm(request, "ville");

		uneCourse.setNom(nom);
		uneCourse.setDate(date);
		uneCourse.setVille(ville);

		return uneCourse;
	}
	
	public int getCourseOrigin(HttpServletRequest request) {
        int idCourse = Integer.valueOf(getDataForm(request, "codeOrigin"));
		return idCourse;
    }
}