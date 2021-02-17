package exception;

public class MyException extends RuntimeException{
	public MyException() {
		super();
	}
	public MyException(String messege) {
		super(messege);
	}
	public MyException(String messege, Throwable throwable) {
		super(messege,throwable);
	}
}