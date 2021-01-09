/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.entity;

/**
 *
 * @author elois
 */
public class Database {
   private String us;
   private String pass;
   private String url;
   private String driver;
   
   
   public Database() {
   this.us="root";
   this.pass="root";
   this.url="jdbc:mysql://localhost:3308/schsdb";
   this.driver="com.mysql.jdbc.Driver";

}

    public String getUs() {
        return us;
    }

    public void setUs(String us) {
        this.us = us;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
