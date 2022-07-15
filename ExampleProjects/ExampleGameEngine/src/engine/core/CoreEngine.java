/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package engine.core;

import engine.rendering.RenderingEngine;
import engine.rendering.Window;

/**
 *
 * @author Administrator
 */
public class CoreEngine {
	private boolean         m_isRunning;
	private Game            m_game;
	private RenderingEngine m_renderingEngine;
	private int             m_width;
	private int             m_height;
	private double          m_frameTime;
	
	public CoreEngine(int width, int height, double framerate, Game game)
	{
		this.m_isRunning = false;
		this.m_game = game;
		this.m_width = width;
		this.m_height = height;
		this.m_frameTime = 1.0/framerate;
		game.SetEngine(this);
	}

	public void CreateWindow(String title)
	{
		Window.CreateWindow(m_width, m_height, title);
		this.m_renderingEngine = new RenderingEngine();
	}

	public void Start()
	{
		if(m_isRunning)
			return;
		
		Run();
	}
	
	public void Stop()
	{
		if(!m_isRunning)
			return;
		
		m_isRunning = false;
	}
	
	private void Run()
	{
		m_isRunning = true;
		
		int frames = 0;
		double frameCounter = 0;

		m_game.Init();

		double lastTime = Time.GetTime();
		double unprocessedTime = 0;
		
		while(m_isRunning)
		{
			boolean render = false;

			double startTime = Time.GetTime();
			double passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime;
			frameCounter += passedTime;
			
			while(unprocessedTime > m_frameTime)
			{
				render = true;
				
				unprocessedTime -= m_frameTime;
				
				if(Window.IsCloseRequested())
					Stop();

				m_game.Input((float) m_frameTime);
				Input.Update();
				
				m_game.Update((float) m_frameTime);
				
				if(frameCounter >= 1.0)
				{
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			if(render)
			{
				m_game.Render(m_renderingEngine);
				Window.Render();
				frames++;
			}
			else
			{
				try
				{
					Thread.sleep(1);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		CleanUp();
	}

	private void CleanUp()
	{
		Window.Dispose();
	}

	public RenderingEngine GetRenderingEngine() {
		return m_renderingEngine;
	}
}
