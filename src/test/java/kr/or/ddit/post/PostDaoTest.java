package kr.or.ddit.post;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.BoardDaoTest;
import kr.or.ddit.board.model.Post;
import kr.or.ddit.board.repository.board.BoardDaoImpl;
import kr.or.ddit.board.repository.board.IBoardDao;
import kr.or.ddit.board.repository.post.IPostDao;
import kr.or.ddit.board.repository.post.PostDaoImpl;
import kr.or.ddit.board.service.post.PostServiceImpl;
import kr.or.ddit.util.MybatisUtil;

public class PostDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(PostDaoTest.class);

	private IPostDao postDao;
	private SqlSession sqlSession;

   // 테스트에 공통적으로 필요한 자원을 생성 / 초기화
   @Before
   public void setup() {
      logger.debug("before");
      postDao = new PostDaoImpl();
      sqlSession = MybatisUtil.getSession();
   }

   // 테스트에 공통적으로 사용한 자원을 해제
   @After
   public void tearDown() {
      logger.debug("after");
      sqlSession.close();
   }

	@Test
	public void insertPost() {
		/***Given***/
		Post post = new Post();

		/***When***/
		post.setBoardId(3);
		post.setPostContent("내용입니다");
		post.setPostTitle("제목입니다");
		post.setUserId("brown");

		int res = postDao.insertPost(sqlSession, post);

		/***Then***/
		assertEquals(1, res);

	}

	@Test
	public void getPost() {
		/***Given***/
		int postId = 1;

		/***When***/
		Post post = postDao.getPost(sqlSession, postId);


		/***Then***/
		assertEquals(1, post.getPostId());

	}

}
