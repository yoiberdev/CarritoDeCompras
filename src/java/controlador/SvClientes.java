/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import config.Fecha;
import modelo.Carrito;
import modelo.Cliente;
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.Producto;
import modeloDAO.ClienteDAO;
import modeloDAO.CompraDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class SvClientes extends HttpServlet {

    Cliente cliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();
    CompraDAO compraDAO = new CompraDAO();

    String logueo = " ";
    String correoVal = "";

    Fecha fecha = new Fecha();
    Compra compra = new Compra();
    DetalleCompra detallecompra = new DetalleCompra();

    List<Producto> listaproductos = new ArrayList<>();
    List<Carrito> listaCarrito = new ArrayList<>();
    double montoPagar;
    int idcompra;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvClientes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvClientes at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        sesion.setAttribute("logueo", logueo);
        sesion.setAttribute("correoVal", correoVal);
        if (request.getParameter("nuevoCliente") != null && request.getParameter("nuevoCliente").equals("Registrar")) {
            // Datos del cliente
            String nombres = request.getParameter("nombres");
            String dni = request.getParameter("dni");
            String direccion = request.getParameter("direccion");
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");

            // Dato de la compra
            double monto = Double.parseDouble(request.getParameter("monto"));

            cliente.setDni(dni);
            cliente.setNombre(nombres);
            cliente.setDireccion(direccion);
            cliente.setCorreo(correo);
            cliente.setPassword(password);
            clienteDAO.insertarCliente(cliente);
            logueo = cliente.getNombre();
            correoVal = cliente.getCorreo();
            response.sendRedirect("metodoPago.jsp");
        } else if (request.getParameter("login") != null && request.getParameter("login").equals("Validar")) {
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");
            // Realiza la validación del usuario en la base de datos
            // Supongamos que esto llena un objeto cliente si la validación es exitosa
            Cliente cliente = clienteDAO.Validar(correo, password);

            if (cliente != null) {
                logueo = cliente.getNombre();
                correoVal = cliente.getCorreo();;
                response.sendRedirect("metodoPago.jsp"); // Redirige al inicio de sesión
            } else {
                try (PrintWriter out = response.getWriter()) {
                    out.print("<label class=\"text-center\">Contraseña o correo incorrecto</label>");
                }
            }
        } else if (request.getParameter("accion2") != null) {
            listaCarrito = new ArrayList();
            cliente = new Cliente();
            sesion.invalidate();
            logueo = "Iniciar Sesion";
            correoVal = "Iniciar Sesion";
            response.sendRedirect("carrito.jsp");
        } else {
            sesion.setAttribute("logueo", logueo);
            sesion.setAttribute("correoVal", correoVal);
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
