package com.zkq.mygallery.controller;

import com.zkq.mygallery.entity.Painting;
import com.zkq.mygallery.service.PaintingService;
import com.zkq.mygallery.utils.PageModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaintingServlet", urlPatterns = "/page")
public class PaintingServlet extends HttpServlet {
    private PaintingService paintingService = new PaintingService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = request.getParameter("p");
        String rows = request.getParameter("r");
        String category = request.getParameter("c");
        if(page==null){
            page = "1";
        }
        if(rows==null){
            rows= "6";
        }
        PageModel pageModel = paintingService.pagination(Integer.parseInt(page), Integer.parseInt(rows),category);
        request.setAttribute("PageModel",pageModel);
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }
}
