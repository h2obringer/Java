/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package engine.core;

import engine.rendering.RenderingEngine;

/**
 *
 * @author Administrator
 */
public class Game {
	private GameObject m_root;

	public void Init() {}

	public void Input(float delta)
	{
		GetRootObject().InputAll(delta);
	}

	public void Update(float delta)
	{
		GetRootObject().UpdateAll(delta);
	}

	public void Render(RenderingEngine renderingEngine)
	{
		renderingEngine.Render(GetRootObject());
	}

	public void AddObject(GameObject object)
	{
		GetRootObject().AddChild(object);
	}

	private GameObject GetRootObject()
	{
		if(m_root == null)
			m_root = new GameObject();

		return m_root;
	}

	public void SetEngine(CoreEngine engine) { GetRootObject().SetEngine(engine); }
}
