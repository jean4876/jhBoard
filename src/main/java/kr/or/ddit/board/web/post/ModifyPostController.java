package kr.or.ddit.board.web.post;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.ibatis.logging.LogException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.Post;
import kr.or.ddit.board.model.UploadFile;
import kr.or.ddit.board.service.post.PostServiceImpl;
import kr.or.ddit.board.service.uploadFile.UploadFileServiceImpl;
import kr.or.ddit.user.model.User;
import kr.or.ddit.util.FileuploadUtil;

@WebServlet("/modifyPost")
@MultipartConfig(maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5)
public class ModifyPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ModifyPostController.class);



    public ModifyPostController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int postId = Integer.parseInt(request.getParameter("postId"));

		PostServiceImpl postService = new PostServiceImpl();
		UploadFileServiceImpl uploadFileService = new UploadFileServiceImpl();
		Post post =  postService.getPost(postId);
		List<UploadFile> uploadFileList = uploadFileService.getPostUploadFileList(postId);
		request.setAttribute("post", post);
		request.setAttribute("uploadFileList", uploadFileList);

		request.getRequestDispatcher("/post/modifyPost.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		PostServiceImpl postService = new PostServiceImpl();
		UploadFileServiceImpl uploadFileService = new UploadFileServiceImpl();

		logger.debug("postTitle = {}" , request.getParameter("postTitle"));
		logger.debug("postId = {}" , request.getParameter("postId"));
		logger.debug("postContent = {}" , request.getParameter("smarteditor"));

		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("smarteditor");
		int postId = Integer.parseInt(request.getParameter("postId"));

		Post post = new Post();

		post.setPostId(postId);
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);

		int res = postService.updatePost(post);

		Collection<Part> file =  request.getParts();
		String filename = "";
		String path = "";
		for (Part p : file) {
			if ("picture".equals(p.getName())) {
				// 사용자가 파일을 업로드 한 경우
				if (p.getSize() > 0) {
					filename = FileuploadUtil.getFilename(p.getHeader("Content-Disposition")); // 사용자가 업로드한 파일명
					String realFilename = UUID.randomUUID().toString();
					String ext = FileuploadUtil.getFileExtension(p.getHeader("Content-Disposition"));
					path = FileuploadUtil.getPath() + realFilename + ext;
					p.write(path);
					UploadFile postFile = new UploadFile(filename, path, postId);
					uploadFileService.insertPostUploadFile(postFile);
				}
			}
		}

		response.sendRedirect(request.getContextPath() + "/post?postId="+ postId);
	}

}
