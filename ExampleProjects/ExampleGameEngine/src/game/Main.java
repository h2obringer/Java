/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import engine.core.CoreEngine;

/**
 *
 * @author Administrator
 */
public class Main {
	public static void main(String[] args)
	{
		CoreEngine engine = new CoreEngine(800, 600, 60, new TestGame());
		engine.CreateWindow("3D Game Engine");
		engine.Start();
	}
}
