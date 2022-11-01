/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joseg
 */
public class Producto2 {
     private int id, idmarca, existencia;
    private String producto, descripcion;
    private double precio;

    public Producto2() {
    }
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int respuesta = 0;

    public Producto2(int id, int idmarca, String producto, String descripcion, int existencia, double precio) {
        this.id = id;
        this.idmarca = idmarca;
        this.producto = producto;
        this.descripcion = descripcion;
        this.existencia = existencia;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            String query = "Select p.idproducto as id, p.producto, p.descripcion, p.precio_costo, p.existencia, m.marca, p.idmarca from productos as p INNER JOIN marcas as m on p.idmarca=m.idmarca ORDER BY idproducto;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id", "producto", "descripcion", "precio_venta", "Existencias", "marca", "id_marca"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[8];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("producto");
                datos[2] = consulta.getString("descripcion");
                datos[3] = consulta.getString("precio_costo");
                datos[4] = consulta.getString("existencia");
                datos[5] = consulta.getString("marca");
                datos[6] = consulta.getString("idmarca");

                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
        return tabla;
    }

    public Producto2 Listaid(int id) {
        Producto2 pro = new Producto2();
        String sql = "select * from productos where idProducto=" + id;
        try {
            cn.abrir_conexion();
            con = cn.conexionBD;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro.setId(rs.getInt(1));
                pro.setProducto(rs.getString(2));
                pro.setPrecio(rs.getDouble(6));
                pro.setExistencia(rs.getInt(8));

            }
        } catch (SQLException ex) {
        }
        return pro;
    }
  public Producto2 Listaid2(int id) {
        Producto2 pro = new Producto2();
        String sql = "select * from productos where idProducto=" + id;
        try {
            cn.abrir_conexion();
            con = cn.conexionBD;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro.setId(rs.getInt(1));
                pro.setProducto(rs.getString(2));
                pro.setPrecio(rs.getDouble(7));
                pro.setExistencia(rs.getInt(8));

            }
        } catch (SQLException ex) {
        }
        return pro;
    }

    public int modificar_existencia(int id, int exis) throws SQLException {
        String sql = "update productos set Existencia=? where idProducto=?";

        cn.abrir_conexion();
        con = cn.conexionBD;
        ps = con.prepareStatement(sql);
        ps.setInt(1, exis);
        ps.setInt(2, id);
        ps.executeUpdate();
        return respuesta;
    }

    public int modificar_pVenta(int id, double nuevo_precio) throws SQLException {
        String sql = "update productos set Precio_venta=? where idProducto=?;";

        cn.abrir_conexion();
        con = cn.conexionBD;
        ps = con.prepareStatement(sql);
        ps.setDouble(1, nuevo_precio);
        ps.setInt(2, id);
        ps.executeUpdate();
        return respuesta;
    }

    public Producto2 buscar(int id) {
        Producto2 pr = new Producto2();
        String sql = "select * from productos where idProducto=" + id;
        try {
            cn.abrir_conexion();
            con = cn.conexionBD;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pr.setId(rs.getInt(1));
                pr.setProducto(rs.getString(2));
                pr.setPrecio(rs.getDouble(3));
            }

        } catch (Exception ex) {

        }
        return pr;
    }

    public HashMap sele_prod() {
        HashMap<String, String> drop = new HashMap();
        try {
            cn = new Conexion();
            String query = ("select idProducto as id, Producto,Precio_venta  from productos;");
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()) {
                drop.put(consulta.getString("id"), consulta.getString("Producto") + " |    Precio:" + consulta.getString("Precio_venta"));
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return drop;
    }
}
