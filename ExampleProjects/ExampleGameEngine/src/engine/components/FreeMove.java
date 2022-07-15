/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package engine.components;

import engine.core.Input;
import engine.core.Vector3f;

/**
 *
 * @author Administrator
 */
public class FreeMove extends GameComponent
{
	private float m_speed;
	private int   m_forwardKey;
	private int   m_backKey;
	private int   m_leftKey;
	private int   m_rightKey;

	public FreeMove(float speed)
	{
		this(speed, Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D);
	}

	public FreeMove(float speed, int forwardKey, int backKey, int leftKey, int rightKey)
	{
		this.m_speed = speed;
		this.m_forwardKey = forwardKey;
		this.m_backKey = backKey;
		this.m_leftKey = leftKey;
		this.m_rightKey = rightKey;
	}

	@Override
	public void Input(float delta)
	{
		float movAmt = m_speed * delta;

		if(Input.GetKey(m_forwardKey))
			Move(GetTransform().GetRot().GetForward(), movAmt);
		if(Input.GetKey(m_backKey))
			Move(GetTransform().GetRot().GetForward(), -movAmt);
		if(Input.GetKey(m_leftKey))
			Move(GetTransform().GetRot().GetLeft(), movAmt);
		if(Input.GetKey(m_rightKey))
			Move(GetTransform().GetRot().GetRight(), movAmt);
	}

	private void Move(Vector3f dir, float amt)
	{
		GetTransform().SetPos(GetTransform().GetPos().Add(dir.Mul(amt)));
	}
}
