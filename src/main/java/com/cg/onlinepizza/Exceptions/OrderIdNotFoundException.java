package com.cg.onlinepizza.Exceptions;

public class OrderIdNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

		public OrderIdNotFoundException() {

		}

		public OrderIdNotFoundException(String message) {
			super(message);
		}
	}


