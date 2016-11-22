#version 330

uniform sampler2D uTexture;
uniform float exposure;
in vec2 vTexCoord;

out vec4 outColor;



void main(void) 
{	    

	const float gamma = 2.2;
    vec3 hdrColor = texture(uTexture, vTexCoord).rgb;

	vec3 mapped = vec3(1.0) - exp(-hdrColor * exposure);
	
	mapped = pow(mapped, vec3(1.0 / gamma));
	
    outColor = vec4(mapped, 1.0);
}