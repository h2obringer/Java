/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package engine.rendering;

import engine.core.Vector3f;

/**
 *
 * @author Administrator
 */
public class Attenuation extends Vector3f
{
	public Attenuation(float constant, float linear, float exponent) {
		super(constant, linear, exponent);
	}

	public float GetConstant()
	{
		return GetX();
	}

	public float GetLinear()
	{
		return GetY();
	}

	public float GetExponent()
	{
		return GetZ();
	}
}
