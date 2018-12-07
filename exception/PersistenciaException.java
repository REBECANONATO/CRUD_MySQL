package br.edu.devmedia.jdbc.exception;

public class PersistenciaException extends Exception {


	private static final long serialVersionUID = -8916895283914218760L;

	public PersistenciaException(String msg, Exception exception) {
		super(msg, exception);
	}
	public PersistenciaException(String msg) {
		super(msg);
	}
	
	
	
	
}
