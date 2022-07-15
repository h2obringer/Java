/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import domain.Move;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Administrator
 */
public class MoveDAO {
    
    private SqlSession sqlSession;
    
    public MoveDAO(SqlSession sqlSession){
        this.sqlSession=sqlSession;
    }
    
    public List<Move> getAllMoves() throws IOException{
        return sqlSession.selectList("getAllMoves"); //getAllMoves referenced at MoveDAO.xml
    };
    
    public Move getSingleMove(String moveName) throws IOException{
        HashMap<String,String> inputMap = new HashMap<>();
        inputMap.put("moveName", moveName);
        return (Move) sqlSession.selectOne("getSingleMove",inputMap); //getSingleMove referenced at MoveDAO.xml
    }
    
    public void insertMove(Move move) throws IOException{
        HashMap<String, Object> inputMap = new HashMap<>();
        inputMap.put("move",move.getMove());
        inputMap.put("category",move.getCategory());
        inputMap.put("movetype",move.getMoveType());
        inputMap.put("power",move.getPower());
        inputMap.put("accuracy",move.getAccuracy());
        inputMap.put("pp",move.getPP());
        sqlSession.insert("insertMove",inputMap);
        sqlSession.commit();
    }
    
    public void updateMove(Move move) throws IOException{
        HashMap<String, Object> inputMap = new HashMap<>();
        inputMap.put("move",move.getMove());
        inputMap.put("category",move.getCategory());
        inputMap.put("movetype",move.getMoveType());
        inputMap.put("power",move.getPower());
        inputMap.put("accuracy",move.getAccuracy());
        inputMap.put("pp",move.getPP());
        sqlSession.update("updateMove",inputMap);
        sqlSession.commit();
    }
    
    public void deleteMove(String move) throws IOException{
        sqlSession.update("deleteMove",move);
        sqlSession.commit();
    }
}
