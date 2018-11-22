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

		String nomChampClassement = "classement";

		int idCheval = Integer.valueOf(getDataForm(request, "idCheval"));
		int idCourse = Integer.valueOf(getDataForm(request, "idCourse"));
		String classementStr = getDataForm(request, nomChampClassement);
		int classement = 0;

		if (classementStr == null) {
			ajouterErreur(nomChampClassement, "Le champ classement est obligatoire");
		} else {
			try {
				classement = Integer.valueOf(classementStr);
			} catch (NumberFormatException e) {
				ajouterErreur(nomChampClassement, "Le classement doit être un nombre entier");
			}
		}

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
