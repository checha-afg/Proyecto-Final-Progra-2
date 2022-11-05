/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Producto;
import Modelo.Producto2;
import Modelo.Ventas;
import Modelo.Ventas2;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author joseg
 */
public class ser_ventas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    int item, item2, cod;
    String descripcion;
    double precio, total;
    int cantidad;
    double subtotal;
    Ventas2 v = new Ventas2();
    List<Ventas2> lista = new ArrayList<>();
    int id_tabla, table;
    Producto br = new Producto();
    int busc, busc2;
    Ventas ventas;
    Producto2 bh = new Producto2();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ser_ventas</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet ser_ventas at " + request.getContextPath() + "</h1>");
            
            String accion = request.getParameter("accion");
            String menu = request.getParameter("menu");

            if (menu.equals("Venta")) {

                switch (accion) {

                    case "Agregar":
                        total = 0.0;
                        item = item + 1;
                        cod = Integer.valueOf(request.getParameter("codigoproducto"));
                        descripcion = request.getParameter("nombreproducto");
                        precio = Double.parseDouble(request.getParameter("precio"));
                        cantidad = Integer.parseInt(request.getParameter("cantidad"));
                        subtotal = precio * cantidad;
                        v = new Ventas2();

                        v.setIdproducto(cod);
                        v.setDescripcion(descripcion);
                        v.setPrecio(precio);
                        v.setCantidad(cantidad);
                        v.setSubtotal(subtotal);
                        v.setItem(item);
                        lista.add(v);
                        for (int i = 0; i < lista.size(); i++) {
                            total = total + lista.get(i).getSubtotal();

                        }

                        request.setAttribute("total", total);
                        request.setAttribute("lista", lista);
                        break;

                    case "Cancelar":
                        //       lista.remove(v); SIRVE PARA REMOVER EL ULTIMO DATO DE LA TABLA
                        lista.removeAll(lista);
                        item = 0;
                        break;

                    case "remover":

                        id_tabla = Integer.parseInt(request.getParameter("id22"));
                        int restar = id_tabla - 1;
                        item = item - 1;

                        lista.remove(restar);

                        v.setItem(item);

                        double total88 = 0.0;

                        for (int i = 0; i < lista.size(); i++) {
                            total88 = total88 + lista.get(i).getSubtotal();

                        }
                        request.setAttribute("total", total88);
                        request.setAttribute("lista", lista);
                        break;
                    case "editar":
                        double total_edit = 0.0;
                        busc = Integer.parseInt(request.getParameter("id"));
                        busc2 = Integer.parseInt(request.getParameter("id33"));
                        Producto2 hr = bh.Listaid2(busc);
                        request.setAttribute("proo", hr);
                        for (int ff = 0; ff < lista.size(); ff++) {
                            total_edit = total_edit + lista.get(ff).getSubtotal();
                        }

                        request.setAttribute("total", total_edit);
                        request.setAttribute("lista", lista);

                        break;

                    case "Modificar":
                        double total66 = 0.0;
                        item2 = busc2 - 1;
                        int cod2 = Integer.valueOf(request.getParameter("codigoproducto"));
                        String descripcion2 = request.getParameter("nombreproducto");
                        double precio2 = Double.parseDouble(request.getParameter("precio"));
                        int cantidad2 = Integer.parseInt(request.getParameter("cantidad"));
                        double subtotal2 = precio2 * cantidad2;
                        Ventas2 vv = new Ventas2();

                        vv.setIdproducto(cod2);
                        vv.setDescripcion(descripcion2);
                        vv.setPrecio(precio2);
                        vv.setCantidad(cantidad2);
                        vv.setSubtotal(subtotal2);
                        vv.setItem(busc2);
                        lista.set(item2, vv);
                        for (int ar = 0; ar < lista.size(); ar++) {
                            total66 = total66 + lista.get(ar).getSubtotal();
                        }
                        request.setAttribute("total", total66);
                        request.setAttribute("lista", lista);
                        break;

                    case "Agregar_venta":

                        for (int e = 0; e < lista.size(); e++) {
                            Producto pe = new Producto();
                            int cantidad44 = lista.get(e).getCantidad();
                            int idproducto44 = lista.get(e).getIdproducto();
                            pe = pe.Listaid(idproducto44);
                            int sac = pe.getExistencia() - cantidad44;

                            pe.modificar_existencia(idproducto44, sac);
                        }

                        ventas = new Ventas(0, Integer.valueOf(request.getParameter("drop_fact")), request.getParameter("txt_serie"), request.getParameter("txt_fecha"), Integer.valueOf(request.getParameter("drop_cliente")), Integer.valueOf(request.getParameter("drop_empleado")), " ");
                        ventas.agregar();
                        int idv = Integer.parseInt(ventas.Idventas());

                        for (int i = 0; i < lista.size(); i++) {
                            v = new Ventas2();
                            br = new Producto();
                            v.setId(idv);
                            v.setIdproducto(lista.get(i).getIdproducto());
                            v.setCantidad(lista.get(i).getCantidad());
                            v.setPrecio(lista.get(i).getPrecio());
                            br.setExistencia(lista.get(i).getCantidad());
                            ventas.GuardarDetalle(v);

                        }
                        lista.removeAll(lista);
                        item = 0;
                        break;

                }
                request.getRequestDispatcher("Maestro_ventas.jsp").forward(request, response);
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
