package formulaires;

import javax.servlet.http.HttpServletRequest;
import modele.Cheval;
import modele.Course;
import modele.Participer;

/**
 *
 * @author slam
 */
public class ParticipationForm extends Form {
	
	public Participer getParticipation(HttpServletRequest request) {
		Participer uneParticipation = new Participer();

		int idCheval = Integer.valueOf(getDataForm(request, "idCheval"));
		int idCourse = Integer.valueOf(getDataForm(request, "idCourse"));
		int classement = Integer.valueOf(getDataForm(request, "classement"));

		Cheval unCheval = new Cheval();
		unCheval.setId(idCheval);
		uneParticipation.setCheval(unCheval);
		Course uneCourse = new Course();
		uneCourse.setId(idCourse);
		uneParticipation.setCourse(uneCourse);
		uneParticipation.setPlace(classement);

		return uneParticipation;
	}
}
