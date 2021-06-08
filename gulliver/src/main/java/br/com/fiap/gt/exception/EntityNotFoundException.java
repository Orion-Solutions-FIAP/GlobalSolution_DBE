package br.com.fiap.gt.exception;

public class EntityNotFoundException extends Exception {

	public EntityNotFoundException() {
		super("Entity Not Found");
	}
	
	public EntityNotFoundException(String msg) {
		super(msg);
	}
	
}
