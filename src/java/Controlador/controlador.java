package Controlador;

import Modelo.Marcas;
import Modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joseg
 */
public class controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Producto producto;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlador</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet controlador at " + request.getContextPath() + "</h1>");
            
            //FORMULARIO PRODUCTO
            producto= new Producto(request.getParameter("txt_producto"),request.getParameter("txt_descripcion"),request.getParameter("txt_imagen"),request.getParameter("txt_fecha_ingreso"),Integer.parseInt(request.getParameter("drop_marcas")),Integer.parseInt(request.getParameter("txt_existencia")),Double.parseDouble(request.getParameter("txt_costo")),Double.parseDouble(request.getParameter("txt_venta")),0);
            if("agregar_producto".equals(request.getParameter("btn_agregar_productos")))
            {
                if (producto.agregar()>0)
                {
                    response.sendRedirect("index.jsp");
                }
                else
                {
                    //String imagen ="https://i.blogs.es/4329e4/error500/450_1000.png";
                    out.println("<p>Error</p>");
                    //response.sendRedirect("Tablas\\producto.jsp");
                    //out.println("<img src="+imagen);
                }
            }
            //              MODIFICAR
            else if("modificar_producto".equals(request.getParameter("btn_modificar_productos")))
            {
                int id=Integer.parseInt(request.getParameter("txt_id_producto"));
                if (producto.modificar()>0)
                {
                    response.sendRedirect("index.jsp");
                }
                else
                {
                    //String imagen ="https://i.blogs.es/4329e4/error500/450_1000.png";
                    out.println("<p>Error</p>");
                    //response.sendRedirect("Tablas\\producto.jsp");
                    //out.println("<img src="+imagen);
                }
            }
            //              ELIMINAR
            else if("eliminar_producto".equals(request.getParameter("btn_eliminar_productos")))
            {
                int id=Integer.parseInt(request.getParameter("txt_id_producto"));
                if (producto.eliminar()>0)
                {
                    response.sendRedirect("index.jsp");
                }
                else
                {
                    //String imagen ="https://i.blogs.es/4329e4/error500/450_1000.png";
                    out.println("<p>Error</p>");
                    //response.sendRedirect("Tablas\\producto.jsp");
                    //out.println("<img src="+imagen);
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
