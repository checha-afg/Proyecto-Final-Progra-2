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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author joseg
 */
public class Cliente extends Persona{
  private int genero;
  private String nit, correo_electronico,fecha_ingreso;
 public Cliente(){}
Conexion cn;
    public Cliente(int id, String nombres, String apellidos, String nit, int genero,  String telefono,String correo_electronico, String fecha_ingreso,String direccion, String fecha_nacimiento) {
        super(id, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.genero = genero;
        this.nit = nit;
        this.correo_electronico = correo_electronico;
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Map mostrar_c()
    {
        
        
        Map<String, List<String>> drop_cliente = new HashMap<String, List<String>>();
        try
        {
            //String codigo_sql="select idcliente as id, nombres, nit from clientes;";
            String codigo_sql="select idcliente as id, nombres, nit from clientes;";
            
            cn = new Conexion();
            cn.abrir_conexion();
            ResultSet consulta= cn.conexionBD.createStatement().executeQuery(codigo_sql);
            while(consulta.next())
            {
                //drop_cliente.put(consulta.getString("id"), new String[]{consulta.getString("nombres"), consulta.getString("nit")});
                List<String> valores = new ArrayList<String>();
                valores.add(consulta.getString("nombres"));
                valores.add(consulta.getString("nit"));
                drop_cliente.put(consulta.getString("id"), valores);

            }
            
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }
        
       
        
        return drop_cliente;
    }
    
    public HashMap seleccionar2() {
        HashMap<String, String> drop = new HashMap();
        try {
            cn = new Conexion();
            String query = ("select idCliente as id, Nombres,Apellidos from clientes;");
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()) {
                drop.put(consulta.getString("id"), consulta.getString("Nombres") + " " + consulta.getString("Apellidos"));
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return drop;
    }
    
    public HashMap seleccionar() {
        HashMap<String, String> drop = new HashMap();
        try {
            cn = new Conexion();
            String query = ("select idCliente as id, Nombres,Apellidos,NIT from clientes;");
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()) {
                drop.put(consulta.getString("id"), consulta.getString("Nombres") + " " + consulta.getString("Apellidos") + ", " + consulta.getString("NIT"));
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return drop;
    }
    
    public HashMap mostrar_clientes()
    {
        //Map<String, Entry<Action, Boolean>> actionMap = new HashMap<String, Entry<Action, Boolean>>();
        //HashMap<String, HashMap<String,String>> drop_cliente = new HashMap();
        HashMap<String,String> drop_cliente= new HashMap();
        try
        {
            //String codigo_sql="select idcliente as id, nombres, nit from clientes;";
            String codigo_sql="select idcliente as id, nombres from clientes;";
            cn = new Conexion();
            cn.abrir_conexion();
            ResultSet consulta= cn.conexionBD.createStatement().executeQuery(codigo_sql);
            while(consulta.next())
            {
                //drop_cliente.put(consulta.getString("id"), consulta.getString("nombres"), consulta.getString("nit"));
                drop_cliente.put(consulta.getString("id"),consulta.getString("nombres"));
            }
            
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }
        
        return drop_cliente;
    }
    
    
     @Override
    public int agregar() {
        int retorno;

        {
            try {

                String fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
                PreparedStatement parametro;
                String query = "insert into clientes(Nombres,Apellidos,NIT,Genero,Telefono,Correo_electronico,Fechaingreso)values(?,?,?,?,?,?,?);";
                cn = new Conexion();
                cn.abrir_conexion();
                parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
                parametro.setString(1, this.getNombres());
                parametro.setString(2, this.getApellidos());
                parametro.setString(3, this.getNit());
                parametro.setInt(4, this.getGenero());
                parametro.setString(5, this.getTelefono());
                parametro.setString(6, this.getCorreo_electronico());
                parametro.setString(7, fecha);

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
        String uno[] = {"Masculino", "Femenino"};
        String elegir;
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "select idCliente as id, Nombres,Apellidos,NIT,Genero,Telefono,Correo_electronico,Fechaingreso from clientes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"Id", "Nombres", "Apellidos", "Nit","Genero", "Telefono", "Correo","Fecha Ingreso"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[9];
            while (consulta.next()) {

                if (consulta.getString("Genero").equals("1")) {
                    elegir = uno[0];
                } else {
                    elegir = uno[1];
                }

                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("Nombres");
                datos[2] = consulta.getString("Apellidos");
                datos[3] = consulta.getString("NIT");
                datos[4] = elegir;
                datos[5] = consulta.getString("Telefono");
                datos[6] = consulta.getString("Correo_electronico");
                datos[7] = consulta.getString("Fechaingreso");
         
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return tabla;
    }

 
  @Override
 public int modificar() {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = "update clientes set Nombres=?,Apellidos=?,NIT=?,Genero=?,Telefono=?,Correo_electronico=? where idCliente=?;";
                cn = new Conexion();
                cn.abrir_conexion();
                parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
                parametro.setString(1, this.getNombres());
                parametro.setString(2, this.getApellidos());
                parametro.setString(3, this.getNit());
                parametro.setInt(4, this.getGenero());
                parametro.setString(5, this.getTelefono());
                parametro.setString(6, this.getCorreo_electronico());
                parametro.setInt(7, this.getId());

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
    public int eliminar() 
    {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = "delete from clientes where idCliente=?;";
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

/* HashMap<String,String[]> drop_cliente= new HashMap();
        try
        {
            //String codigo_sql="select idcliente as id, nombres, nit from clientes;";
            String codigo_sql="select idcliente as id, nombres, nit from clientes;";
            cn = new Conexion();
            cn.abrir_conexion();
            ResultSet consulta= cn.conexionBD.createStatement().executeQuery(codigo_sql);
            while(consulta.next())
            {
                drop_cliente.put(consulta.getString("id"), new String[]{consulta.getString("nombres"), consulta.getString("nit")});
            }
            
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }*/