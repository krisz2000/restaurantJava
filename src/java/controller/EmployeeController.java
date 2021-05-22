/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;
import service.EmployeeService;

/**
 *
 * @author Win10
 */
@WebServlet(name = "EmployeeController", urlPatterns = {"/EmployeeController"})
public class EmployeeController extends HttpServlet {

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
            if (request.getParameter("task").equals("getEmployeeById")) {
                if (request.getParameter("id") != null){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    Employee result = EmployeeService.getEmployeeById(id);
                    out.print(result.serialize());
                }
            }
            if (request.getParameter("task").equals("getEmployeeByFirstName")) {
                if (request.getParameter("firstName") != null){
                    String firstName = request.getParameter("firstName");
                    Employee result = EmployeeService.getEmployeeByFirstName(firstName);
                    out.print(result.serialize());
                }
            }
            if (request.getParameter("task").equals("listEmployees")) {
                List<Employee> result = EmployeeService.listEmployees();
                out.print(serialize(result));
                
            }
            if (request.getParameter("task").equals("createEmployee")) {
                if (request.getParameter("firstName") != null && request.getParameter("lastName") != null && request.getParameter("restaurantId") != null){
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    Date birthDate = null;
                    if(request.getParameter("birthDate") != null){
                        birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthDate"));  
                    }
                    Integer addressId = null;               
                    if(request.getParameter("addressId") != null){
                        addressId = Integer.parseInt(request.getParameter("addressId"));
                    }
                    Integer bossId = null;   
                    if(request.getParameter("bossId") != null){
                        bossId = Integer.parseInt(request.getParameter("bossId"));
                    }
                    Integer restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
                    Boolean result = EmployeeService.createEmployee(firstName, lastName, birthDate, addressId, bossId, restaurantId);
                    out.print(result);
                }
            }
            if (request.getParameter("task").equals("removeEmployee")) {
                if (request.getParameter("id") != null){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    Boolean result = EmployeeService.removeEmployee(id);
                    out.print(result);
                }
            }
            if (request.getParameter("task").equals("updateEmployee")) {
                if(request.getParameter("id") != null){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    String firstName = null;
                    if(request.getParameter("firstName") != null){
                        firstName = request.getParameter("firstName");
                    }
                    String lastName = null;
                    if(request.getParameter("lastName") != null){
                        lastName = request.getParameter("lastName");
                    }
                    Date birthDate = null;
                    if(request.getParameter("birthDate") != null){
                        birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthDate"));  
                    }
                    Integer addressId = null;               
                    if(request.getParameter("addressId") != null){
                        addressId = Integer.parseInt(request.getParameter("addressId"));
                    }
                    Integer bossId = null;   
                    if(request.getParameter("bossId") != null){
                        bossId = Integer.parseInt(request.getParameter("bossId"));
                    }
                    Integer restaurantId = null;
                    if(request.getParameter("restaurantId") != null){
                        restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
                    }
                    Boolean result = EmployeeService.updateEmployee(id, firstName, lastName, birthDate, addressId, bossId, restaurantId);
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
