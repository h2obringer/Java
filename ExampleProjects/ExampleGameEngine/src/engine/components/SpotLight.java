/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package engine.components;

import engine.core.Vector3f;
import engine.rendering.Attenuation;
import engine.rendering.Shader;

/**
 *
 * @author Administrator
 */
public class SpotLight extends PointLight
{
	private float m_cutoff;
	
	public SpotLight(Vector3f color, float intensity, Attenuation attenuation, float cutoff)
	{
		super(color, intensity, attenuation);
		this.m_cutoff = cutoff;

		SetShader(new Shader("forward-spot"));
	}
	
	public Vector3f GetDirection()
	{
		return GetTransform().GetTransformedRot().GetForward();
	}

	public float GetCutoff()
	{
		return m_cutoff;
	}

	public void SetCutoff(float cutoff)
	{
		this.m_cutoff = cutoff;
	}
}
