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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joseg
 */
public class Compras_detalle {
    private int idcompra_detalle,idcompra,idproducto,cantidad;
    private double precio_costo_unitario;
    Conexion cn;
    
    public Compras_detalle(){}
    
    public Compras_detalle(int idcompra_detalle, int idcompra, int idproducto, int cantidad, double precio_costo_unitario) {
        this.idcompra_detalle = idcompra_detalle;
        this.idcompra = idcompra;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precio_costo_unitario = precio_costo_unitario;
    }
    
    
    
    public int getIdcompra_detalle() {
        return idcompra_detalle;
    }

    public void setIdcompra_detalle(int idcompra_detalle) {
        this.idcompra_detalle = idcompra_detalle;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
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

    public double getPrecio_costo_unitario() {
        return precio_costo_unitario;
    }

    public void setPrecio_costo_unitario(double precio_costo_unitario) {
        this.precio_costo_unitario = precio_costo_unitario;
    }
    
    
    ///////////////////////////////////////////////////////////////////
    
    public int modificando_cantidades()
    {
        int devolver=0;
        int antiguo_idprod, nuevo_idprod, validar_existencias, cantidad_de_registro, cantidad_a_reducir, validar, nueva_cantidad;
        antiguo_idprod=id_prod_de_registro();   //obteniendo el id del registro
        nuevo_idprod=getIdproducto();           //obteniendo el nuevo id
        validar_existencias=obtener_cantidad_de_registro(); //obteniendo la cantidad de existencias de la tabla productos
        cantidad_de_registro=cantidad_de_comprasdetalle();  //obteniendo la cantidad del registro compras_detalle
        cantidad_a_reducir=getCantidad();       //la cantidad que se va a reducir o aumentar
        validar=cantidad_de_registro-cantidad_a_reducir;    //resta para la validacion
        
       
        if(validar_existencias>validar || (cantidad_de_registro==cantidad_a_reducir && antiguo_idprod!=nuevo_idprod) || (cantidad_de_registro==cantidad_a_reducir && antiguo_idprod==nuevo_idprod)) //aqui está el error   if(validar_existencias>cantidad_de_ventas)    if(validar_existencias>cantidad_reducir || (cantidad_de_ventas==cantidad_reducir && antiguo_idprod!=nuevo_idprod))
        {
            if(antiguo_idprod==nuevo_idprod)
            {
                if(cantidad_de_registro>cantidad_a_reducir)
                {
                    nueva_cantidad=cantidad_de_registro-cantidad_a_reducir;
                    try
                    {
                        cn=new Conexion();
                        String consulta="update productos set existencia=existencia-? where idproducto=?";
                        cn.abrir_conexion();
                        PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
                        parametro.setInt(1, nueva_cantidad);
                        parametro.setInt(2, nuevo_idprod);

                        devolver=parametro.executeUpdate();
                        cn.cerrar_conexion();
                    }catch(SQLException ex)
                    {

                    }
                }
                else if(cantidad_de_registro<cantidad_a_reducir)
                {
                    nueva_cantidad=cantidad_a_reducir - cantidad_de_registro;
                    try
                    {
                        cn=new Conexion();
                        String consulta="update productos set existencia=existencia+? where idproducto=?";
                        cn.abrir_conexion();
                        PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
                        parametro.setInt(1, nueva_cantidad);
                        parametro.setInt(2, getIdproducto());

                        devolver=parametro.executeUpdate();
                        cn.cerrar_conexion();
                    }catch(SQLException ex)
                    {

                    }

                }
                else{return 1;}
            }
            else
            {
                int nuevo_prod=obtener_cantidad_de_registro(); //cantidad de la tabla productos
                try
                {
                    if(nuevo_prod>cantidad_de_registro) //cantidad de compras detalle
                    {
                        
                        modificacion_antiguoidprod(cantidad_de_registro); //cantidad de compras_Detalle
                        
                    
                        cn=new Conexion();
                        String consulta="update productos set existencia=existencia+? where idproducto=?";
                        cn.abrir_conexion();
                        PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
                        parametro.setInt(1, cantidad_a_reducir);
                        parametro.setInt(2, nuevo_idprod);
                        devolver=parametro.executeUpdate();
                    }
                    
                    cn.cerrar_conexion();
                }catch(SQLException ex)
                {

                }
            }
            
            
        }
        
        return devolver;
    }
    
    
    public void modificacion_antiguoidprod(int cantidad)
    {
        int antiguo_idprod=0, retorno=0;
        antiguo_idprod=id_prod_de_registro();
        try
        {
            cn=new Conexion();
            String consulta="update productos set existencia=existencia-? where idproducto=?";
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
    
    public int cantidad_de_comprasdetalle()
    {
        int existencias=0;
        try
        {
            cn=new Conexion();
            String consulta="select cantidad from compras_detalle where idcompra_detalle=?";
            cn.abrir_conexion();
            PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
            parametro.setInt(1, getIdcompra_detalle());
            
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
    
    public int obtener_cantidad_de_registro()
    {
        int existencias=0;
        try
        {
            cn=new Conexion();
            String consulta="select existencia from productos where idproducto=?;";
            cn.abrir_conexion();
            PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
            parametro.setInt(1, id_prod_de_registro());
            
            ResultSet peticion=parametro.executeQuery();
            while(peticion.next())
            {
                existencias=peticion.getInt(1);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex)
        {
            
        }
        
        
        return existencias;
    }
    /*
    public int obtener_cantidad_de_registro()
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
        }catch(SQLException ex)
        {
            
        }
        
        
        return existencias;
    }
    */
    public int id_prod_de_registro()
    {
        int mayor_idp=0;
        try
        {
            cn=new Conexion();
            cn.abrir_conexion();
            String idprod_de_registro="select idproducto from compras_detalle where idcompra_Detalle=?";
            PreparedStatement parametros=(PreparedStatement)cn.conexionBD.prepareStatement(idprod_de_registro);
            parametros.setInt(1, getIdcompra_detalle());
            ResultSet peticion=parametros.executeQuery();
            while(peticion.next())
            {
                mayor_idp=peticion.getInt(1);
            }
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
                
        }
        return mayor_idp;
    }
    ///////////////////////////////////////////////////////////////////
    
    
    public int idcompra_max()
    {
        int mayor_idc=0;
        try
        {
            cn=new Conexion();
            cn.abrir_conexion();
            String buscar_idc_mayor="select MAX(idcompra) from compras";
            ResultSet consulta=cn.conexionBD.createStatement().executeQuery(buscar_idc_mayor);
            while(consulta.next())
            {
                mayor_idc=consulta.getInt(1);
            }
            cn.cerrar_conexion();
        }catch(Exception ex)
        {
                
        }
        return mayor_idc;
    }
    
    public int modificacion_eliminar()
    {
        int antiguo_idprod=0, retorno=0, cantidad_disponible, cantidad_eliminar;
        cantidad_disponible=obtener_cantidad_de_registro();
        cantidad_eliminar=cantidad_de_comprasdetalle();
        antiguo_idprod=id_prod_de_registro();
        if(cantidad_disponible>cantidad_eliminar)
        {
            try
            {
                cn=new Conexion();
                String consulta="update productos set existencia=existencia-? where idproducto=?";
                cn.abrir_conexion();
                PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
                parametro.setInt(1, cantidad_eliminar);
                parametro.setInt(2, antiguo_idprod);

                retorno=parametro.executeUpdate();
                cn.cerrar_conexion();
            }catch(Exception ex)
            {

            }
        }
        return retorno;
    }
    
    
    public int eliminar()
    {
        int devolver=0;
        try
        {
            PreparedStatement parametro;
            String codigo_sql="delete from db_punto_venta.compras_detalle where idcompra_detalle=?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(codigo_sql);
            parametro.setInt(1, getIdcompra_detalle());
            
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
            cn = new Conexion();
            cn.abrir_conexion();
            PreparedStatement parametros;
            String consulta_sql="update compras_detalle set  idproducto=?, cantidad=?, precio_costo_unitario=? where idcompra_detalle=?";
            parametros=(PreparedStatement)cn.conexionBD.prepareStatement(consulta_sql);
            
            parametros.setInt(1, getIdproducto());
            parametros.setInt(2, getCantidad());
            parametros.setDouble(3, getPrecio_costo_unitario());
            parametros.setInt(4, getIdcompra_detalle());
            
            devolver=parametros.executeUpdate();
            modificar_costo_prod(0);
            cn.cerrar_conexion();
        }catch(SQLException ex)
        {
            
        }
        
        return devolver;
    }
    
    public int agregar()
    {
        int retorno=0;
        //double precio_u=precio_unitario(getIdproducto());
        try
        {
            PreparedStatement parametro;
            String  insertar = "insert into compras_detalle(idcompra,idproducto,cantidad,precio_costo_unitario) VALUES (?,?,?,?)";
            cn=new Conexion();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(insertar);
            parametro.setInt(1, getIdcompra());
            parametro.setInt(2, getIdproducto());
            parametro.setInt(3, getCantidad());
            parametro.setDouble(4, getPrecio_costo_unitario());
            retorno=parametro.executeUpdate();
            modificar_costo_prod(getCantidad());
            cn.cerrar_conexion();
        }catch(Exception ex)
        {

        }
        return retorno;
    }
    
    public int modificar_costo_prod(int cantidad)
    {
        double precio_costo=getPrecio_costo_unitario();
        double precio_venta=precio_costo*1.25;
        int devolver=0;
        try
        {
            PreparedStatement parametro;
            String codigo_sql="update db_punto_venta.productos set precio_costo=?, precio_venta=?, existencia=existencia+? where idproducto=?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(codigo_sql);
            
            parametro.setDouble(1, getPrecio_costo_unitario());
            parametro.setDouble(2, precio_venta);
            parametro.setDouble(3, cantidad);
            parametro.setInt(4, getIdproducto());
            
            devolver=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(HeadlessException | SQLException ex)
        {
            System.out.println("error........"+ex.getMessage());
        }
        
        return devolver;
    }
    
    public DefaultTableModel leer()
    {
        DefaultTableModel tabla = new DefaultTableModel();
        try
        {
            cn = new Conexion();
            String query="select cd.idcompra_detalle as id, c.no_orden_compra, p.proveedor, prod.producto, cd.cantidad, cd.precio_costo_unitario, c.fecha_orden, c.fechaingreso, c.idproveedor, cd.idproducto, cd.idcompra from compras_detalle as cd INNER JOIN compras c on cd.idcompra=c.idcompra INNER JOIN proveedores p on c.idproveedor=p.idproveedor INNER JOIN productos prod on cd.idproducto=prod.idproducto;";
            cn.abrir_conexion();
            ResultSet consulta=cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[]={"id","no_orden","proveedor","producto","cantidad","precio_unitario","fecha_orden","fecha_ingreso","idproveedor","idproducto","idcompra"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[]= new String [12];
            while(consulta.next())
            {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("no_orden_compra");
                datos[2] = consulta.getString("proveedor");
                datos[3] = consulta.getString("producto");
                datos[4] = consulta.getString("cantidad");

                datos[5] = consulta.getString("precio_costo_unitario");
                datos[6] = consulta.getString("fecha_orden");
                datos[7] = consulta.getString("fechaingreso");
                datos[8] = consulta.getString("idproveedor");
                datos[9] = consulta.getString("idproducto");
                datos[10]=consulta.getString("idcompra");
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
/*
    public int modificando_cantidades()
    {
        int devolver=0;
        int antiguo_idprod, nuevo_idprod, validar_existencias, cantidad_de_registro, cantidad_a_reducir, validar, nueva_cantidad;
        antiguo_idprod=id_prod_de_registro();   //obteniendo el id del registro
        nuevo_idprod=getIdproducto();           //obteniendo el nuevo id
        validar_existencias=obtener_cantidad_de_registro(); //obteniendo la cantidad de existencias de la tabla productos
        cantidad_de_registro=cantidad_de_comprasdetalle();  //obteniendo la cantidad del registro compras_detalle
        cantidad_a_reducir=getCantidad();       //la cantidad que se va a reducir o aumentar
        validar=cantidad_de_registro-cantidad_a_reducir;    //resta para la validacion
        
       
        if(validar_existencias>validar || (cantidad_de_registro==cantidad_a_reducir && antiguo_idprod!=nuevo_idprod) || (cantidad_de_registro==cantidad_a_reducir && antiguo_idprod==nuevo_idprod)) //aqui está el error   if(validar_existencias>cantidad_de_ventas)    if(validar_existencias>cantidad_reducir || (cantidad_de_ventas==cantidad_reducir && antiguo_idprod!=nuevo_idprod))
        {
            if(antiguo_idprod==nuevo_idprod)
            {
                if(cantidad_de_registro>cantidad_a_reducir)
                {
                    nueva_cantidad=cantidad_de_registro-cantidad_a_reducir;
                    try
                    {
                        cn=new Conexion();
                        String consulta="update productos set existencia=existencia-? where idproducto=?";
                        cn.abrir_conexion();
                        PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
                        parametro.setInt(1, nueva_cantidad);
                        parametro.setInt(2, nuevo_idprod);

                        devolver=parametro.executeUpdate();
                        cn.cerrar_conexion();
                    }catch(SQLException ex)
                    {

                    }
                }
                else if(cantidad_de_registro<cantidad_a_reducir)
                {
                    nueva_cantidad=cantidad_a_reducir - cantidad_de_registro;
                    try
                    {
                        cn=new Conexion();
                        String consulta="update productos set existencia=existencia+? where idproducto=?";
                        cn.abrir_conexion();
                        PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
                        parametro.setInt(1, nueva_cantidad);
                        parametro.setInt(2, getIdproducto());

                        devolver=parametro.executeUpdate();
                        cn.cerrar_conexion();
                    }catch(SQLException ex)
                    {

                    }

                }
                else{return 1;}
            }
            else
            {
                int nuevo_prod=obtener_cantidad_de_prueba(); //cantidad de la tabla productos
                try
                {
                    if(nuevo_prod>cantidad_de_registro) //cantidad de compras detalle
                    {
                        
                        modificacion_antiguoidprod(cantidad_de_registro); //cantidad de compras_Detalle
                        
                    
                        cn=new Conexion();
                        String consulta="update productos set existencia=existencia+? where idproducto=?";
                        cn.abrir_conexion();
                        PreparedStatement parametro= (PreparedStatement) cn.conexionBD.prepareStatement(consulta);
                        parametro.setInt(1, cantidad_a_reducir);
                        parametro.setInt(2, nuevo_idprod);
                        devolver=parametro.executeUpdate();
                    }
                    
                    cn.cerrar_conexion();
                }catch(SQLException ex)
                {

                }
            }
            
            
        }
        
        return devolver;
    }
*/