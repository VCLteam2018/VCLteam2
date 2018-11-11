/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.registration;

import java.io.Serializable;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author Lenovo
 */
public class RegistrationDAO implements Serializable{
    public boolean checkLogin(String username,String password) throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if(conn != null){
                String sql = "Select * From Registration Where username = ? and password=?  ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                
                rs = stm.executeQuery();
                if(rs.next())
                    return true;
            }         
        } finally {
            if(rs!=null) rs.close();
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }
        return false;
    }
    
    private List<RegistrationDTO> listAccount;

    public List<RegistrationDTO> getListAccount() {
        return listAccount;
    }
    public void searchByLastName(String value) throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if(conn != null){
                String sql = "Select * From Registration Where lastname Like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%"+value+"%");
                rs = stm.executeQuery();
                while(rs.next()){
                    String username =  rs.getString("username");
                    String password = rs.getString("password");
                    String lastname= rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    if(listAccount == null){
                        listAccount  = new ArrayList<RegistrationDTO>();
                    }
                    this.listAccount.add(dto);
                }
            }         
        } finally {
            if(rs!=null) rs.close();
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }
    }
    public boolean deleteRecord(String username) throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement stm = null;
        
        try {
            conn = DBUtils.makeConnection();
            if(conn != null){
                String sql = "Delete From Registration Where username=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                
                int row  = stm.executeUpdate();
                if(row>0){
                    return true;
                }
            }      
        } finally {
            
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }
        return false;
        
    }
    public boolean updatePassRole(String username, String password, boolean role) throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement stm = null;
        
        try {
            conn = DBUtils.makeConnection();
            if(conn != null){
                String sql = "update Registration set password=?, isAdmin =? where username=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                int row  = stm.executeUpdate();
                if(row>0){
                    return true;
                }
            }      
        } finally {
            
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }
        return false;
        
    }
    public boolean insertAccount(String username, String password, String fullname, boolean role) throws SQLException, NamingException{
         Connection conn = null;
        PreparedStatement stm = null;
        
        try {
            conn = DBUtils.makeConnection();
            if(conn != null){
                String sql = "Insert into Registration(username,password,lastname,isAdmin) Values(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, role);
                int row  = stm.executeUpdate();
                if(row>0){
                    return true;
                }
            }      
        } finally {
            
            if(stm!=null) stm.close();
            if(conn!=null) conn.close();
        }
        return false;
        
    }
    
}
