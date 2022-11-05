package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import Modelo.Cliente;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Luis Fernando Paxel
 */
public class src_cliente extends HttpServlet {

    Cliente cliente;
    private int genero;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            //    out.println("<head>");
            //  out.println("<title>Cliente</title>");
            out.println("</head>");
            out.println("<body>");

            if ("Masculino".equals(request.getParameter("gen"))) {
                genero = 1;
            } else {
                genero = 0;
            }
            cliente = new Cliente(Integer.valueOf(request.getParameter("txt_id")), request.getParameter("txt_nombre"), request.getParameter("txt_apellido"), request.getParameter("txt_nit"), genero, request.getParameter("txt_telefono"), request.getParameter("txt_correo"), "", "", "");

            if ("agregar".equals(request.getParameter("btn_agregar"))) {

                if (cliente.agregar() > 0) {
                    Thread.sleep(2000);
                    response.sendRedirect("index_cliente.jsp");
                } else {
                    Thread.sleep(2000);
                    response.sendRedirect("index_cliente.jsp");
                }
            }
            if ("modificar".equals(request.getParameter("btn_modificar"))) {

                if (cliente.modificar() > 0) {
                    Thread.sleep(2000);
                    response.sendRedirect("index_cliente.jsp");

                } else {
                    Thread.sleep(2000);
                    response.sendRedirect("index_cliente.jsp");
                }
            }

            if ("eliminar".equals(request.getParameter("btn_eliminar"))) {

                if (cliente.eliminar() > 0) {
                out.println("<h1>Registro eliminado Correctamente :vv</h1>");
                out.println("<a href ='index_cliente.jsp '> Regresar </a>");
                } else {
                out.println("<h1>Registro  No eliminado Correctamente :vv</h1>");
                out.println("<a href ='index_cliente.jsp '> Regresar </a>");
        
                }
            }

            out.println("</body>");
            out.println("</html>");
        }
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(src_cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(src_cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
