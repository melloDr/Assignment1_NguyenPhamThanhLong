/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dtos.CartDto;
import dtos.FoodDto;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class ConfirmController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ConfirmController.class);
    private static final String SUCCESS = "viewCart.jsp";
    private static final String ERROR = "invalid.html";

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
        HttpSession session = request.getSession();
        try {
            CartDto cart = (CartDto) session.getAttribute("CART");
            String StringOver = utils.GetFormat.checkQuantity(cart);
            if (!(StringOver.trim().isEmpty())) {
                request.setAttribute("MSGSTRINGOVER", "The order's quantity is larger than the existing quantity: " + StringOver);
                //-- thêm cái name nữa
                url = SUCCESS;
            } else {
                String userName = (String) session.getAttribute("LOGIN_NAME");
                String GMAILLOGIN = (String) session.getAttribute("GMAILLOGIN");
                String userId = daos.FoodDao.getUserID(userName).trim();
                if ("GMAILLOGIN".equals(GMAILLOGIN.trim())) {
                    userId = "gmail";
                }
                String dateBuy = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                float total = Float.parseFloat((String) session.getAttribute("TOTAL").toString().trim());
                daos.FoodDao.addCart1(userId, total, dateBuy);
                int orderId = daos.FoodDao.getOrderID();
                for (FoodDto f : cart.getCart().values()) {
                    String a = f.getId();
                    int b = f.getQuantity();
                    float c = f.getPrice();
                    daos.FoodDao.addCart2(f.getId(), f.getQuantity(), f.getPrice(), orderId);
                }
                url = SUCCESS;
                //------- Lưu xuống database ở đây
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
