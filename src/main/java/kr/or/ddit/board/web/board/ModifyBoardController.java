package kr.or.ddit.board.web.board;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.board.BoardServiceImpl;

@WebServlet("/modifyBoard")
public class ModifyBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ModifyBoardController.class);


    public ModifyBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HashMap<String, String> map = new HashMap<String, String>();

		logger.debug("boardId = {}", request.getParameter("boardId"));
		logger.debug("boardUse = {}", request.getParameter("boardUse"));

		map.put("boardId", request.getParameter("boardId"));
		map.put("boardUse", request.getParameter("boardUse"));

		logger.debug("Map = {}", map);

		BoardServiceImpl boardService = new BoardServiceImpl();
		int res = boardService.updateBoard(map);
		logger.debug("update res = {}", res);

		request.getRequestDispatcher("/board/manageBoard.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
