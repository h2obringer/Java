/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package engine.rendering.resourceManagement;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

/**
 *
 * @author Administrator
 */
public class TextureResource {
	private int m_id;
	private int m_refCount;

	public TextureResource()
	{
		this.m_id = glGenTextures();
		this.m_refCount = 1;
	}

	@Override
	protected void finalize()
	{
		glDeleteBuffers(m_id);
	}

	public void AddReference()
	{
		m_refCount++;
	}

	public boolean RemoveReference()
	{
		m_refCount--;
		return m_refCount == 0;
	}

	public int GetId() { return m_id; }
}
