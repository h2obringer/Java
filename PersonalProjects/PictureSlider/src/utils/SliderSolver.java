/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;
import static utils.Variables.*;
    
/**
 *
 * @author Obringer
 */
public class SliderSolver{
    
    Vector <Pair <String,String> > adjacent_vertices = new Vector(10,2); //for(all vertices w adjacent to v){

    //****************************************************************************
    // Function: build_adjacent_vertex_list (private)
    //
    // Parameters: 
    //    1. a pair of strings. The first being the starting position of the board
    //         and the second being the moves needed to get there. The moves are
    //         very important and will become the final output for the BFS function
    //    2. a vector of pairs similar to the first parameter (This is passed by
    //         reference so as to modify it so that nothing has to be returned by
    //         the function)
    //
    // Description/Effect: fills a vector with the adjacent vertices to the current 
    //   vertex
    //
    //***************************************************************************** 
    void build_adjacent_vertex_list(Pair<String,String> p){ //parameters:(string &v,vector<string> &avl)
        //following is to find vertices avl adjacent to v
        int index=-1;
        //find index of where empty space is in slider position
        String v=p.getFirst();
        for(int i=0;i<v.length();i++){
            if(v.charAt(i)==BLANK){
               index=i;
               break;
            }
        }
        String temp;
        String moves;
        char[] replace = new char[9];

        //add all posible adjacent vertices to the vector
        if((index!=0)&&(index!=3)&&(index!=6)){
            temp= new String(v);
            replace=temp.toCharArray();
            moves=p.getSecond();
            replace[index]=replace[index-1];  
            replace[index-1]=BLANK;
            temp=String.valueOf(replace);
            moves+="R";
            Pair<String,String> p2 = new Pair(temp,moves);
            adjacent_vertices.add(p2);
        }
        if((index!=2)&&(index!=5)&&(index!=8)){
            temp= new String(v);
            replace=temp.toCharArray();
            moves=p.getSecond();
            replace[index]=replace[index+1];
            replace[index+1]=BLANK;
            temp=String.valueOf(replace);
            moves+="L";
            Pair<String,String> p2 = new Pair(temp,moves);
            adjacent_vertices.add(p2);
        }
        if((index!=0)&&(index!=1)&&(index!=2)){
            temp= new String(v);
            replace=temp.toCharArray();
            moves=p.getSecond();
            replace[index]=replace[index-3];
            replace[index-3]=BLANK;
            temp=String.valueOf(replace);
            moves+="D";
            Pair<String,String> p2 = new Pair(temp,moves);
            adjacent_vertices.add(p2);
        }
        if((index!=6)&&(index!=7)&&(index!=8)){
            temp=new String(v);
            replace=temp.toCharArray();
            moves=p.getSecond();
            replace[index]=replace[index+3];
            replace[index+3]=BLANK;
            temp=String.valueOf(replace);
            moves+="U";
            Pair<String,String> p2 = new Pair(temp,moves);
            adjacent_vertices.add(p2);
        }
    } 

    //***************************************************************************************
    // Function: BFS (public)
    //
    // Parameters: a string (this will be the starting position of the board in sliders)
    //
    // Description: This is a modified version of the BFS function given in class. It uses
    //   strings and not integers. It shows line for line what lines I have replaced from
    //   the original function in order to make it work for my problem.
    //
    // Effect: outputs to the screen the moves needed to complete the slider puzzle in order
    // 
    //***************************************************************************************
    public String BFS(String v){
        if(v.equals("012345678")){
            return "already solved";
        }

        Set<String> visited = new HashSet<String>();	
        visited.add(v);         		
        Queue<Pair <String,String>> q = new LinkedList<Pair<String,String>>();
        Pair<String,String> p = new Pair(v,"");
        q.add(p);
        while(!q.isEmpty()){
            p=q.poll();

            build_adjacent_vertex_list(p);

            for(Pair<String,String> pair : adjacent_vertices){
                if(pair.getFirst().equals("012345678")){
                    //System.out.println(pair.getSecond());
                    //System.out.println("solved");
                    return pair.getSecond();
                }
                
                if(!visited.contains(pair.getFirst())){
                    q.add(pair);
                    visited.add(pair.getFirst());
                }
            }	
            adjacent_vertices.clear();
        }
        //if(q.isEmpty()){  
        return "impossible";
    }
}
