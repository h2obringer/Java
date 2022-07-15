/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import engine.components.GameComponent;
import engine.core.Quaternion;
import engine.core.Vector3f;
import engine.rendering.RenderingEngine;
import engine.rendering.Shader;

/**
 *
 * @author Administrator
 */
public class LookAtComponent extends GameComponent
{
	private RenderingEngine m_renderingEngine;

	@Override
	public void Update(float delta)
	{
		if(m_renderingEngine != null)
		{
			Quaternion newRot = GetTransform().GetLookAtRotation(m_renderingEngine.GetMainCamera().GetTransform().GetTransformedPos(),
					new Vector3f(0, 1, 0));
					//GetTransform().GetRot().GetUp());

			GetTransform().SetRot(GetTransform().GetRot().NLerp(newRot, delta * 5.0f, true));
			//GetTransform().SetRot(GetTransform().GetRot().SLerp(newRot, delta * 5.0f, true));
		}
	}

	@Override
	public void Render(Shader shader, RenderingEngine renderingEngine)
	{
		this.m_renderingEngine = renderingEngine;
	}
}
