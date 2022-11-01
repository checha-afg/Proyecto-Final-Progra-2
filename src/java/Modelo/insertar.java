/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Luis Fernando Paxel
 */
public class insertar {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;
    int f = 0;

    public int agregar(Producto p) {

        String fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Conexion cn = new Conexion();
        String sql = "insert into productos(Producto,idMarca,Descripcion,Imagen,Precio_costo,Precio_venta,Existencia,Fecha_ingreso) values(?,?,?,?,?,?,?,?);";
        try {
            cn.abrir_conexion();
            con = cn.conexionBD;
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getProducto());
            ps.setInt(2, p.getIdmarca());
            ps.setString(3, p.getDescripcion());
            ps.setString(4, p.getImagen());
            ps.setDouble(5, p.getPrecio_costo());
            ps.setDouble(6, p.getPrecio_venta());
            ps.setInt(7, p.getExistencia());
            ps.setString(8, fecha);
            ps.executeUpdate();

        } catch (Exception ex) {

        }
        return r;
    }

    public int modificar(Producto pr) {

        String fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Conexion cn = new Conexion();
         String sql = "update productos set Producto=?,idMarca=?,Descripcion=?,Imagen=?,Precio_costo=?,Precio_venta=?,Existencia=?, Fecha_ingreso=?  where idProducto=?;";
    try {
            cn.abrir_conexion();
            con = cn.conexionBD;
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getProducto());
            ps.setInt(2, pr.getIdmarca());
            ps.setString(3, pr.getDescripcion());
            ps.setString(4, pr.getImagen());
            ps.setDouble(5, pr.getPrecio_costo());
            ps.setDouble(6, pr.getPrecio_venta());
            ps.setInt(7, pr.getExistencia());
            ps.setString(8, fecha);
            ps.setInt(9, pr.getId());
            ps.executeUpdate();

        } catch (Exception ex) {

        }
        return f;
    }

}
