/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts.login;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.upload.MultipartRequestHandler;
import sample.registration.RegistrationDAO;

/**
 *
 * @author nvluo
 */
public class LoginStrutsActionForm extends org.apache.struts.action.ActionForm {
    private String username;
    private String password;

    /**
     *
     */
    public LoginStrutsActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public boolean checkLogin(){
       RegistrationDAO dao = new RegistrationDAO();
            
            try{
            boolean result = dao.checkLogin(username, password);
            return result;
        }catch(SQLException ex){
            Logger.getLogger(LoginStrutsActionForm.class.getName()).log(Level.SEVERE, null,ex);
        }catch(NamingException ex){
         Logger.getLogger(LoginStrutsActionForm.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return true;
    }
   

  

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
   
}
