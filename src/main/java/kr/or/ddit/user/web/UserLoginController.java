package kr.or.ddit.user.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.board.BoardServiceImpl;
import kr.or.ddit.board.service.board.IBoardService;
import kr.or.ddit.user.model.User;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;


@WebServlet(urlPatterns = {"/login"}, loadOnStartup = 5)
public class UserLoginController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

   private IUserService userService;

   @Override
	public void init() throws ServletException {
	   userService = new UserServiceImpl();
	}
   /**
    *
    * Method : doGet
    * 작성자 : PC-23
    * 변경이력 :
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException
    * Method 설명 : 로그인 화면 요청 처리(forward)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //webapp/jsp/login.jsp  --> jsp.login.jsp
      request.getRequestDispatcher("/login/login.jsp").forward(request, response);
   }
   /**
    *
    * Method : doPost
    * 작성자 : PC-11
    * 변경이력 :
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException
    * Method 설명 : 로그인 요청 처리
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   // userId, password 파라미터 logger 출력
	   String userId = request.getParameter("userId");
	   String pass = request.getParameter("pass");

	   logger.debug("userId : {}", userId);
	   logger.debug("password : {}", pass);
	   // 사용자가 입력한 계정정보와 db에 있는 값이랑 비교
	   //db에서 조회해온 사용자 정보
	   User user = userService.getUser(userId);
	   logger.debug("user : {}", user);

	   // 사용자가 입력한 파라미터 정보와 db에서 조회해온 값이 동일 할 경우 --> webapp/main.jsp
	   // 사용자가 입력한 파라미터 정보와 db에서 조회해온 값이 다를 경우 --> webapp/login/login.jsp

	   // db에 존재하지 않는 사용자 체크 --> 로그인 화면으로 이동
	   if(user == null) {
		   doGet(request, response);
	   } else if(user.checkLoginValidate(userId, pass)) {

		   HttpSession session = request.getSession();
		   logger.debug("session.getId() : {}",session.getId());
		   session.setAttribute("S_USERVO", user);
		   request.setAttribute("elTest", "elTestValue");
		   request.getRequestDispatcher("/main.jsp").forward(request, response);
	   }
	   else {
		   doGet(request, response);
	   }
   }


}