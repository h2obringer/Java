/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package engine.components;

import engine.rendering.Material;
import engine.rendering.Mesh;
import engine.rendering.RenderingEngine;
import engine.rendering.Shader;

/**
 *
 * @author Administrator
 */
public class MeshRenderer extends GameComponent
{
	private Mesh     m_mesh;
	private Material m_material;

	public MeshRenderer(Mesh mesh, Material material)
	{
		this.m_mesh = mesh;
		this.m_material = material;
	}

	@Override
	public void Render(Shader shader, RenderingEngine renderingEngine)
	{
		shader.Bind();
		shader.UpdateUniforms(GetTransform(), m_material, renderingEngine);
		m_mesh.Draw();
	}
}
