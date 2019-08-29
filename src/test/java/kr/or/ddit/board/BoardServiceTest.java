package kr.or.ddit.board;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.board.BoardServiceImpl;
import kr.or.ddit.board.service.board.IBoardService;

public class BoardServiceTest {

	private IBoardService boardService;

	@Before
	public void setUp() throws Exception {

		boardService = new BoardServiceImpl();

	}

	@Test
	public void buildBoard(){
		/***Given***/
		Map<String, String> map = new HashMap<String, String>();

		/***When***/
		map.put("boardNm", "JunitTest");
		map.put("boardUse", "사용");
		int res = boardService.buildBoard(map);

		/***Then***/
		assertEquals(1, res);

	}

	@Test
	public void getAllBoard() {
		/***Given***/

		/***When***/
		List<Board> list = boardService.getAllBoard();

		/***Then***/
		assertEquals(8, list.size());
	}

	@Test
	public void updateBoard() {
		/***Given***/
		Map<String, String> map = new HashMap<String, String>();

		/***When***/
		map.put("boardId", "2");
		map.put("boardUse", "미사용");
		int res = boardService.updateBoard(map);

		/***Then***/
		assertEquals(1, res);
	}


}
