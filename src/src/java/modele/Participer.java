package modele;

public class Participer {

	private int place;
	private Course course;
	private Cheval cheval;

	public Participer() {
		this(0, null, null);
	}

	public Participer(int place, Course course, Cheval cheval) {
		this.place = place;
		this.course = course;
		this.cheval = cheval;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Cheval getCheval() {
		return cheval;
	}

	public void setCheval(Cheval cheval) {
		this.cheval = cheval;
	}
}
