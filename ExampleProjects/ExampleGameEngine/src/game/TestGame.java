/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import engine.components.Camera;
import engine.components.DirectionalLight;
import engine.components.FreeLook;
import engine.components.FreeMove;
import engine.components.MeshRenderer;
import engine.components.PointLight;
import engine.components.SpotLight;
import engine.core.Game;
import engine.core.GameObject;
import engine.core.Matrix4f;
import engine.core.Quaternion;
import engine.core.Vector3f;
import engine.rendering.Attenuation;
import engine.rendering.Material;
import engine.rendering.Mesh;
import engine.rendering.Texture;
import engine.rendering.Window;

/**
 *
 * @author Administrator
 */
public class TestGame  extends Game
{
	public void Init()
	{
		Mesh mesh = new Mesh("plane3.obj");
		Material material2 = new Material(new Texture("bricks.jpg"), 1, 8,
			new Texture("bricks_normal.jpg"), new Texture("bricks_disp.png"), 0.03f, -0.5f);

		Material material = new Material(new Texture("bricks2.jpg"), 1, 8,
				new Texture("bricks2_normal.png"), new Texture("bricks2_disp.jpg"), 0.04f, -1.0f);

		Mesh tempMesh = new Mesh("monkey3.obj");

		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

		GameObject planeObject = new GameObject();
		planeObject.AddComponent(meshRenderer);
		planeObject.GetTransform().GetPos().Set(0, -1, 5);

		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0,0,1), 0.4f);

		directionalLightObject.AddComponent(directionalLight);

		GameObject pointLightObject = new GameObject();
		pointLightObject.AddComponent(new PointLight(new Vector3f(0, 1, 0), 0.4f, new Attenuation(0, 0, 1)));

		SpotLight spotLight = new SpotLight(new Vector3f(0,1,1), 0.4f,
				new Attenuation(0,0,0.1f), 0.7f);

		GameObject spotLightObject = new GameObject();
		spotLightObject.AddComponent(spotLight);

		spotLightObject.GetTransform().GetPos().Set(5, 0, 5);
		spotLightObject.GetTransform().SetRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(90.0f)));

		AddObject(planeObject);
		AddObject(directionalLightObject);
		AddObject(pointLightObject);
		AddObject(spotLightObject);

		GameObject testMesh3 = new GameObject().AddComponent(new LookAtComponent()).AddComponent(new MeshRenderer(tempMesh, material));

		AddObject(
				//AddObject(
				new GameObject().AddComponent(new FreeLook(0.5f)).AddComponent(new FreeMove(10.0f))
						.AddComponent(new Camera(new Matrix4f().InitPerspective((float) Math.toRadians(70.0f),
								(float) Window.GetWidth() / (float) Window.GetHeight(), 0.01f, 1000.0f))));

		AddObject(testMesh3);

		testMesh3.GetTransform().GetPos().Set(5, 5, 5);
		testMesh3.GetTransform().SetRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(-70.0f)));

		AddObject(new GameObject().AddComponent(new MeshRenderer(new Mesh("monkey3.obj"), material2)));

		directionalLight.GetTransform().SetRot(new Quaternion(new Vector3f(1, 0, 0), (float) Math.toRadians(-45)));
	}
}
