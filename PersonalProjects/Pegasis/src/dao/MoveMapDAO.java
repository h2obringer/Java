/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import domain.Move;
import java.io.IOException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Administrator
 */
public class MoveMapDAO {
    private SqlSession sqlSession;
    
    public MoveMapDAO(SqlSession sqlSession){
        this.sqlSession=sqlSession;
    }
    
    public List<Move> getAllMoveMap() throws IOException{
        return sqlSession.selectList("getAllMoveMap"); //getAllMoves referenced at MoveDAO.xml
    };
}
