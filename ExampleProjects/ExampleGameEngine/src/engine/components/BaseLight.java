/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package engine.components;

import engine.core.CoreEngine;
import engine.core.Vector3f;
import engine.rendering.Shader;

/**
 *
 * @author Administrator
 */
public class BaseLight extends GameComponent
{
	private Vector3f m_color;
	private float    m_intensity;
	private Shader   m_shader;
	
	public BaseLight(Vector3f color, float intensity)
	{
		this.m_color = color;
		this.m_intensity = intensity;
	}

	@Override
	public void AddToEngine(CoreEngine engine)
	{
		engine.GetRenderingEngine().AddLight(this);
	}

	public void SetShader(Shader shader)
	{
		this.m_shader = shader;
	}

	public Shader GetShader()
	{
		return m_shader;
	}

	public Vector3f GetColor()
	{
		return m_color;
	}

	public void SetColor(Vector3f color)
	{
		this.m_color = color;
	}

	public float GetIntensity()
	{
		return m_intensity;
	}

	public void SetIntensity(float intensity)
	{
		this.m_intensity = intensity;
	}
}
