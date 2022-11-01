/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 * @author Luis Fernando Paxel
 */
public class Proveedor extends Persona {

    private String nit;

    public Proveedor() {
    }
    Conexion cn;

    public Proveedor(int id, String nombres, String nit, String direccion, String telefono, String apellidos, String fecha_nacimiento) {
        super(id, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.nit = nit;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    
    public HashMap mostrar_proveedor()
    {
        HashMap<String, String> drop_marca = new HashMap();
        try
        {
            String codigo_sql="Select idproveedor as id, proveedor from proveedores order by id";
            cn = new Conexion();
            cn.abrir_conexion();
            ResultSet consulta=cn.conexionBD.createStatement().executeQuery(codigo_sql);
            while(consulta.next())
            {
                drop_marca.put(consulta.getString("id"), consulta.getString("proveedor"));
            }
            
            cn.cerrar_conexion();    
        }catch(SQLException ex)
        {
         System.out.println(ex.getMessage());   
        }
        
        return drop_marca;
    }
    
    @Override
    public int agregar() {
        int retorno;

        {
            try {

                PreparedStatement parametro;
                String query = "insert into proveedores(Proveedor,Nit,Direccion,Telefono) values(?,?,?,?);";
                cn = new Conexion();
                cn.abrir_conexion();
                parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
                parametro.setString(1, this.getNombres());
                parametro.setString(2, this.getNit());
                parametro.setString(3, this.getDireccion());
                parametro.setString(4, this.getTelefono());

                retorno = parametro.executeUpdate();
                cn.cerrar_conexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("Error!!22" + ex.getMessage());
                retorno = 0;

            }
            return retorno;
        }
    }

    public DefaultTableModel leer3() {
        DefaultTableModel tabla = new DefaultTableModel();

        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "select idProveedor as id,Proveedor,Nit,Direccion,Telefono from proveedores;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"Id", "Proveedor", "Nit", "Direccion", "Telefono"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[9];
            while (consulta.next()) {

                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("Proveedor");
                datos[2] = consulta.getString("Nit");
                datos[3] = consulta.getString("Direccion");
                datos[4] = consulta.getString("Telefono");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return tabla;
    }

    public int modificar() {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = "update proveedores set Proveedor=?,Nit=?,Direccion=?,Telefono=? where idProveedor=?;";
                cn = new Conexion();
                cn.abrir_conexion();
                parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
                parametro.setString(1, this.getNombres());
                parametro.setString(2, this.getNit());
                parametro.setString(3, this.getDireccion());
                parametro.setString(4, this.getTelefono());
                parametro.setInt(5, this.getId());

                retorno = parametro.executeUpdate();
                cn.cerrar_conexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("Error!!" + ex.getMessage());
                retorno = 0;

            }
            return retorno;
        }
    }
    
    @Override
    public int eliminar() {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = "delete from proveedores where idProveedor=?;";
                cn = new Conexion();
                cn.abrir_conexion();
                parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
                parametro.setInt(1, this.getId());
                retorno = parametro.executeUpdate();
                cn.cerrar_conexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("Error!!" + ex.getMessage());
                retorno = 0;

            }
            return retorno;
        } 
       }


}
