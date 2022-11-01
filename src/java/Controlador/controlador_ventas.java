/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;

import Modelo.Ventas_Detalle;
import Modelo.Ventas;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author joseg
 */
public class controlador_ventas extends HttpServlet {

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
    Ventas vendo;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlador_ventas</title>");            
            out.println("</head>");
            out.println("<body>");
            
            vendo = new Ventas(Integer.valueOf(request.getParameter("txt_id")), Integer.valueOf(request.getParameter("txt_factura")), request.getParameter("txt_serie"), request.getParameter("txt_ffact"), Integer.valueOf(request.getParameter("drop_cliente")), Integer.valueOf(request.getParameter("drop_empleado")), request.getParameter("txt_fni"));

            if ("modificar".equals(request.getParameter("btn_modificar"))) {

                if (vendo.modificar() > 0) {
                    Thread.sleep(2000);
                    response.sendRedirect("Maestro_ventas.jsp");

                } else {
                    Thread.sleep(2000);
                    response.sendRedirect("Maestro_ventas.jsp");
                }
            }
            
            if ("eliminar".equals(request.getParameter("btn_eliminar"))) {
                int Id_venta = Integer.valueOf(request.getParameter("txt_id"));
                vendo.eliminar_detalles(Id_venta);
                vendo.eliminar_venta(Id_venta);
                Thread.sleep(2000);
                response.sendRedirect("Maestro_ventas.jsp");
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
            Logger.getLogger(controlador_ventas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(controlador_ventas.class.getName()).log(Level.SEVERE, null, ex);
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
