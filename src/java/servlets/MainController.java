/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {

    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String SEARCH = "SearchController";
    private static final String GETALLFOOD = "GetAllFoodController";
    private static final String DELETEASADMIN = "DeleteAsAdminController";
    private static final String ADDTOCART = "AddToCartController";
    private static final String VIEWCART = "viewCart.jsp";
    private static final String ADMIN = "AdminController";
    private static final String UPDATE = "UpdateAsAdminController";
    private static final String ADDNEW = "AddNewAsAdminController";
    private static final String DELETECART = "DeleteOfCartController";
    private static final String UPDATECART = "UpdateInCartController";
    private static final String CONFIRM = "ConfirmController";
    private static final String HISTORY = "ShoppingHistoryController";
    private static final String HISTORYDETAIL = "HistoryDetailController";

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
        String url = GETALLFOOD;
        HttpSession session = request.getSession();
        try {
            String pageIn = (String) session.getAttribute("flag");
            String btnAction = request.getParameter("btnAction");
            String btnPage = request.getParameter("btnPage");
            String userName = (String) session.getAttribute("LOGIN_NAME");

            if ("Login".equals(btnAction)) {
                url = LOGIN;
            } else if ("Logout".equals(btnAction)) {
                url = LOGOUT;
            } else if ("Search".equals(btnAction)) {
                url = SEARCH;
            } else if ("Delete or reset".equals(btnAction)) {
                url = DELETEASADMIN;
            } else if ("Add this food".equals(btnAction)) {
                url = ADDTOCART;
            } else if ("home".equals(btnAction)) {
                url = GETALLFOOD;
            } else if ("View Cart".equals(btnAction)) {
                if (userName == null) {
                    url = LOGIN;
                    request.setAttribute("MSGUSERCART", "Sorry, but you must login to use this function!");
                } else {
                    url = VIEWCART;
                }
            } else if ("Update".equals(btnAction)) {
                url = UPDATE;
            } else if ("Add new".equals(btnAction)) {
                url = ADDNEW;
            } else if ("Delete food of Cart".equals(btnAction)) {
                url = DELETECART;
            } else if ("Update your food".equals(btnAction)) {
                url = UPDATECART;
            } else if ("Confirm".equals(btnAction)) {
                url = CONFIRM;
            } else if ("Shopping history".equals(btnAction)) {
                url = HISTORY;
            }else if ("Find".equals(btnAction)) {
                url = HISTORY;
            }else if ("View history".equals(btnAction)) {
                url = HISTORYDETAIL;
            }
            if ("1".equals(btnPage) || "2".equals(btnPage) || "3".equals(btnPage) || "4".equals(btnPage) || "5".equals(btnPage) || "6".equals(btnPage) || "7".equals(btnPage)) {
                if ("search".equals(pageIn)) {
                    url = SEARCH;
                }
                if ("getAll".equals(pageIn)) {
                    url = GETALLFOOD;
                }
                if ("admin".equals(pageIn)) {
                    url = ADMIN;
                }
            }
        } catch (Exception e) {
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
