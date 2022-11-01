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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author joseg
 */
public class Empleado extends Persona {

    private String dpi, Fecha_inicio, fecha_ingreso;
    private int id_empleado, genero, id_puesto;

    private Conexion cn;

    public Empleado() {
    }

    public Empleado(int id, String nombres, String apellidos, String direccion, String telefono, String dpi, int genero, String fecha_nacimiento, int id_puesto, String Fecha_inicio, String fecha_ingreso) {
        super(id, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.dpi = dpi;
        this.Fecha_inicio = Fecha_inicio;
        this.fecha_ingreso = fecha_ingreso;
        this.genero = genero;
        this.id_puesto = id_puesto;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getFecha_inicio() {
        return Fecha_inicio;
    }

    public void setFecha_inicio(String Fecha_inicio) {
        this.Fecha_inicio = Fecha_inicio;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }
    
    
    public HashMap mostrar_empleado()
    {
        HashMap<String, String> drop_empleado= new HashMap();
        try
        {
            String codigo_sql="select idempleado as id, nombres from empleados;";
            cn=new Conexion();
            cn.abrir_conexion();
            ResultSet consulta=cn.conexionBD.createStatement().executeQuery(codigo_sql);
            while(consulta.next())
            {
                
                drop_empleado.put(consulta.getString("id"), consulta.getString("nombres"));
            }
            
            cn.cerrar_conexion();
        }catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return drop_empleado;
    }
    
    @Override
    public int agregar() {
        int retorno;

        {
            try {

                String fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
                PreparedStatement parametro;
                String query = "insert into empleados(Nombres,Apellidos,Direccion,Telefono,DPI,Genero,Fecha_nacimiento,idPuesto,Fecha_inicio_labores,Fechaingreso)values(?,?,?,?,?,?,?,?,?,?);";
                cn = new Conexion();
                cn.abrir_conexion();
                parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
                parametro.setString(1, this.getNombres());

                parametro.setString(2, this.getApellidos());
                parametro.setString(3, this.getDireccion());
                parametro.setString(4, this.getTelefono());
                parametro.setString(5, this.getDpi());
                parametro.setInt(6, this.getGenero());
                parametro.setString(7, this.getFecha_nacimiento());
                parametro.setInt(8, this.getId_puesto());
                parametro.setString(9, this.getFecha_inicio());
                parametro.setString(10, fecha);

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
            String query = "select idEmpleado as id, m.Nombres, m.Apellidos, m.Direccion, m.Telefono,m.DPI,m.Genero,m.Fecha_nacimiento,m.Fecha_inicio_labores,m.Fechaingreso, P.Puesto, P.Idpuesto from empleados as m, puestos as P where m.idPuesto=P.Idpuesto;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"Id", "Nombres", "Apellidos", "Direccion", "Telefono", "Dpi", "Genero", "Nacimiento", "Fecha Contrato", "Fecha Ingreso", "Puesto", "id_puesto"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[13];
            while (consulta.next()) {

                if (consulta.getString("Genero").equals("1")) {
                    elegir = uno[0];
                } else {
                    elegir = uno[1];
                }

                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("Nombres");
                datos[2] = consulta.getString("Apellidos");
                datos[3] = consulta.getString("Direccion");
                datos[4] = consulta.getString("Telefono");
                datos[5] = consulta.getString("DPI");
                datos[6] = elegir;//consulta.getString("Genero");//
                datos[7] = consulta.getString("Fecha_nacimiento");
                datos[8] = consulta.getString("Fecha_inicio_labores");
                datos[9] = consulta.getString("Fechaingreso");
                datos[10] = consulta.getString("Puesto");
                datos[11] = consulta.getString("Idpuesto");

                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return tabla;
    }

    public HashMap seleccionar2(){
        HashMap <String, String> drop = new HashMap();
    try{
        cn = new Conexion();
        String query=("select idEmpleado as id, m.Nombres, m.Apellidos  from empleados as m, puestos as P where m.idPuesto=P.Idpuesto;");
        cn.abrir_conexion();
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        while(consulta.next()){
            drop.put(consulta.getString("id"), consulta.getString("Nombres")+" "+consulta.getString("Apellidos") );
        }
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
        return drop;
    }
    
    public HashMap seleccionar(){
        HashMap <String, String> drop = new HashMap();
    try{
        cn = new Conexion();
        String query=("select idEmpleado as id, m.Nombres, m.Apellidos,P.Puesto from empleados as m, puestos as P where m.idPuesto=P.Idpuesto;");
        cn.abrir_conexion();
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        while(consulta.next()){
            drop.put(consulta.getString("id"), consulta.getString("Nombres")+" "+consulta.getString("Apellidos")+", "+consulta.getString("Puesto"));
        }
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
        return drop;
    }
    
    @Override
    public int modificar() {
        int retorno;

        {
            try {
                PreparedStatement parametro;
                String query = "update empleados set Nombres=?, Apellidos=?,Direccion=?,Telefono=?,DPI=?,Genero=?, Fecha_nacimiento=?,idPuesto=?, Fecha_inicio_labores=? where idEmpleado=?;";
                cn = new Conexion();
                cn.abrir_conexion();
                parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
                parametro.setString(1, this.getNombres());
                parametro.setString(2, this.getApellidos());
                parametro.setString(3, this.getDireccion());
                parametro.setString(4, this.getTelefono());
                parametro.setString(5, this.getDpi());
                parametro.setInt(6, this.getGenero());
                parametro.setString(7, this.getFecha_nacimiento());
                parametro.setInt(8, this.getId_puesto());
                parametro.setString(9, this.getFecha_inicio());
                parametro.setInt(10, this.getId());

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
                String query = "delete from empleados where idEmpleado=?;";
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
