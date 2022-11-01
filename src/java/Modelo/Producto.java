package Modelo;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joseg
 */
public class Producto {
    private String producto, descripcion, imagen, fecha_ingreso;
    private int id, idmarca, existencia;
    private double precio_costo, precio_venta;
    Conexion cn;
    Connection con;
    int respuesta=0;
    
    public Producto(){  }

    public Producto(String producto, String descripcion, String imagen, String fecha_ingreso, int idmarca, int existencia, double precio_costo, double precio_venta, int id) {
        this.producto = producto;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fecha_ingreso = fecha_ingreso;
        this.idmarca = idmarca;
        this.existencia = existencia;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
        this.id = id;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public int modificar_existencia(int id, int exis) {
        String sql = "update productos set Existencia=? where idProducto=?";

        try {
            cn.abrir_conexion();
            con = cn.conexionBD;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, exis);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
        return respuesta;
    }
    
    public Producto Listaid(int id) {
        Producto pro = new Producto();
        String sql = "select * from productos where idProducto=" + id;
        try {
            cn.abrir_conexion();
            con = cn.conexionBD;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pro.setId(rs.getInt(1));
                pro.setProducto(rs.getString(2));
                pro.setPrecio_costo(rs.getDouble(6));
                pro.setExistencia(rs.getInt(8));

            }
        } catch (SQLException ex) {
        }
        return pro;
    }
    
    public int modificar_pVenta(int id, double nuevo_precio) {
        String sql = "update productos set Precio_venta=? where idProducto=?;";

        try {
            cn.abrir_conexion();
            con = cn.conexionBD;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nuevo_precio);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
        return respuesta;
    }
    
    public String obtener_imagen()
    {
        String imagen="";
        int id=22;
        try
        {
            cn=new Conexion();
            String consulta="select imagen from productos where idproducto=?;";
            cn.abrir_conexion();
            PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
            parametro.setInt(1, id);
            
            ResultSet peticion=parametro.executeQuery();
            while(peticion.next())
            {
                imagen="Imagenes/"+peticion.getString(1);
            }
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }
        
        
        return imagen;
    }

    
    public HashMap mostrar_producto()
    {
        HashMap<String, String> drop_producto=new HashMap();
        try
        {
            String codigo_sql="select idproducto as id, producto from productos";
            cn= new Conexion();
            cn.abrir_conexion();
            ResultSet consulta=cn.conexionBD.createStatement().executeQuery(codigo_sql);
            while(consulta.next())
            {
                drop_producto.put(consulta.getString("id"), consulta.getString("producto"));
            }
            
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }
        
        return drop_producto;
    }
    
    
    public int eliminar()
    {
        int devolver=0;
        try
        {
            PreparedStatement parametro;
            String codigo_sql="delete from db_punto_venta.productos where idproducto=?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(codigo_sql);
            parametro.setInt(1, getId());
            
            devolver=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(HeadlessException | SQLException ex)
        {
            System.out.println("error........"+ex.getMessage());
        }
        
        return devolver;
    } 
    
    
   public int modificar() {
        int devolver;
        {
            try {
                String fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
                PreparedStatement parametro;
                String codigo_sql = "update productos set Producto=?,idMarca=?,Descripcion=?,Precio_costo=?,Precio_venta=?,Existencia=?, Fecha_ingreso=?  where idProducto=?;";
                cn = new Conexion();
                cn.abrir_conexion();
                parametro = (PreparedStatement) cn.conexionBD.prepareStatement(codigo_sql);
                parametro.setString(1, this.getProducto());
                parametro.setInt(2, this.getIdmarca());
                parametro.setString(3, this.getDescripcion());
                parametro.setDouble(4, this.getPrecio_costo());
                parametro.setDouble(5, this.getPrecio_venta());
                parametro.setInt(6, this.getExistencia());
                parametro.setString(7, fecha);
                parametro.setInt(8, getId());

                devolver = parametro.executeUpdate();
                cn.cerrar_conexion();
            } catch (HeadlessException | SQLException ex) {
                System.out.println("error........" + ex.getMessage());
                devolver = 0;
            }

            return devolver;
        }
   }
    
    
   
   public int agregar()
    {
        int devolver=0;
        try
        {
            String fecha= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            PreparedStatement parametro;
            String codigo_sql="Insert into db_punto_venta.productos (producto, descripcion, imagen, fecha_ingreso, idmarca, existencia, precio_costo, precio_venta) values (?,?,?,?,?,?,?,?)";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(codigo_sql);
            parametro.setString(1, getProducto());
            parametro.setString(2, getDescripcion());
            parametro.setString(3, "Imagenes/"+getImagen());
            //parametro.setString(4, getFecha_ingreso());
            parametro.setString(4, fecha);
            parametro.setInt(5,    getIdmarca());
            parametro.setInt(6, getExistencia());
            parametro.setDouble(7, getPrecio_costo());
            parametro.setDouble(8, getPrecio_venta());
            
            devolver=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(HeadlessException | SQLException ex)
        {
            System.out.println("error........"+ex.getMessage());
        }
        
        return devolver;
    }
    
   public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            String query = "Select p.idproducto as id, p.producto, p.descripcion, p.imagen, p.precio_costo, p.precio_venta, p.existencia, p.fecha_ingreso, m.marca, p.idmarca,p.imagen from productos as p INNER JOIN marcas as m on p.idmarca=m.idmarca ORDER BY idproducto;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id", "producto", "descripcion", "Imagen", "precio_costo", "precio_venta", "Existencias", "Fecha ingreso", "marca", "id_marca", "img"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[11];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("producto");
                datos[2] = consulta.getString("descripcion");
                datos[3] = consulta.getString("imagen");
                datos[4] = consulta.getString("precio_costo");
                datos[5] = consulta.getString("precio_venta");
                datos[6] = consulta.getString("existencia");
                datos[7] = consulta.getString("fecha_ingreso");
                datos[8] = consulta.getString("marca");
                datos[9] = consulta.getString("idmarca");
                datos[10] = consulta.getString("imagen");

                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
        return tabla;
    }
    
   public DefaultTableModel leer2() {
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
   

