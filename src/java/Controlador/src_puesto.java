/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Puesto;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author joseg
 */
public class src_puesto extends HttpServlet {

    Puesto puesto;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet src_puesto</title>");
            out.println("</head>");
            out.println("<body>");
            puesto = new Puesto(Integer.valueOf(request.getParameter("txt_id")), request.getParameter("txt_puesto"));
          
            if ("agregar".equals(request.getParameter("btn_agregar"))) {

                if (puesto.agregar() > 0) {
                   Thread.sleep(1000);
                    response.sendRedirect("index_puesto.jsp");
                 } else {
                    Thread.sleep(1000);
                    response.sendRedirect("index_puesto.jsp");
                }
            }
            
            if ("modificar".equals(request.getParameter("btn_modificar"))) {

                if (puesto.modificar() > 0) {
                Thread.sleep(2000);
                    response.sendRedirect("index_puesto.jsp");
                    } else {
                Thread.sleep(2000);
                    response.sendRedirect("index_puesto.jsp");
                    }
            }
            if ("eliminar".equals(request.getParameter("btn_eliminar"))) {

                if (puesto.eliminar() > 0) {
                Thread.sleep(2000);
                  out.println("<h1>Exito</h1>");
          
                    response.sendRedirect("index_puesto.jsp");
                    
                    } else {
                Thread.sleep(2000);
                    response.sendRedirect("index_puesto.jsp");
                      out.println("<h1>Error</h1>");
          
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
            Logger.getLogger(src_puesto.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(src_puesto.class.getName()).log(Level.SEVERE, null, ex);
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