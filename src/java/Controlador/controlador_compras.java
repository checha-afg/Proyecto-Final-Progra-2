/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Compras;
import Modelo.Compras_detalle;
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
public class controlador_compras extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Compras_detalle compras_d;
    Compras compra;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlador_compras</title>");            
            out.println("</head>");
            out.println("<body>");
            compras_d=new Compras_detalle(Integer.parseInt(request.getParameter("txt_id_compra_detalle")), Integer.parseInt(request.getParameter("txt_compra")), Integer.parseInt(request.getParameter("drop_producto")), Integer.parseInt(request.getParameter("txt_cantidad")), Double.parseDouble(request.getParameter("txt_precio_unitario")));
            compra=new Compras(Integer.parseInt(request.getParameter("txt_compra")), Integer.parseInt(request.getParameter("txt_orden_compra")), Integer.parseInt(request.getParameter("drop_proveedor")), request.getParameter("txt_fecha_orden"));
            
            if("agregar_compra".equals(request.getParameter("btn_agregar_compra")))
            {
                if(compra.agregar()>0)
                {
                    compras_d.agregar();
                    response.sendRedirect("Maestro_compras.jsp");
                }
                else
                {
                    //String imagen ="https://i.blogs.es/4329e4/error500/450_1000.png";
                    out.println("<p>Error al ingresar la compra</p>");
                    //response.sendRedirect("Tablas\\producto.jsp");
                    //out.println("<img src="+imagen);
                }
                
            }
            if("modificar_compra".equals(request.getParameter("btn_modificar_compra")))
            {
                if(compras_d.modificando_cantidades()>0)
                {

                    if(compras_d.modificar()>0)
                    {
                        compra.modificar();
                        response.sendRedirect("Maestro_compras.jsp");
                    }else
                    {
                        out.print("<p>ERROR AL MODIFICAR COMPRA_DETALLE </p>");
                    }
                }
                else
                {
                    String imagen ="https://c.tenor.com/2CoAwNOjrDYAAAAC/aqua-konosuba.gif";
                        out.println("<p>Error, hubo un error al modificar la cantidad</p>");
                        out.println("<img src="+imagen);
                }
            }
            if("eliminar_compra".equals(request.getParameter("btn_eliminar_compra")))
            {
                if(compras_d.modificacion_eliminar()>0)
                {

                    if(compras_d.eliminar()>0)
                    {
                        compra.eliminar();
                        response.sendRedirect("Maestro_compras.jsp");
                    }else
                    {
                        out.print("<p>ERROR AL ELIMINAR COMPRA_DETALLE </p>");
                    }
                }
                else
                {
                    String imagen ="https://c.tenor.com/2CoAwNOjrDYAAAAC/aqua-konosuba.gif";
                        out.println("<p>Error, hubo un error al modificar la cantidad</p>");
                        out.println("<img src="+imagen);
                }
            }
            //out.println("<h1>Servlet controlador_compras at " + request.getContextPath() + "</h1>");
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
