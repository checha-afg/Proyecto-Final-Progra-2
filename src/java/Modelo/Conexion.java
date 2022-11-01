/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author joseg
 */
public class Conexion {
    public Connection conexionBD;
    private final String bd="db_proyectofinal";
    private final String puerto="3306";
    private final String urlConexion = String.format("jdbc:mysql://localhost:%s/%s",puerto,bd);
    private final String usuario="root";
    private final String contra="AaBb12..";
    private final String jdbc="com.mysql.cj.jdbc.Driver";
    
    public void abrir_conexion()
    {
        try
        {
            Class.forName(jdbc);
            conexionBD=DriverManager.getConnection(urlConexion,usuario,contra);
            //JOptionPane.showMessageDialog(null, "conexion exitosa ","exito",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(HeadlessException | ClassNotFoundException | SQLException ex)
        {
            System.out.println("error, sad........"+ex.getMessage());
        }
    }
    public void cerrar_conexion()
    {
        try
        {
            conexionBD.close();
            
        }catch(SQLException ex)
        {
            System.out.println("error........"+ex.getMessage());
        }
    }
}

