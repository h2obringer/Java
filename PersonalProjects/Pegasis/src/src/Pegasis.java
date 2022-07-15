/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

import domain.Move;
import domain.MoveMap;
import domain.Pokedex;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.io.Resources;


/**
 *
 * @author Administrator
 */
public class Pegasis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Pegasis main = new Pegasis();
            List<Move> allMoves = main.getAllMoves();
            List<MoveMap> allMoveMaps = main.getAllMoveMap();
            List<Pokedex> allPokedex = main.getAllPokedex();
            
            
            File file = new File("insert_into_pokemon_tables.sql");
            // if file doesnt exists, then create it
            if (!file.exists()) {
             	file.createNewFile();
            }
 
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
                
            System.out.println(allMoves.size());
            System.out.println(allMoveMaps.size());
            System.out.println(allPokedex.size());
            
            
            
            for(int i=0;i<allMoves.size();i++){
                bw.write("INSERT INTO MOVES VALUES (\"" + allMoves.get(i).getMove() +"\",\"" + allMoves.get(i).getCategory() + "\",\"" + allMoves.get(i).getMoveType() + "\",\"" + allMoves.get(i).getPower() + "\",\"" + allMoves.get(i).getAccuracy() + "\",\"" + allMoves.get(i).getPP() + "\");");
                bw.write("\n");
            }
            bw.write("\n");
            for(int i=0;i<allMoveMaps.size();i++){
                bw.write("INSERT INTO MOVE_MAP VALUES (\"" + allMoveMaps.get(i).getPokemon() +"\",\"" + allMoveMaps.get(i).getMove() + "\",\"" + allMoveMaps.get(i).getLevel() + "\");");
                bw.write("\n");
            }
            bw.write("\n");
            for(int i=0;i<allPokedex.size();i++){
                bw.write("INSERT INTO POKEDEX VALUES (\"" + allPokedex.get(i).getId() +"\",\"" + allPokedex.get(i).getPokemon() + "\",\"" + allPokedex.get(i).getType1() + "\",\"" + allPokedex.get(i).getType2() + "\",\"" + allPokedex.get(i).getSeen() + "\",\"" + allPokedex.get(i).getCaught() + "\");");
                bw.write("\n");
            }
              
            bw.close();
            System.out.println("Done");
            
            /*System.out.println("Number of moves in database: " + allMoves.size()); 
            Move myMove = main.getSingleMove("Hyper Beam");
            System.out.println("Move name: " + myMove.getMove() + "\n"
                + "Move category: " + myMove.getCategory() + "\n"
                + "Move type: " + myMove.getMoveType() + "\n"
                + "Move Power: " + myMove.getPower() + "\n" 
                + "Move Accuracy: " + myMove.getAccuracy() +"\n"
                + "Move PP: " + myMove.getPP());
            myMove = new Move();
            myMove.setMove("Test Insert");
            myMove.setCategory("Special");
            myMove.setMoveType("Grass");
            myMove.setPower(1);
            myMove.setAccuracy(2);
            myMove.setPP(3);
            main.insertMove(myMove);
            System.out.println("Inserted Move");
            myMove.setPower(100);
            myMove.setAccuracy(100);
            main.updateMove(myMove);
            System.out.println("Updated Move");
            main.deleteMove("Test Insert");
            System.out.println("Deleted Move");*/
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //Obtain the MySQL connection with MyBatis
    private static SqlSession getSqlSession() throws IOException{
        String resource = "mybatis/SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }
    
    public List<Move> getAllMoves() throws IOException{
        return getSqlSession().selectList("getAllMoves"); //getAllMoves referenced at MoveDAO.xml
    };
    
    public List<Pokedex> getAllPokedex() throws IOException{
        return getSqlSession().selectList("getAllPokedex");
    }
    
    public List<MoveMap> getAllMoveMap() throws IOException{
        return getSqlSession().selectList("getAllMoveMap");
    }
    
    public Move getSingleMove(String moveName) throws IOException{
        HashMap<String,String> inputMap = new HashMap<>();
        inputMap.put("moveName", moveName);
        return (Move) getSqlSession().selectOne("getSingleMove",inputMap); //getSingleMove referenced at MoveDAO.xml
    }
    
    public void insertMove(Move move) throws IOException{
        HashMap<String, Object> inputMap = new HashMap<>();
        inputMap.put("move",move.getMove());
        inputMap.put("category",move.getCategory());
        inputMap.put("movetype",move.getMoveType());
        inputMap.put("power",move.getPower());
        inputMap.put("accuracy",move.getAccuracy());
        inputMap.put("pp",move.getPP());
        SqlSession sqlSession = getSqlSession();
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
        SqlSession sqlSession = getSqlSession();
        sqlSession.update("updateMove",inputMap);
        sqlSession.commit();
    }
    
    public void deleteMove(String move) throws IOException{
        SqlSession sqlSession = getSqlSession();
        sqlSession.update("deleteMove",move);
        sqlSession.commit();
    }
}
