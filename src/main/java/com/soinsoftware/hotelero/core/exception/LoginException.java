package com.soinsoftware.hotelero.core.exception;

/**
 * 
 * @author Carlos Rodriguez
 * @since 1.0.0
 *
 */
public class LoginException extends Exception {
	
	public static final String MSG_NOT_VALID = "Usuario y/o contrase&ntilde;a no validos.";

	public static final String MSG_NOT_ROLE = "El usuario no tiene permiso para ingresar a Hotelero.";

	private static final long serialVersionUID = 6207596019507777997L;

	public LoginException(final String message) {
		super(message);
	}
}