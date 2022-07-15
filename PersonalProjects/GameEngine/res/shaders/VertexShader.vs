#version 130 //130 seems to fix the supported version issue... would prefer 330 though

//layout (location = 0) in vec3 position; //supported only in version 330

in vec3 position; //only possible without the layout command due to glBindAttribLocation function

out vec4 color;

uniform float uniformFloat;
uniform mat4 transform;

void main(){
	//clamp ensure values are between 0 and 1
	color = vec4(clamp(position, 0.0, uniformFloat), 1.0);
	gl_Position = transform*vec4(position, 1.0); //can multiply position by a constant to change size of shapes
}