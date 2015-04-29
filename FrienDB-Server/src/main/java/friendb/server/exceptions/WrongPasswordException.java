/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendb.server.exceptions;

/**
 *
 * @author evanguby
 */
public class WrongPasswordException extends Exception {

    /**
     * Creates a new instance of <code>WrongPasswordException</code> without
     * detail message.
     */
    public WrongPasswordException()
    {
        super();
    }

    /**
     * Constructs an instance of <code>WrongPasswordException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public WrongPasswordException(String msg)
    {
        super(msg);
    }
    
}
