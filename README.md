# PerlinNoiseTerrainGenerator

# Introduction #

An openGL project developend for educational porpouse on PUC-PR. It uses perlin noise to creates a procedural terrain rendered in OpenGL.

# Basics #
There's a file in root folder in project called 'config.json' that defines all properties that the generator uses to create the perlin noise image.. These are the parametes and expected values:

* MAX_SMOOTH_FILTER * = The times that the smooth filter runs on the generated image. Bigger value, smoothess terrain. 
* TERRAIN_MIN_VALUE * = The base value that the terrain gets. (Remembering, the water treshold is 25).. Max value = 255;
* TERRAIN_TRESHOLD * = The value that variates the value of height. The expected value is 255 - TERRAIN_MIN_VALUE.,
* TERRAIN_MAX_DIFFERENCE * = The max difference between the last and the one pixel that's generated in the procedural proceses. Bigger more spikes may have on image.
* POST_FX * = It's the post processing code that will be executed. The base is 'fxNone' but it's possible to enable HDR using 'fxHDR' on this parameter.

# Hotkeys #
While up and running the project, you can use WASD to move the camera position and use the Arrow keys to move the camera directions..

With the space keys, it loads the configuration inside the config.json file and generate again the terrain. (It changes the PostFX too). # Will Always generate the terrain #!

While on HDR mode, you can use the keys 'I' or 'O' to increase or decrease the exposure value.

# Thanks #
I have to thank the professor Vinicius Godoy Mendonça, my partner Rodrigo Stacheski and a friend Alisson Diego for helping in this project.
