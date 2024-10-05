package servlets;

import DAO.ProgressDAO;
import MODEL.Progress;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/progress")
public class ProgressServlet extends HttpServlet {
    private ProgressDAO progressDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        progressDAO = new ProgressDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "view":
                viewProgress(request, response);
                break;
            case "update":
                showUpdateForm(request, response);
                break;
            case "delete":
                deleteProgress(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addProgress(request, response);
                break;
            case "update":
                updateProgress(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    private void addProgress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int moduleId = Integer.parseInt(request.getParameter("moduleId"));
            String progressStatus = request.getParameter("progressStatus");
            String completionDate = request.getParameter("completionDate");
            int score = Integer.parseInt(request.getParameter("score"));
            int attempts = Integer.parseInt(request.getParameter("attempts"));

            Progress progress = new Progress(userId, moduleId, progressStatus, completionDate, score, attempts);
            progressDAO.createProgress(progress);
            System.out.println("Added new progress record.");
            // Redirect to the servlet with action=view instead of directly to the JSP
            response.sendRedirect("progress?action=view");
        } catch (NumberFormatException e) {
            // Handle invalid input
            System.err.println("Invalid input while adding progress record.");
            e.printStackTrace();
            response.sendRedirect("addProgress.jsp?error=Invalid input");
        }
    }

    private void viewProgress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Progress> progressList = progressDAO.getAllProgress();
        request.setAttribute("progressList", progressList);
        System.out.println("Forwarding to viewProgress.jsp with " + progressList.size() + " records.");
        request.getRequestDispatcher("viewProgress.jsp").forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int progressId = Integer.parseInt(request.getParameter("id"));
            Progress progress = progressDAO.getProgressById(progressId);
            if (progress != null) {
                request.setAttribute("progress", progress);
                System.out.println("Forwarding to updateProgress.jsp for ID: " + progressId);
                request.getRequestDispatcher("updateProgress.jsp").forward(request, response);
            } else {
                System.out.println("No progress record found with ID: " + progressId);
                response.sendRedirect("progress?action=view&error=No record found");
            }
        } catch (NumberFormatException e) {
            // Handle invalid input
            System.err.println("Invalid ID format in update request.");
            e.printStackTrace();
            response.sendRedirect("progress?action=view&error=Invalid ID");
        }
    }

    private void updateProgress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int progressId = Integer.parseInt(request.getParameter("progressId"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            int moduleId = Integer.parseInt(request.getParameter("moduleId"));
            String progressStatus = request.getParameter("progressStatus");
            String completionDate = request.getParameter("completionDate");
            int score = Integer.parseInt(request.getParameter("score"));
            int attempts = Integer.parseInt(request.getParameter("attempts"));

            Progress progress = new Progress(progressId, userId, moduleId, progressStatus, completionDate, score, attempts);
            progressDAO.updateProgress(progress);
            System.out.println("Updated progress record with ID: " + progressId);
            // Redirect to the servlet with action=view instead of directly to the JSP
            response.sendRedirect("progress?action=view");
        } catch (NumberFormatException e) {
            // Handle invalid input
            System.err.println("Invalid input while updating progress record.");
            e.printStackTrace();
            response.sendRedirect("updateProgress.jsp?error=Invalid input");
        }
    }

    private void deleteProgress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int progressId = Integer.parseInt(request.getParameter("id"));
            progressDAO.deleteProgress(progressId);
            System.out.println("Deleted progress record with ID: " + progressId);
            // Redirect to the servlet with action=view instead of directly to the JSP
            response.sendRedirect("progress?action=view");
        } catch (NumberFormatException e) {
            // Handle invalid input
            System.err.println("Invalid ID format in delete request.");
            e.printStackTrace();
            response.sendRedirect("progress?action=view&error=Invalid ID");
        }
    }

    @Override
    public void destroy() {
        progressDAO.closeConnection();
        super.destroy();
    }
}
