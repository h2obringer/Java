/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import domain.Move;
import domain.Owned;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Administrator
 */
public class OwnedDAO {
    
    private SqlSession sqlSession;
    
    public OwnedDAO(SqlSession sqlSession){
        this.sqlSession=sqlSession;
    }
    
    public List<Move> getAllOwned() throws IOException{
        return sqlSession.selectList("getAllOwned"); //getAllMoves referenced at MoveDAO.xml
    };
    
    public Owned getSingleOwned(String pokemonName) throws IOException{
        HashMap<String,String> inputMap = new HashMap<>();
        inputMap.put("pokemonName", pokemonName);
        return (Owned) sqlSession.selectOne("getSingleOwned",inputMap); //getSingleMove referenced at MoveDAO.xml
    }
    /*
    public void insertMove(Owned owned) throws IOException{
        HashMap<String, Object> inputMap = new HashMap<>();
        inputMap.put("",owned.);
        inputMap.put("",owned.);
        inputMap.put("",owned.);
        inputMap.put("",owned.);
        inputMap.put("",owned.);
        inputMap.put("",owned.);
        sqlSession.insert("insertOwned",inputMap);
        sqlSession.commit();
    }
    
    public void updateOwned(Owned owned) throws IOException{
        HashMap<String, Object> inputMap = new HashMap<>();
        inputMap.put("",owned.);
        inputMap.put("",owned.);
        inputMap.put("",owned.);
        inputMap.put("",owned.);
        inputMap.put("",owned.);
        inputMap.put("",owned.);
        sqlSession.update("updateOwned",inputMap);
        sqlSession.commit();
    }*/
    
    public void deleteOwned(String owned) throws IOException{
        sqlSession.update("deleteOwned",owned);
        sqlSession.commit();
    }
}
