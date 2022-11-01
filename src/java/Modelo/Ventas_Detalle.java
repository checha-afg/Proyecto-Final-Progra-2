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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joseg
 */
public class Ventas_Detalle {
    private int idventa, idproducto, cantidad;
    private double precio_unitario;
    Conexion cn;
    
    public Ventas_Detalle(){}
    
    public Ventas_Detalle(int idventa, int idproducto, int cantidad, double precio_unitario) {
        this.idventa = idventa;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
    
    public int modificando_cantidad()
    {
        int retorno=0, cantidad_de_ventas=0, cantidad_reducir=0, nuevaCantidad=0, nuevo_idprod=0, antiguo_idprod=0,validar_existencias=0;
        validar_existencias=obtener_cantidad(); //con este obtengo las existencias de la tabla productos
        antiguo_idprod=idprod_de_ventasdetalle();
        nuevo_idprod=getIdproducto();
        cantidad_de_ventas=cantidad_de_ventasdetalle(); //con este obtengo la cantidad de la tabla ventas detalle
        cantidad_reducir=getCantidad(); //con este obtengo la cantidad ingresada en la JSP
        int prue=cantidad_reducir-cantidad_de_ventas;
        
        if(validar_existencias>prue || (cantidad_de_ventas==cantidad_reducir && antiguo_idprod!=nuevo_idprod) || (cantidad_de_ventas==cantidad_reducir && antiguo_idprod==nuevo_idprod)) //aqui está el error   if(validar_existencias>cantidad_de_ventas)    if(validar_existencias>cantidad_reducir || (cantidad_de_ventas==cantidad_reducir && antiguo_idprod!=nuevo_idprod))
        { //if(validar_existencias>cantidad_reducir || (cantidad_de_ventas==cantidad_reducir && antiguo_idprod!=nuevo_idprod) || (cantidad_de_ventas==cantidad_reducir && antiguo_idprod==nuevo_idprod))
            if(antiguo_idprod==nuevo_idprod)
            {
                if(cantidad_de_ventas>cantidad_reducir)
                {
                    nuevaCantidad=cantidad_de_ventas-cantidad_reducir;
                    try
                    {
                        cn=new Conexion();
                        String consulta="update productos set existencia=existencia+? where idproducto=?";
                        cn.abrir_conexion();
                        PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
                        parametro.setInt(1, nuevaCantidad);
                        parametro.setInt(2, getIdproducto());

                        retorno=parametro.executeUpdate();
                        cn.cerrar_conexion();
                    }catch(Exception ex)
                    {

                    }
                }
                else if(cantidad_de_ventas<cantidad_reducir)
                {
                    nuevaCantidad=cantidad_reducir - cantidad_de_ventas;
                    try
                    {
                        cn=new Conexion();
                        String consulta="update productos set existencia=existencia-? where idproducto=?";
                        cn.abrir_conexion();
                        PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
                        parametro.setInt(1, nuevaCantidad);
                        parametro.setInt(2, getIdproducto());

                        retorno=parametro.executeUpdate();
                        cn.cerrar_conexion();
                    }catch(Exception ex)
                    {

                    }

                }
                else
                {
                    retorno=1;
                }
            }
            else
            {
                int validar_nuevo=getIdproducto();
                int para_nuevo_prod=obtener_cantidad();
                try
                {
                    if(para_nuevo_prod>cantidad_reducir)
                    {
                        
                        modificacion_antiguoidprod(cantidad_de_ventas);
                        
                    
                        cn=new Conexion();
                        String consulta="update productos set existencia=existencia-? where idproducto=?";
                        cn.abrir_conexion();
                        PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
                        parametro.setInt(1, cantidad_reducir);
                        parametro.setInt(2, nuevo_idprod);
                        retorno=parametro.executeUpdate();
                    }
                    
                    cn.cerrar_conexion();
                }catch(Exception ex)
                {

                }
            }
        }
        /*else if(cantidad_de_ventas==cantidad_reducir)
        {
            retorno=1;
        }*/
        else
        {
            retorno=0;
        }
        return retorno;
    }
    
    
    public void modificacion_antiguoidprod(int cantidad)
    {
        int antiguo_idprod=0, retorno=0;
        antiguo_idprod=idprod_de_ventasdetalle();
        try
        {
            cn=new Conexion();
            String consulta="update productos set existencia=existencia+? where idproducto=?";
            cn.abrir_conexion();
            PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
            parametro.setInt(1, cantidad);
            parametro.setInt(2, antiguo_idprod);
            
            retorno=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }
    }
    
    public int idprod_de_ventasdetalle()
    {
        int id=0;
        try
        {
            cn=new Conexion();
            String consulta="select idproducto from ventas_detalle where idventa_detalle=?";
            cn.abrir_conexion();
            PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
            parametro.setInt(1, getIdventa());
            
            ResultSet peticion=parametro.executeQuery();
            while(peticion.next())
            {
                id=peticion.getInt(1);
            }
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }
        return id;
    }
    
    
    public int cantidad_de_ventasdetalle()
    {
        int existencias=0;
        try
        {
            cn=new Conexion();
            String consulta="select cantidad from ventas_detalle where idventa_detalle=?";
            cn.abrir_conexion();
            PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
            parametro.setInt(1, getIdventa());
            
            ResultSet peticion=parametro.executeQuery();
            while(peticion.next())
            {
                existencias=peticion.getInt(1);
            }
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }
        return existencias;
    }
    
    
    
    
    public int reducir_cantidad()
    {
        int retorno=0, existencias=0, cantidad_reducir=0;
        existencias=obtener_cantidad();
        cantidad_reducir=getCantidad();
        if(existencias>cantidad_reducir)
        {
        try
        {
            cn=new Conexion();
            String consulta="update productos set existencia=existencia-? where idproducto=?";
            cn.abrir_conexion();
            PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
            parametro.setInt(1, cantidad_reducir);
            parametro.setInt(2, getIdproducto());
            
            retorno=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }
        }
        else
        {
            retorno=0;
        }
        
        return retorno;
    }
    
    
    public int obtener_cantidad()
    {
        int existencias=0;
        try
        {
            cn=new Conexion();
            String consulta="select existencia from productos where idproducto=?;";
            cn.abrir_conexion();
            PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
            parametro.setInt(1, getIdproducto());
            
            ResultSet peticion=parametro.executeQuery();
            while(peticion.next())
            {
                existencias=peticion.getInt(1);
            }
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
            
        }
        
        
        return existencias;
    }
    
    
    
    
    public double prueba_precio(String id_p)
    {
            //int id= Integer.valueOf(id_p);
            double precio_producto=0;
            try
            {
                cn=new Conexion();
                cn.abrir_conexion();
                String obtenerprecio_unitario="select precio_venta from productos where idproducto=?";
                PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(obtenerprecio_unitario);
                parametro.setString(1, id_p);
                ResultSet consulta=parametro.executeQuery();
                while(consulta.next())
                {
                    precio_producto=consulta.getDouble(1);
                }
                cn.cerrar_conexion();
            }catch(Exception ex)
            {
                
            }
            return precio_producto;
    }
    
    
    
    
    public double precio_unitario(int id)
    {
            double precio_producto=0;
            try
            {
                cn=new Conexion();
                cn.abrir_conexion();
                String obtenerprecio_unitario="select precio_venta from productos where idproducto=?";
                PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(obtenerprecio_unitario);
                parametro.setInt(1, id);
                ResultSet consulta=parametro.executeQuery();
                while(consulta.next())
                {
                    precio_producto=consulta.getDouble(1);
                }
                cn.cerrar_conexion();
            }catch(Exception ex)
            {
                
            }
            return precio_producto;
    }
    
    public int idventamax()
    {
            int mayor_idv=0;
            try
            {
            cn=new Conexion();
            cn.abrir_conexion();
            String buscar_idv_mayor="select MAX(idventa) from ventas";
            ResultSet consulta=cn.conexionBD.createStatement().executeQuery(buscar_idv_mayor);
            while(consulta.next())
            {
                mayor_idv=consulta.getInt(1);
            }
            cn.cerrar_conexion();
            }catch(Exception ex)
            {
                
            }
            return mayor_idv;
    }
    
    
    public int eliminar()
    {
        int devolver=0;
        try
        {
            PreparedStatement parametro;
            String codigo_sql="delete from db_punto_venta.ventas_detalle where idventa_detalle=?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(codigo_sql);
            parametro.setInt(1, getIdventa());
            
            devolver=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(HeadlessException | SQLException ex)
        {
            System.out.println("error........"+ex.getMessage());
        }
        
        return devolver;
    }  
    
    public DefaultTableModel Mostrar() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "select idVenta_detalle as id, m.idVenta,P.producto, m.Cantidad, m.Precio_unitario, P.Idproducto from ventas_detalle as m, productos as P where m.idProducto=P.idProducto order by idVenta_detalle ;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"Id", "Venta", "Cantidad", "precio", "producto", "idproducto"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[6];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("idVenta");
                datos[2] = consulta.getString("producto");
                datos[3] = consulta.getString("Cantidad");
                datos[4] = consulta.getString("Precio_unitario");
                datos[5] = consulta.getString("Idproducto");

                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return tabla;
    }
    
    public int modificar()
    {
        int devolver=0;
        double precio_u=precio_unitario(getIdproducto());
        try
        {
            PreparedStatement parametro;
            String codigo_sql="update ventas_detalle set  idproducto=?, cantidad=?, precio_unitario=? where idventa_detalle=?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(codigo_sql);
            
            parametro.setInt(1, getIdproducto());
            parametro.setInt(2, getCantidad());
            parametro.setDouble(3, precio_u);
            parametro.setInt(4, getIdventa());
            
            devolver=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(HeadlessException | SQLException ex)
        {
            System.out.println("error........"+ex.getMessage());
        }
        
        return devolver;
    } 
    
    public int agregar()
    {
        int retorno=0;
        double precio_u=precio_unitario(getIdproducto());
        try
        {
            PreparedStatement parametro;
            String  insertar = "insert into ventas_detalle(idventa,idproducto,cantidad,precio_unitario) VALUES (?,?,?,?)";
            cn=new Conexion();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(insertar);
            parametro.setInt(1, getIdventa());
            parametro.setInt(2, getIdproducto());
            parametro.setInt(3, getCantidad());
            parametro.setDouble(4, precio_u);
            retorno=parametro.executeUpdate();
            
            cn.cerrar_conexion();
        }catch(Exception ex)
        {

        }
        return retorno;
    }
    
    
    public DefaultTableModel leer()
    {
        DefaultTableModel tabla = new DefaultTableModel();
        try
        {
            cn = new Conexion();
            String query="select vd.idventa_detalle as id, v.serie,v.nofactura, v.fechafactura, prod.producto, vd.cantidad, vd.precio_unitario, c.nombres as nombresc, c.apellidos as apellidosc,c.NIT,e.nombres, e.apellidos, v.fechaingreso, vd.idproducto, v.idcliente, v.idempleado from ventas_detalle as vd INNER JOIN ventas v on vd.idventa=v.idventa INNER JOIN productos prod on vd.idproducto=prod.idproducto INNER JOIN	clientes c on v.idcliente=c.idcliente INNER JOIN empleados e on v.idempleado=e.idempleado ORDER BY idventa_detalle;";
            cn.abrir_conexion();
            ResultSet consulta=cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[]={"id","serie","nofactura","fechafactura","producto","cantidad","precio_unitario","nombresc","apellidosc","nit","nombres","apellidos","fechaingreso","id_producto","id_cliente","id_empleado"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[]= new String [16];
            while(consulta.next())
            {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("serie");
                datos[2] = consulta.getString("nofactura");
                datos[3] = consulta.getString("fechafactura");
                datos[4] = consulta.getString("producto");
                datos[5] = consulta.getString("cantidad");
                datos[6] = consulta.getString("precio_unitario");
                datos[7] = consulta.getString("nombresc");
                datos[8] = consulta.getString("apellidosc");
                datos[9] = consulta.getString("NIT");
                datos[10] = consulta.getString("nombres");
                datos[11] = consulta.getString("apellidos");
                datos[12] = consulta.getString("fechaingreso");
                datos[13] = consulta.getString("idproducto");
                datos[14] = consulta.getString("idcliente");
                datos[15] = consulta.getString("idempleado");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex)
        {
            System.out.println("Error:"+ex.getMessage());
        }
        return tabla;
    }
    
}