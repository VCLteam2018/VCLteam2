/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.io.Serializable;
import java.sql.Connection;

import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author nvluo
 */
public class DBUtils implements Serializable {
    public static Connection makeConnection() throws  SQLException, NamingException{
        Context context = new InitialContext();
        Context tomcatCtx = (Context) context.lookup("java:comp/env");
        
        DataSource ds = (DataSource) tomcatCtx.lookup("Struts1SE1278");
        if (ds!=null) {
            Connection con = ds.getConnection();
            return con;
        }
        return null;
    }
    }
    

