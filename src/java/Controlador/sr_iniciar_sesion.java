/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Metodos_Login;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author chech
 */


public class sr_iniciar_sesion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession sesion = request.getSession();
            Metodos_Login metodos = new Metodos_Login();
            String txtUsuario = request.getParameter("txtUsuario");
            String txtContrasena = request.getParameter("txtContrasena");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Procesando</title>");
            out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script type=\"text/javascript\">"); 
            
            boolean iniciarSesion = metodos.buscarUsuarioInicioSesion(txtUsuario, txtContrasena);
            if(iniciarSesion == true){
                out.println("alert('Bienvenido, ha ingresado con el usuario: "+ txtUsuario +"')");
                out.println("location = 'Bienvenido.jsp'");
                
                String nombre = metodos.buscarNombre(txtUsuario);
                sesion.setAttribute("nombre", nombre);
                sesion.setAttribute("txtUsuario", txtUsuario);
            }else{
                out.println("alert('Usuario o contraseña inexistentes')");
                out.println("location = 'error.jsp'");
            }
            System.out.println("Valor inicio de sesion: "+ iniciarSesion);
            out.println("</script>");
            out.println("<script src='//cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
            out.println("<script src='js/sweetAlert.js'></script>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
