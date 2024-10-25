package com.example.exam_ejb;

import com.example.exam_ejb.beanStateless.CdService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.Naming;
import java.util.List;

@WebServlet("/cds")
public class CdServlet extends HttpServlet {

    private CdService cdService;

    @Override
    public void init() throws ServletException {
        try {
            // Rechercher le service RMI par son nom
            cdService = (CdService) Naming.lookup("rmi://localhost:1099/CdService");
        } catch (Exception e) {
            throw new ServletException("Erreur de connexion au service RMI", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("listAvailable".equals(action)) {
                List<CD> availableCds = cdService.listAvailableCds();
                request.setAttribute("cds", availableCds);
                request.getRequestDispatcher("/cdList.jsp").forward(request, response);

            } else if ("listBorrowed".equals(action)) {
                List<CD> borrowedCds = cdService.listBorrowedCds();
                request.setAttribute("cds", borrowedCds);
                request.getRequestDispatcher("/borrowedList.jsp").forward(request, response);

            } else if ("borrow".equals(action)) {
                int cdId = Integer.parseInt(request.getParameter("cdId"));
                cdService.borrowCd(cdId);
                response.sendRedirect("cds?action=listAvailable");

            } else if ("return".equals(action)) {
                int cdId = Integer.parseInt(request.getParameter("cdId"));
                cdService.returnCd(cdId);
                response.sendRedirect("cds?action=listBorrowed");
            }
        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'interaction avec le service RMI", e);
        }
    }
}