/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dtos.FoodDto;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class AddNewAsAdminController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddNewAsAdminController.class);

    private static final String SUCCESS = "AdminController";
    private static final String ERROR = "createFood.jsp";

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
        response.setContentType("text/html;charset=UTF-8");
        String sql = ERROR;
        List<FoodDto> list = null;
        HttpSession session = request.getSession();
        try {
            String categories = daos.FoodDao.getCategory();
            session.setAttribute("cbbCategories", categories);
            String txtFoodIdToCreate = request.getParameter("txtFoodIdToCreate");
            session.setAttribute("NAMETOCREATESESSION", txtFoodIdToCreate);
            String txtNameToCreate = request.getParameter("txtNameToCreate");
            session.setAttribute("IDTOCREATESESSION", txtNameToCreate);
            list = daos.AdminDAO.getAllFood();
            for (int i = 0; i < list.size(); i++) {
                String s = list.get(i).getId();
                if (list.get(i).getId().trim().equals(txtFoodIdToCreate.trim())) {
                    txtFoodIdToCreate = "";
                }
            }
            String txtQuantityToCreate = request.getParameter("txtQuantityToCreate");
            session.setAttribute("QUANTITYTOCREATESESSION", txtNameToCreate);
//            int quantityToCreate = Integer.parseInt(txtQuantityToCreate);
            int quantityToCreate = utils.GetFormat.getInt(txtQuantityToCreate);
            String txtPriceToCreate = request.getParameter("txtPriceToCreate");
            session.setAttribute("PRICETOCREATESESSION", txtNameToCreate);
//            float priceToCreate = Float.parseFloat(txtPriceToCreate);
            float priceToCreate = utils.GetFormat.getFloat(txtPriceToCreate);
            String txtCategoryToCreate = request.getParameter("txtCategoryToCreate");
            String categoryToCreate = daos.AdminDAO.getCategoryID(txtCategoryToCreate);
            String txtImagesToCreate = request.getParameter("txtImagesToCreate");
            String dateOfCreate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            String status = "true";
            if (txtFoodIdToCreate.trim().isEmpty()) {
                request.setAttribute("MSGIDADD", "This Id is Duplicate!");
            } else {
                request.setAttribute("MSGIDADD", null);
            }
            if (quantityToCreate == 0) {
                request.setAttribute("MSGQUANTITYADD", "Quantity must is positive number!");
            } else {
                request.setAttribute("MSGQUANTITYADD", null);
            }
            if (priceToCreate == 0) {
                request.setAttribute("MSGPRICEADD", "Price must is positive number!");
            } else {
                request.setAttribute("MSGPRICEADD", null);
            }
            if (!(txtFoodIdToCreate.trim().isEmpty()) && !(quantityToCreate == 0) && !(priceToCreate == 0)) {
                daos.AdminDAO.addFoodAsAdmin(txtFoodIdToCreate, txtNameToCreate, quantityToCreate, priceToCreate, categoryToCreate, txtImagesToCreate, dateOfCreate, status);
                sql = SUCCESS;
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            request.getRequestDispatcher(sql).forward(request, response);
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
