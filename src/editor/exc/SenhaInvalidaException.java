/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.exc;

/**
 *
 * @author helbert
 */
public class SenhaInvalidaException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SenhaInvalidaException(String message) {
        super(message);
    }

}
