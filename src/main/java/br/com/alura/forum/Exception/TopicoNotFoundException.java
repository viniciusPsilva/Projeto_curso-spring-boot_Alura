package br.com.alura.forum.Exception;

public class TopicoNotFoundException extends MyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TopicoNotFoundException(String msg) {
		super(msg);
	}
}
