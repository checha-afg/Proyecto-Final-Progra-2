package Controlador;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.logging.Level;
import java.util.logging.Logger;
import Modelo.Empleado;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 
 * @author Luis Fernando Paxel
 */
public class src_empleado extends HttpServlet {

    Empleado empleado;
    private String uno ="2021-04-15 19:30:31";
   private int genero;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet src_empleado</title>");            
           
            out.println("</head>");
            out.println("<body>");
           if("Masculino".equals(request.getParameter("gen"))){
           //   out.println("<h1>A seleccionado Hombre </h1>");
              genero=1;
             
           }else{
           //   out.println("<h1>A seleccionado Mujer </h1>"); 
               genero=0;
           } 
            
         empleado = new Empleado(Integer.valueOf(request.getParameter("txt_id")),request.getParameter("txt_nombres"),request.getParameter("txt_apellidos"),request.getParameter("txt_direccion"),request.getParameter("txt_telefono"),request.getParameter("txt_dpi"),genero,request.getParameter("txt_fn"),Integer.valueOf(request.getParameter("drop_puestos")),request.getParameter("txt_fni"),"");
           
            if("agregar".equals(request.getParameter("btn_agregar"))){
                
            if (empleado.agregar() > 0) {
           Thread.sleep(2000);
           response.sendRedirect("index_empleado.jsp");
            
            } else {
           Thread.sleep(2000);
           response.sendRedirect("index_empleado.jsp");
              }
            }
                if("modificar".equals(request.getParameter("btn_modificar"))){
                
            if (empleado.modificar() > 0) {
           Thread.sleep(2000);
           response.sendRedirect("index_empleado.jsp");
            } else {
                out.println("<h1>Error carnal</h1>");
                out.println("<a href ='index_empleado.jsp '> Regresar </a>");
            }
            }
        if("eliminar".equals(request.getParameter("btn_eliminar"))){
                
            if (empleado.eliminar() > 0) {
           Thread.sleep(2000);
           response.sendRedirect("index_empleado.jsp");
            } else {
                out.println("<h1>Error carnal</h1>");
                out.println("<a href ='index_empleado.jsp '> Regresar </a>");
            }
            }

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
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(src_empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(src_empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
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