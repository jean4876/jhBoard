package kr.or.ddit.uploadFile;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.UploadFile;
import kr.or.ddit.board.repository.post.IPostDao;
import kr.or.ddit.board.repository.post.PostDaoImpl;
import kr.or.ddit.board.repository.uploadFile.IUploadFileDao;
import kr.or.ddit.board.repository.uploadFile.UploadFileDaoImpl;
import kr.or.ddit.util.MybatisUtil;

class UploadFileDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileDaoTest.class);

	private IUploadFileDao uploadFileDao;
	private SqlSession sqlSession;

   // 테스트에 공통적으로 필요한 자원을 생성 / 초기화
   @Before
   public void setup() {
      logger.debug("before");
      uploadFileDao = new UploadFileDaoImpl();
      sqlSession = MybatisUtil.getSession();
   }

   // 테스트에 공통적으로 사용한 자원을 해제
   @After
   public void tearDown() {
      logger.debug("after");
      sqlSession.close();
   }

	@Test
	void insertPostUploadFile() {
		/***Given***/
		String path="path";
		int postId = 1;
		String uploadNm = "파일명";
		int uploadId = 2;
		UploadFile file = new UploadFile();

		/***When***/

		file.setFilePath(path);
		file.setPostId(postId);
		file.setUploadFileNm(uploadNm);
		file.setUploadId(uploadId);

		/***Then***/
		int res = uploadFileDao.insertPostUploadFile(sqlSession, file);
		assertEquals(1, res);

	}

}
