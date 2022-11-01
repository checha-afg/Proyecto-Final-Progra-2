/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;

import Modelo.Ventas_Detalle;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 *
 * @author joseg
 */
public class controlador_ventas2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Ventas_Detalle ventas_detalle;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlador_ventas2</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet controlador_ventas2 at " + request.getContextPath() + "</h1>");
            ventas_detalle = new Ventas_Detalle(Integer.valueOf(request.getParameter("txt_id_venta")), Integer.valueOf(request.getParameter("drop_producto2")), Integer.valueOf(request.getParameter("txt_cantidad2")), 0);
            if("Agregar_nuevo_prod".equals(request.getParameter("btn_agregar_nuevo_prod")))
            { 
                
                if(ventas_detalle.reducir_cantidad()>0)
                    {
                            ventas_detalle.agregar();
                            response.sendRedirect("Maestro_ventas.jsp");
                    }
                    else
                    {
                        String imagen ="https://c.tenor.com/2CoAwNOjrDYAAAAC/aqua-konosuba.gif";
                        out.println("<p>Error, ya no queda el suficiente producto para hacer esta venta</p>");
                        out.println("<img src="+imagen);
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
