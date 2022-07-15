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
public class PokedexDAO {
    private SqlSession sqlSession;
    
    public PokedexDAO(SqlSession sqlSession){
        this.sqlSession=sqlSession;
    }
    
    public List<Move> getAllPokedex() throws IOException{
        return sqlSession.selectList("getAllPokedex"); //getAllMoves referenced at MoveDAO.xml
    };
}
