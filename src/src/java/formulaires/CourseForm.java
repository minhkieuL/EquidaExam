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

		String nomChampNom = "nom";
		String nomChampDate = "date";
		String nomChampVille = "ville";

		String nom = getDataForm(request, nomChampNom);
		String date = getDataForm(request, nomChampDate);
		String ville = getDataForm(request, nomChampVille);

		if (nom == null) {
			ajouterErreur(nomChampNom, "Le champ nom est obligatoire");
		} else {
			if (nom.length() < 3 || nom.length() > 32) {
				ajouterErreur(nomChampNom, "La longueur du nom doit être compris entre 3 et 32 charactères");
			}
		}

		if (date == null) {
			ajouterErreur(nomChampDate, "Le champ date est obligatoire");
		} else {
			//TODO Vérifier qu'il s'agit bien d'une date
		}

		if (ville == null) {
			ajouterErreur(nomChampVille, "Le champ ville est obligatoire");
		} else {
			if (ville.length() < 3 || ville.length() > 32) {
				ajouterErreur(nomChampVille, "La longueur de lav ville doit être compris entre 3 et 32 charactères");
			}
		}

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
