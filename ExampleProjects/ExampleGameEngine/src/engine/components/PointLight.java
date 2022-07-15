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
public class PointLight extends BaseLight
{
	private static final int COLOR_DEPTH = 256;

	private Attenuation m_attenuation;
	private float       m_range;
	
	public PointLight(Vector3f color, float intensity, Attenuation attenuation)
	{
		super(color, intensity);
		this.m_attenuation = attenuation;

		float a = attenuation.GetExponent();
		float b = attenuation.GetLinear();
		float c = attenuation.GetConstant() - COLOR_DEPTH * GetIntensity() * GetColor().Max();

		this.m_range = (float)((-b + Math.sqrt(b * b - 4 * a * c))/(2 * a));

		SetShader(new Shader("forward-point"));
	}

	public float GetRange()
	{
		return m_range;
	}

	public void SetRange(float range)
	{
		this.m_range = range;
	}

	public Attenuation GetAttenuation()
	{
		return m_attenuation;
	}
}
