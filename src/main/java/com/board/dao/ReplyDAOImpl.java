package com.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession sql;
	private static String namespace = "com.board.mappers.reply";
	
	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace+".replyList",bno);
	}

	@Override
	public void write(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace + ".replyWrite", vo);
	}

	@Override
	public void modify(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".replyModify", vo);
	}

	@Override
	public void delete(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace + ".replyDelete", vo);
	}

}
