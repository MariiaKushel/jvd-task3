package by.javacourse.task3.exeption;

public class MedicinsException extends Exception {

	public MedicinsException() {
		super();
	}

	public MedicinsException(String message) {
		super(message);
	}

	public MedicinsException(Exception e) {
		super(e);
	}

	public MedicinsException(String message, Exception e) {
		super(message, e);
	}
}
