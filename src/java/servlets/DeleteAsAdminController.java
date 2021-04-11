/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dtos.FoodDto;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class DeleteAsAdminController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DeleteAsAdminController.class);
    private static final String SUCCESS = "AdminController";
    private static final String ERROR = "admin.jsp";

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
        String url = ERROR;
        List<FoodDto> list = null;
        try {
            String timeUpdate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            String btnIndex = request.getParameter("btnIndex"); // Index số trang 
            String txtEnd = null;
            if (btnIndex != null) {
                txtEnd = btnIndex;
            }
            int indexString = 1;
            if (txtEnd != null) {
                indexString = Integer.parseInt(txtEnd);
            }
            int count = daos.FoodDao.countTable();
            int pageSize = 20, endPage = 0;
            endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }
            request.setAttribute("END", endPage);
            request.setAttribute("COUNT_NO", indexString);
            list = daos.AdminDAO.get20Foods(indexString);
            //-------------
            String[] cbName = request.getParameterValues("checkBoxDelete");
            ArrayList<String> rehibilitate = new ArrayList<String>();
            for (int i = 0; i < list.size(); i++) {
                rehibilitate.add(list.get(i).getId());
            }
            for (int i = 0; i < cbName.length; i++) {
                rehibilitate.remove(cbName[i]);
            }
            for (int i = 0; i < cbName.length; i++) {
                daos.AdminDAO.deleteFoodAsAdmin(cbName[i], timeUpdate);
            }
            for (int i = 0; i < rehibilitate.size(); i++) {
                daos.AdminDAO.resetFoodAsAdmin(rehibilitate.get(i), timeUpdate);
            }
            url = SUCCESS;
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
