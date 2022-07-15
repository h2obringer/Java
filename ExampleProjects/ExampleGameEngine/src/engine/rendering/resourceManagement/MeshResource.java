/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package engine.rendering.resourceManagement;

import static org.lwjgl.opengl.GL15.*;

/**
 *
 * @author Administrator
 */
public class MeshResource {
	private int m_vbo;
	private int m_ibo;
	private int m_size;
	private int m_refCount;

	public MeshResource(int size)
	{
		m_vbo = glGenBuffers();
		m_ibo = glGenBuffers();
		this.m_size = size;
		this.m_refCount = 1;
	}

	@Override
	protected void finalize()
	{
		glDeleteBuffers(m_vbo);
		glDeleteBuffers(m_ibo);
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

	public int GetVbo()  { return m_vbo; }
	public int GetIbo()  { return m_ibo; }
	public int GetSize() { return m_size; }
}
