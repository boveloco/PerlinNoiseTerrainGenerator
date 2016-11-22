#version 330

uniform sampler2D uTexture;
uniform float exposure;
in vec2 vTexCoord;

out vec4 outColor;



void main(void) 
{	    

    vec3 hdrColor = texture(uTexture, vTexCoord).rgb;
    outColor = vec4(hdrColor, 1.0);
}