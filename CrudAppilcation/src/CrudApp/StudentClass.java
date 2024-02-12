package CrudApp;

public class StudentClass {
	private int studId;
	private String studName;
	private String studEmailID;
	private char studGrade;
	private long studNumber;

	public int getStudId() {
		return studId;
	}

	public void setStudId(int studId) {
		this.studId = studId;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public String getStudEmailID() {
		return studEmailID;

	}

	public void setStudEmailID(String studEmailID) {
		this.studEmailID = studEmailID;
	}

	public char getStudGrade() {
		return studGrade;
	}

	public void setStudGrade(char c) {
		this.studGrade = c;
	}

	public long getStudNumber() {
		return studNumber;
	}

	public void setStudNumber(long l) {
		this.studNumber = l;
	}

}