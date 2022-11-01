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
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
 * @author joseg
 */
public class sr_guardar_login extends HttpServlet {

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
        PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            
            Metodos_Login metodos = new Metodos_Login();
            String txtCurp = request.getParameter("txtCurp");
            String txtNombre = request.getParameter("txtNombre");
            String txtApellidos = request.getParameter("txtApellidos");
            String txtContrasena = request.getParameter("txtContrasena");
            String txtUsuarioGeneradoAutomaticamente = request.getParameter("txtUsuarioGeneradoAutomaticamente");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>"); 
            out.println("<body>");
            out.println("<script type=\"text/javascript\">");
            
            boolean usuarioRepetido = metodos.buscarUsuarioRepetidoBD(txtCurp);
            if(usuarioRepetido == true){
                //out.println("alert('!El usuario ya ha sido creado anteriormente¡')");
                out.println("location = 'error.jsp'");
            }else{
                boolean registro = metodos.registrarUsuario(txtCurp, txtNombre, txtApellidos, txtContrasena, txtUsuarioGeneradoAutomaticamente);
                if(registro == true){
                    //out.println("alert('El usuario se ha registrado con éxito')");
                    out.println("location='usuariocreado.jsp'");
                 }else{
                    //out.println("alert('ERROR no se ha podido registrar el usuario')");
                    out.println("location = 'error.jsp'");
                }
            }
            out.println("</script>"); 
            out.println("</body>");
            out.println("</html>");
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
