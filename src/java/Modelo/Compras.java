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

/**
 *
 * @author joseg
 */
public class Compras {
    private int idcompra,no_orden_compra,idproveedor;
    private String fecha_orden;
    Conexion cn;
    
    public Compras(){}
    
    public Compras(int idcompra, int no_orden_compra, int idproveedor, String fecha_orden) {
        this.idcompra = idcompra;
        this.no_orden_compra = no_orden_compra;
        this.idproveedor = idproveedor;
        this.fecha_orden = fecha_orden;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getNo_orden_compra() {
        return no_orden_compra;
    }

    public void setNo_orden_compra(int no_orden_compra) {
        this.no_orden_compra = no_orden_compra;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(String fecha_orden) {
        this.fecha_orden = fecha_orden;
    }
    
    public int eliminar()
    {
        int devolver=0;
        try
        {
            PreparedStatement parametro;
            String codigo_sql="delete from db_punto_venta.compras where idcompra=?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(codigo_sql);
            parametro.setInt(1, getIdcompra());
            
            devolver=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(HeadlessException | SQLException ex)
        {
            System.out.println("error........"+ex.getMessage());
        }
        
        return devolver;
    }
    
    
    public int modificar()
    {
        int devolver=0;
        try
        {
            String fecha= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            cn = new Conexion();
            cn.abrir_conexion();
            PreparedStatement parametros;
            String consulta_sql="update compras set idproveedor=?, fecha_orden=?, fechaingreso=? where idcompra=?";
            parametros=(PreparedStatement)cn.conexionBD.prepareStatement(consulta_sql);
            parametros.setInt(1, getIdproveedor());
            parametros.setString(2, getFecha_orden());
            parametros.setString(3, fecha);
            parametros.setInt(4, getIdcompra());
            devolver=parametros.executeUpdate();
        }catch(SQLException ex)
        {
            
        }
        
        return devolver;
    }
    
    public int no_orden()
    {
            int orden=0;
            try
            {
            cn=new Conexion();
            cn.abrir_conexion();
            String buscar_idc_mayor="select MAX(no_orden_compra) from compras;";
            ResultSet consulta=cn.conexionBD.createStatement().executeQuery(buscar_idc_mayor);
            while(consulta.next())
            {
                orden=consulta.getInt(1);
            }
            cn.cerrar_conexion();
            }catch(Exception ex)
            {
                
            }
            return orden;
    }
    
    public int agregar()
    {
        int retorno=0;
        //double precio_u=precio_unitario(getIdproducto());
        try
        {
            String fecha= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            PreparedStatement parametro;
            String  insertar = "insert into compras(no_orden_compra,idproveedor,fecha_orden,fechaingreso) VALUES (?,?,?,?)";
            cn=new Conexion();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(insertar);
            parametro.setInt(1, getNo_orden_compra());
            parametro.setInt(2, getIdproveedor());
            parametro.setString(3, getFecha_orden());
            parametro.setString(4, fecha);
            retorno=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(Exception ex)
        {

        }
        return retorno;
    }
    
}
