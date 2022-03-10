package com.team5.controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team5.dao.RecipeDAO;
import com.team5.dao.UserDAO;
import com.team5.vo.RecipeVO;
import com.team5.vo.UserVO;

public class MyPageAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "/mypage/mypage.jsp";
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			url = "app?command=login_form";
		}
		else {
			UserDAO userDAO = UserDAO.getInstance();
			RecipeDAO recipeDAO = RecipeDAO.getInstance();


			UserVO userVO = userDAO.selectUserById(loginUser.getId());
			List<RecipeVO> recipeVOS = recipeDAO.selectRecipeListByUserId(1);

			System.out.println("MyPageAction");
			request.setAttribute("userVO", userVO);
			request.setAttribute("recipeVOS", recipeVOS);
		}

	
		request.getRequestDispatcher(url).forward(request, response);
	}
}
