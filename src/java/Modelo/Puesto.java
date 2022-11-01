/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author joseg
 */
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis Fernando Paxel
 */
public class Puesto {
    private String puesto;
    private int id_puesto;
Conexion cn;
public Puesto(){   
}
    public Puesto(int id_puesto,String puesto) {
        this.puesto = puesto;
        this.id_puesto = id_puesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }
    
     public HashMap seleccionar(){
        HashMap <String, String> drop = new HashMap();
    try{
        cn = new Conexion();
        String query=("select Idpuesto as id,Puesto from puestos;");
        cn.abrir_conexion();
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        while(consulta.next()){
            drop.put(consulta.getString("id"), consulta.getString("Puesto"));
        }
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
        return drop;
    }

    
        public DefaultTableModel Leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "select Idpuesto as id,Puesto from puestos;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"Id", "puesto"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[2];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("Puesto");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return tabla;
    }
        
         public int agregar() {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = "insert into puestos(Puesto)values(?);";
                cn = new Conexion();
                cn.abrir_conexion();
                parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
                parametro.setString(1, this.getPuesto());
                retorno = parametro.executeUpdate();
                cn.cerrar_conexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("Error!!" + ex.getMessage());
                retorno = 0;

            }
            return retorno;
        }
    }
    public int modificar() {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = "update puestos set Puesto=? where Idpuesto=?;";
                cn = new Conexion();
                cn.abrir_conexion();
                parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
                parametro.setString(1, this.getPuesto());
                parametro.setInt(2, this.getId_puesto());
             
                retorno = parametro.executeUpdate();
                cn.cerrar_conexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("Error!!" + ex.getMessage());
                retorno = 0;

            }
            return retorno;
        }
       } 
    
      public int eliminar() {
        int retorno=0;

        {
            try {
                PreparedStatement parametro;
                String query = "delete from puestos where Idpuesto=?;";
                cn = new Conexion();
                cn.abrir_conexion();
                parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
                parametro.setInt(1, this.getId_puesto());
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
