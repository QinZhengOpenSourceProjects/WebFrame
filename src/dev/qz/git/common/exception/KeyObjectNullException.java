package dev.qz.git.common.exception;

/**
 * 
 * -----------------------------------------------------------------------------
 * key object null exception ,self defined exception,satisfy the framework needs
 * -----------------------------------------------------------------------------
 * 
 * @author Qin Zheng
 *
 */

/*
 * 
 * when the key target object is null throws this exception
 */

public class KeyObjectNullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// override the parent class constructor
	public KeyObjectNullException() {

		super();

	}

	// override the parent class constructor
	public KeyObjectNullException(String ExMessage) {

		super(ExMessage);

	}

}
