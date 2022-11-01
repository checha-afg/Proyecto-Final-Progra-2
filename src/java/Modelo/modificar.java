/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Luis Fernando Paxel
 */
public class modificar {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;
    int f = 0;

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

    public int modificar22(Producto pr) {

        String fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Conexion cn = new Conexion();
        String sql = "update productos set Imagen=? where idProducto=?;";
        try {
            cn.abrir_conexion();
            con = cn.conexionBD;
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getImagen());
            ps.setInt(2, pr.getId());
            ps.executeUpdate();

        } catch (Exception ex) {

        }
        return f;
    }
}