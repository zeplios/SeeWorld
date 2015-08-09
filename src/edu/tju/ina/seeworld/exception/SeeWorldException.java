package edu.tju.ina.seeworld.exception;

public class SeeWorldException extends Exception {

	private static final long serialVersionUID = 5041044897976532742L;
	private String errorMsg;

	public SeeWorldException(Throwable e) {
		super(e);
		this.errorMsg = e.getMessage();
	}

	public SeeWorldException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
