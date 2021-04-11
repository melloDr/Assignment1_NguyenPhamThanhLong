/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dtos.OrdersDto;
import java.io.IOException;
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
public class ShoppingHistoryController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ShoppingHistoryController.class);

    private static final String SUCCESS = "shoppingHistory.jsp";
    private static final String ERROR = "login.jsp";

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
        HttpSession session = request.getSession();
        String url = ERROR;
        String name = (String) session.getAttribute("LOGIN_NAME");
        String role = (String) session.getAttribute("ROLE");
        List<OrdersDto> list = null;
        if (role == null || role.trim().isEmpty()||"admin".equals(role.trim()) ) {
            url = ERROR;
        } else if ("member".equals(role.trim())) {
            url = SUCCESS;
        }
        try {
            String txtSearchHis = request.getParameter("txtSearchHis");
            String searchBy = request.getParameter("txtSearchHistoryBy");
            if ("txtSearchByNameHis".equals(searchBy)) {
                String userId = daos.UserDao.getUserId(txtSearchHis).trim();
                list = daos.FoodDao.searchHistoryByName(userId);
            }
            if ("txtDateHis".equals(searchBy)) {
                list = daos.FoodDao.searchHistoryByDateBuy(txtSearchHis);
            }
            if (list == null) {
                request.setAttribute("MSGSEARCHHISTORY", "Please input right name or format!");
            } else {
                session.setAttribute("LISTHISTORY", list);
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
