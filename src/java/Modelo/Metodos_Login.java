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


public class Metodos_Login {
    private Connection conexion;
    private PreparedStatement parametro;
    private ResultSet resultado;
    
    public boolean registrarUsuario(String idusuario, String nombre, String apellidos, String contrasena, String usuarioGeneradoAutomaticamente){
        boolean registro = false;
        try {
            conexion = Conexion_login.abrir_conexionLog();
            String query = "INSERT INTO usuarios (idusuario,nombre,apellidos,contrasena,usuario_generado_automaticamente) VALUES (?,?,?,?,?);";
            parametro =  conexion.prepareStatement(query);
            parametro.setString(1, idusuario);
            parametro.setString(2, nombre);
            parametro.setString(3, apellidos);
            parametro.setString(4, contrasena);
            parametro.setString(5, usuarioGeneradoAutomaticamente);
            int ejecutar = parametro.executeUpdate();
            if(ejecutar >0){
                registro = true;
                System.out.println("Inserccion Exitosa");
            }else{
                registro = false;
                System.out.println("Inserccion No Exitosa");
            }
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error..."+ex.getMessage());
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error..."+ex.getMessage());
            }
        }
        System.out.println("Valor del registro: "+registro);
        return registro;
    }
    
    public boolean buscarUsuarioRepetidoBD(String curp){
        boolean usuarioRepetido = false;
        try {
            conexion = Conexion_login.abrir_conexionLog();
            String query = "SELECT idusuario FROM usuarios WHERE idusuario = ?";
            parametro = conexion.prepareStatement(query);
            parametro.setString(1, curp);
            resultado = parametro.executeQuery();
            if(resultado.next()){
                usuarioRepetido = true; //ya existe
            }else{
                usuarioRepetido =  false; //no existe
        }
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error..."+ex.getMessage());
        }finally{
            try{
                conexion.close();
            }catch (SQLException ex) {
                System.out.println("Error..."+ex.getMessage());
            }
        }
        System.out.println("Usuario repetido: "+usuarioRepetido);
        return usuarioRepetido;
    }
    
    public boolean buscarUsuarioInicioSesion(String usuario, String contrasena){
        boolean iniciarSesion = false;
        try {
            conexion = Conexion_login.abrir_conexionLog();
            String query = "SELECT usuario_generado_automaticamente, contrasena FROM usuarios WHERE usuario_generado_automaticamente = ? AND contrasena = ?";
            parametro = conexion.prepareStatement(query);
            parametro.setString(1, usuario);
            parametro.setString(2, contrasena);
            resultado = parametro.executeQuery();
            if(resultado.next()){
                iniciarSesion = true; //Acceso confirmado
            }else{
                iniciarSesion = false; //Acceso denegado
            }
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error..."+ex.getMessage());
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error..."+ex.getMessage());
            }
        }
        return iniciarSesion;
    }
    
    public String buscarNombre(String usuario){
        String nombre = null;
        try {
            conexion = Conexion_login.abrir_conexionLog();
            String query = "SELECT nombre FROM usuarios WHERE usuario_generado_automaticamente = ?";
            parametro = conexion.prepareStatement(query);
            parametro.setString(1, usuario);
            resultado = parametro.executeQuery();
            if(resultado.next()){
                nombre = resultado.getString("nombre");
            }else{
                nombre = null;
            }
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error..."+ex.getMessage());
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error..."+ex.getMessage());
            }
        }
        return  nombre;
    }
}