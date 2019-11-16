/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsi.m1.utils;

/**
 *
 * @author nox
 */
public class Constants {
    public static final String URL = "jdbc:derby://localhost:1527/JEEPRJ";
    public static final String USER_BDD = "jee";
    public static final String MDP_BDD = "jee";
    public static final String Query_SELECT_ALL_EMPLOYEES = "SELECT * from EMPLOYEES";
    public static final String Query_SELECT_ONE_EMPLOYEE = "SELECT * from EMPLOYEES WHERE ID = ?";
    public static final String Query_DELETE_ONE_EMPLOYEE ="DELETE FROM EMPLOYEES WHERE ID = ?";
    public static final String Query_UPDATE_ONE_EMPLOYEE ="UPDATE EMPLOYEES SET LASTNAME = ?, FIRSTNAME = ?, HOMEPHONE = ?, MOBILEPHONE = ?, WORKPHONE = ?, ADDRESS = ?, ZIPCODE = ?, CITY = ?, MAIL = ? WHERE ID = ?";
    public static final String Query_ADD_ONE_EMPLOYEE ="INSERT INTO EMPLOYEES(LASTNAME, FIRSTNAME, HOMEPHONE, MOBILEPHONE, WORKPHONE, ADDRESS, ZIPCODE, CITY, MAIL) VALUES (?,?,?,?,?,?,?,?,?)";
    /**Login input's name*/
    public static final String FRM_LOGIN = "loginForm";
    /**Password input's name*/
    public static final String FRM_PASSWORD = "passwordForm";
    /**Error (login informations) message*/
    public static final String ERR_LOGIN = "Echec de la connexion ! Vérifiez votre login et/ou mot de passe et essayez à nouveau.";
    /**Success (delete) message*/
    public static final String SUPP_OK = "Suppression réussie.";
    /**Error (no employee selected) message*/
    public static final String ERR_SELECT = "Veuillez sélectionner un employé.";
    /**Success (add) message*/
    public static final String ADD_OK =  "Ajout réussi.";
    /**Error (form has been filled with wrong parameters) message*/
    public static final String FORM_KO = "Merci de remplir le formulaire avec des informations valides.";
    /**Success (modify) message*/
    public static final String UPDT_OK =  "Modification réussie.";
    /**Button's name*/
    public static final String BTN_NAME =  "button";
    /**Radio button's name*/
    public static final String RADIO_BTN =  "selector";
    /**Employee form - lastName input*/
    public static final String LN_FRM =  "lastNameInput";
    /**Employee form - firstName input*/
    public static final String FN_FRM =  "firstNameInput";
    /**Employee form - homePhone input*/
    public static final String HP_FRM =  "homePhoneInput";
    /**Employee form - mobilePhone input*/
    public static final String MP_FRM =  "mobilePhoneInput";
    /**Employee form - workPhone input*/
    public static final String WP_FRM =  "workPhoneInput";
    /**Employee form - address input*/
    public static final String ADDR_FRM =  "addressInput";
    /**Employee form - ZIP input*/
    public static final String ZIP_FRM =  "zipInput";
    /**Employee form - city input*/
    public static final String CITY_FRM =  "cityInput";
    /**ُEmployee form - mail input*/
    public static final String MAIL_FRM = "mailInput";
}
