package edu.tju.ina.seeworld.exception;

public class SeeWorldRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 672443251546233936L;

	private String errorMsg;

	public SeeWorldRuntimeException(RuntimeException e) {
		super(e);
		errorMsg = e.getMessage();
	}

	public SeeWorldRuntimeException(String errorMsg) {
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
