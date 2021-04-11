/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.FoodDao;
import dtos.FoodDto;
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
public class SearchController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddNewAsAdminController.class);

    private static final String SUCCESS = "search.jsp";

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
        String url = SUCCESS;
        List<FoodDto> list = null;
        try {
            String txtSearchByName = request.getParameter("txtSearchByName");
            request.setAttribute("contentOfName", txtSearchByName);
            String txtPriceFrom = "";
            txtPriceFrom = request.getParameter("txtPriceFrom");
            request.setAttribute("contentOfPriceFrom", txtPriceFrom);
            String txtPriceTo = "";
            txtPriceTo = request.getParameter("txtPriceTo");
            request.setAttribute("contentOfPriceTo", txtPriceTo);
            String txtSearchByCategory = request.getParameter("txtSearchByCategory");
            request.setAttribute("contentOfCategory", txtSearchByCategory);
            String btnPage = request.getParameter("btnPage");// cái index ở đây bị trùng
            String txtEnd = null;
            if (btnPage != null) {
                txtEnd = btnPage;
            }
            int indexString = 1;
            if (txtEnd != null) {
                indexString = Integer.parseInt(txtEnd);
            }
            int count = daos.FoodDao.countTableForSearch(txtSearchByName, txtSearchByCategory, txtPriceFrom, txtPriceTo);
            int pageSize = 20, endPage = 0;
            endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }
            request.setAttribute("END", endPage);
            request.setAttribute("COUNT_NO", indexString);
            request.setAttribute("txtSearchByCategory", txtSearchByCategory);
            //------------------------------------
            String categories = daos.FoodDao.getCategory();
            HttpSession session = request.getSession();
            session.setAttribute("cbbCategories", categories);
            list = FoodDao.search(indexString, txtSearchByName, txtSearchByCategory, txtPriceFrom, txtPriceTo);
            if (list != null) {
                request.setAttribute("LIST", list);
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
