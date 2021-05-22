/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Address;
import service.AddressService;

/**
 *
 * @author Win10
 */
@WebServlet(name = "AddressController", urlPatterns = {"/AddressController"})
public class AddressController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("task").equals("getAddressById")) {
                if (!request.getParameter("id").isEmpty()){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    Address result = AddressService.getAddressById(id);
                    out.print(result.serialize());
                }
            }
            if (request.getParameter("task").equals("listAddresses")) {
                List<Address> result = AddressService.listAddresses();
                out.print(serialize(result));
            }
            if (request.getParameter("task").equals("createAddress")) {
                if (request.getParameter("zipCode") != null && request.getParameter("city") != null && request.getParameter("address") != null){
                    String zipCode = request.getParameter("zipCode");
                    String city = request.getParameter("city");
                    String address = request.getParameter("address");
                    String addressComplement = request.getParameter("addressComplement");
                    Boolean result = AddressService.createAddress(zipCode, city, address, addressComplement);
                    out.print(result);
                }
            }
            if (request.getParameter("task").equals("updateAddress")) {
                if (request.getParameter("id") != null ){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    String zipCode = request.getParameter("zipCode");
                    String city = request.getParameter("city");
                    String address = request.getParameter("address");
                    String addressComplement = request.getParameter("addressComplement");
                    Boolean result = AddressService.updateAddress(id, zipCode, city, address, addressComplement);
                    out.print(result);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public String serialize(Object object){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(object);
            return json;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
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
