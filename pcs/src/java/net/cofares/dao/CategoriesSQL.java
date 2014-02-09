/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author pfares
 */
public class CategoriesSQL {
    
    
    private DataSource getOffreDemande() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/offreDemande");
    }
    
}
