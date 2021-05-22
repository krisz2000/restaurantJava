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
import model.Restaurant;
import service.RestaurantService;

/**
 *
 * @author Win10
 */
@WebServlet(name = "RestaurantController", urlPatterns = {"/RestaurantController"})
public class RestaurantController extends HttpServlet {

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
            if (request.getParameter("task").equals("getRestaurantById")) {
                if (request.getParameter("id") != null){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    Restaurant result = RestaurantService.getRestaurantById(id);
                    out.print(result.serialize());
                }
            }
            if (request.getParameter("task").equals("getRestaurantByName")) {
                if (request.getParameter("name") != null){
                    String name = request.getParameter("name");
                    Restaurant result = RestaurantService.getRestaurantByName(name);
                    out.print(result.serialize());
                }
            }
            if (request.getParameter("task").equals("listRestaurants")) {
                List<Restaurant> result = RestaurantService.listRestaurants();
                out.print(serialize(result));
                
            }
            if (request.getParameter("task").equals("createRestaurant")) {
                if (request.getParameter("name") != null && request.getParameter("addressId") != null){
                    String name = request.getParameter("name");
                    Integer addressId = Integer.parseInt(request.getParameter("addressId"));
                    Boolean result = RestaurantService.createRestaurant(name, addressId);
                    out.print(result);
                }
            }
            if (request.getParameter("task").equals("removeRestaurant")) {
                if (request.getParameter("id") != null){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    Boolean result = RestaurantService.removeRestaurant(id);
                    out.print(result);
                }
            }

            if (request.getParameter("task").equals("updateRestaurant")) {
                if(request.getParameter("id") != null){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    String name = request.getParameter("name");
                    Integer addressId = null;
                    if(request.getParameter("addressId") != null){
                        addressId = Integer.parseInt(request.getParameter("addressId"));
                    }
                    Boolean result = RestaurantService.updateRestaurant(id, name, addressId);
                    out.print(result);
                }else{
                    out.print(false);
                }
                
                
                
                
            }
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
