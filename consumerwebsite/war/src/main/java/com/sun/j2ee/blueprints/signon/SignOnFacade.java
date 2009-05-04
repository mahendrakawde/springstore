/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: SignOnFacade.java,v 1.2 2004/05/26 00:06:24 inder Exp $ */

package com.sun.j2ee.blueprints.signon;

import com.sun.j2ee.blueprints.util.dao.DAOFactory;
import com.sun.j2ee.blueprints.signon.dao.UserDAO;
import com.sun.j2ee.blueprints.signon.dao.JNDINames;
import com.sun.j2ee.blueprints.signon.dao.SignOnDAOFinderException;
import com.sun.j2ee.blueprints.signon.dao.InvalidPasswordException;
import com.sun.j2ee.blueprints.signon.dao.SignOnDAODupKeyException; 


public class SignOnFacade {

    //These constants are defined by the application to constrain
    //the user input fields. They are used to validate user entries
    //independent of the database
    public static final int MAX_USERID_LENGTH = 25;
    public static final int MAX_PASSWD_LENGTH = 25;

    private UserDAO userDAO = null; 

    public SignOnFacade() {
        userDAO = (UserDAO)DAOFactory.getDAO(JNDINames.SIGNON_DAO_CLASS);
    }

    /**
     * business method used to check if a user is allowed to sign on
     * @return true if user name and password match in database
     */
    public boolean authenticate(String userName, String password) {
        boolean valid = false;
        try {
            valid = userDAO.matchPassword(userName, password);
        } catch (SignOnDAOFinderException sfx) {
            return false;
        } catch (InvalidPasswordException ix) {
            return false;
        }
        return valid;
    }

    /** business method to call to create new users **/
    public void createSignOn(String userName, String password) 
        throws SignOnLongIdException,
               SignOnInvalidCharException,
               SignOnDupKeyException {

       //validate user input against application constraints
       isInputValidLength(userName, password);
       try {
           userDAO.createUser(userName, password);
       } catch (SignOnDAODupKeyException sdke) {
           throw new SignOnDupKeyException("Duplicate User: " + userName);
       }
    }

    /**
     * Performs user input validation for the application. 
     *
     * @throws CreateException if user input not within length limits 
    **/
    private void isInputValidLength(String userName, String password) throws 
                     SignOnLongIdException, SignOnInvalidCharException {
          // check the input data
        if(userName.length() > MAX_USERID_LENGTH) {
            throw new SignOnLongIdException("User ID cant be more than " +
            MAX_USERID_LENGTH + " chars long");
        }
        if(password.length() > MAX_PASSWD_LENGTH) {
            throw new SignOnLongIdException("Password cant be more than " +
            MAX_PASSWD_LENGTH + " chars long");
        }
        if( (userName.indexOf('%') != -1) ||
        (userName.indexOf('*') != -1) ) {
            throw new SignOnInvalidCharException("User Id cannot " +
            "have '%' or '*' characters");
        }
    }
}
