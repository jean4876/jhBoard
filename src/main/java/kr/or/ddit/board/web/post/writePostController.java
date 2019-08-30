package kr.or.ddit.board.web.post;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.Post;
import kr.or.ddit.board.service.board.BoardServiceImpl;
import kr.or.ddit.board.service.post.PostServiceImpl;
import kr.or.ddit.user.model.User;

@WebServlet("/writePost")
@MultipartConfig(maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5)
public class writePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(writePostController.class);



    public writePostController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String boardId = request.getParameter("boardId");

		request.setAttribute("boardId", boardId);

		request.getRequestDispatcher("/post/writePost.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		PostServiceImpl postService = new PostServiceImpl();
		User sessionUser =  (User) session.getAttribute("S_USERVO");

		logger.debug("boardId = {} " , request.getParameter("boardId"));
		logger.debug("postTitle = {} " , request.getParameter("postTitle"));
		logger.debug("postContent = {} " , request.getParameter("smarteditor"));


		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("smarteditor");
		String userId = sessionUser.getUserId();
		logger.debug("boardID = {} " , request.getParameter("boardId"));

		Post post = new Post();

		post.setBoardId(boardId);
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);
		post.setUserId(userId);

		int res = postService.insertPost(post);

		logger.debug("생성결과 : {}" , res);

		doGet(request, response);

	}

}
