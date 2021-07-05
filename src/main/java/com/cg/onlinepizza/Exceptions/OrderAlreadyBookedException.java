package com.cg.onlinepizza.Exceptions;

public class OrderAlreadyBookedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderAlreadyBookedException(String message) {
		super(message);
		
	}

}
