
package controlador;

import config.Fecha;
import modelo.Carrito;
import modelo.Cliente;
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.Pago;
import modeloDAO.ProductoDAO;
import modelo.Producto;
import modeloDAO.ClienteDAO;
import modeloDAO.CompraDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
public class Controlador extends HttpServlet {

    ProductoDAO pdao = new ProductoDAO();
    CompraDAO compraDAO = new CompraDAO();
    ClienteDAO clienteDAO = new ClienteDAO();

    Producto p = new Producto();
    Cliente cliente = new Cliente();
    Fecha fecha = new Fecha();
    Compra compra = new Compra();
    DetalleCompra detallecompra = new DetalleCompra();

    List<Producto> listaproductos = new ArrayList<>();
    List<Carrito> listaCarrito = new ArrayList<>();
    int item = 0;
    double subtotal = 0.0;
    double totalPagar = 0.0;
    int cantidad = 1;
    int idp;

    Carrito car;
    double carritoSt;

    double montoPagar;
    int idcompra;
    int idpago;
    double montopagar;
    int idProducto = 0;
    Fecha fechaSistem = new Fecha();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String accion = request.getParameter("accion");
        listaproductos = pdao.listar();
        switch (accion) {
            case "Comprar":
                totalPagar = 0.0;
                idp = Integer.parseInt(request.getParameter("id"));
                p = pdao.listarId(idp);
                item = item + 1;
                car = new Carrito();
                car.setItem(item);
                car.setIdProducto(p.getId());
                car.setNombres(p.getNombres());
                car.setDescripcion(p.getDescripcion());
                car.setPrecioCompra(p.getPrecio());
                car.setCantidad(cantidad);
                car.setSubTotal(cantidad * p.getPrecio());
                listaCarrito.add(car);
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("carrito", listaCarrito);
                request.setAttribute("contador", listaCarrito.size());
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            case "AgregarCarrito":
                int pos = 0;
                cantidad = 1;
                int idp = Integer.parseInt(request.getParameter("id"));
                p = pdao.listarId(idp);
                if (listaCarrito.size() > 0) {
                    for (int i = 0; i < listaCarrito.size(); i++) {
                        if (idp == listaCarrito.get(i).getIdProducto()) {
                            pos = i;
                        }
                    }
                    if (idp == listaCarrito.get(pos).getIdProducto()) {
                        cantidad = listaCarrito.get(pos).getCantidad() + cantidad;
                        double subtotal = listaCarrito.get(pos).getPrecioCompra() * cantidad;
                        listaCarrito.get(pos).setCantidad(cantidad);
                        listaCarrito.get(pos).setSubTotal(subtotal);
                    } else {
                        item = item + 1;
                        car = new Carrito();
                        car.setItem(item);
                        car.setIdProducto(p.getId());
                        car.setNombres(p.getNombres());
                        car.setDescripcion(p.getDescripcion());
                        car.setPrecioCompra(p.getPrecio());
                        car.setCantidad(cantidad);
                        car.setSubTotal(cantidad * p.getPrecio());
                        listaCarrito.add(car);
                    }
                } else {
                    item = item + 1;
                    car = new Carrito();
                    car.setItem(item);
                    car.setIdProducto(p.getId());
                    car.setNombres(p.getNombres());
                    car.setDescripcion(p.getDescripcion());
                    car.setPrecioCompra(p.getPrecio());
                    car.setCantidad(cantidad);
                    car.setSubTotal(cantidad * p.getPrecio());
                    listaCarrito.add(car);
                }
                response.setContentType("text/html; charset=iso-8859-1");
                PrintWriter out = response.getWriter();
                out.print(listaCarrito.size());

                break;
            case "Delete":
                int idproducto = Integer.parseInt(request.getParameter("idp"));
                for (int i = 0; i < listaCarrito.size(); i++) {
                    if (listaCarrito.get(i).getIdProducto() == idproducto) {
                        listaCarrito.remove(i);
                    }
                }
                break;
            case "ActualizarCantidad":
                int idpro = Integer.parseInt(request.getParameter("idp"));
                int cant = Integer.parseInt(request.getParameter("Cantidad"));
                for (int i = 0; i < listaCarrito.size(); i++) {
                    if (listaCarrito.get(i).getIdProducto() == idpro) {
                        listaCarrito.get(i).setCantidad(cant);
                        carritoSt = listaCarrito.get(i).getPrecioCompra() * cant;
                        listaCarrito.get(i).setSubTotal(carritoSt);
                    }
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                totalPagar = 0.0;
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar += listaCarrito.get(i).getSubTotal();
                }
                try {
                    JSONObject json = new JSONObject();
                    json.put("cantidad", String.valueOf(cant));
                    json.put("carritosub", String.valueOf(carritoSt));
                    json.put("totalPagar", String.valueOf(totalPagar));
                    response.getWriter().write(json.toString());
                    carritoSt = 0.0;
                } catch (JSONException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Carrito":
                totalPagar = 0.0;
                request.setAttribute("carrito", listaCarrito);
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            case "GenerarCompra":

                compraDAO.Pagar(totalPagar);
                System.out.println("Total: " + totalPagar);

                int idcli = clienteDAO.IdCliente();
                int idpago = compraDAO.IdPago();
                String fechaCompras = fecha.FechaBD();
                String estado = "Cancelado - En Proceso de Envio";
                compra.setIdcliente(idcli);
                compra.setIdpago(idpago);
                compra.setFecha(fechaCompras);
                compra.setMonto(totalPagar);
                compra.setEstado(estado);
                compraDAO.guardarCompra(compra);

                idcompra = compraDAO.IdCompra();
                for (int i = 0; i < listaCarrito.size(); i++) {
                    detallecompra.setIdcompra(idcompra);
                    detallecompra.setIdproducto(listaCarrito.get(i).getIdProducto());
                    detallecompra.setCantidad(listaCarrito.get(i).getCantidad());
                    detallecompra.setPrecioCompra(listaCarrito.get(i).getPrecioCompra());
                    compraDAO.guardarDetalleCompra(detallecompra);
                }
                listaCarrito = new ArrayList<>();
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            case "MisCompras":
                response.sendRedirect("compras.jsp");
                break;
            
            default:
                request.setAttribute("productos", listaproductos);
                request.setAttribute("contador", listaCarrito.size());
                request.getRequestDispatcher("index.jsp").forward(request, response);
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
