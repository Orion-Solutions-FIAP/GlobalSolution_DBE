package br.com.fiap.gt.exception;

public class CommitException extends Exception {

	public CommitException() {
		super("Error performing commit");
	}

	public CommitException(String msg) {
		super(msg);
	}
}
