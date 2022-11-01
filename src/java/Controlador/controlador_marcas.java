/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
/*import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/
import Modelo.Marcas;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 *
 * @author joseg
 */
public class controlador_marcas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Marcas marca;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlador_marcas</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet controlador_marcas at " + request.getContextPath() + "</h1>");
            marca = new Marcas(request.getParameter("txt_marca"),Integer.valueOf(request.getParameter("txt_id_marca")));
            if("agregar_marca".equals(request.getParameter("btn_agregar_marcas")))
            {
                if(marca.agregar()>0)
                {
                    response.sendRedirect("index.jsp");
                }
                else
                {
                    String imagen="https://i2.wp.com/www.memexicoaldia.com/wp-content/uploads/2020/07/107100006_106717961114074_2333680951068078018_n.jpg?resize=640%2C462&ssl=1";
                    out.println("<br>");
                    out.println("<img src="+imagen+">");
                    out.println("<h3>Ha ocurrido un error :c</h3>");
                }
            }
            else if("modificar_marca".equals(request.getParameter("btn_modificar_marcas")))
            {
                if(marca.modificar()>0)
                {
                    response.sendRedirect("index.jsp");
                }
                else
                {
                    String imagen="https://i2.wp.com/www.memexicoaldia.com/wp-content/uploads/2020/07/107100006_106717961114074_2333680951068078018_n.jpg?resize=640%2C462&ssl=1";
                    out.println("<br>");
                    out.println("<img src="+imagen+">");
                    out.println("<h3>Ha ocurrido un error :c</h3>");
                }
            }
            else if("eliminar_marca".equals(request.getParameter("btn_eliminar_marcas")))
            {
                if(marca.eliminar()>0)
                {
                    response.sendRedirect("index.jsp");
                }
                else
                {
                    String imagen="https://i2.wp.com/www.memexicoaldia.com/wp-content/uploads/2020/07/107100006_106717961114074_2333680951068078018_n.jpg?resize=640%2C462&ssl=1";
                    out.println("<br>");
                    out.println("<img src="+imagen+">");
                    out.println("<h3>Ha ocurrido un error :c</h3>");
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
