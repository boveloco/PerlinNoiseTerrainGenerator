package br.pucpr.cg;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.json.simple.parser.ParseException;

public class ImageGenerator {
	
	private BufferedImage out;
	private Parser parse;
	
	public ImageGenerator(int w){
		parse = new Parser();
		out = new BufferedImage(w,w,BufferedImage.TYPE_INT_RGB);
	}
	
	private final String PATH_OUT_FILE = "img/";
	private final String NAME_OUT_FILE = "perlin";
	
	private final int NO_LAST_VALUE = -1;
	
	private int MAX_SMOOTH_FILTER = 10;
	private int TERRAIN_TRESHOLD = 150;
	private int TERRAIN_MIN_VALUE = 40;
	private int TERRAIN_MAX_DIFFERENCE = 60;
	
	private ImageGenerator writeFile() {
		try {
			ImageIO.write(out, "png", new File(PATH_OUT_FILE + NAME_OUT_FILE + ".png"));
			System.out.println(PATH_OUT_FILE + NAME_OUT_FILE + ".png");
			return this;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro Writing File");
		}
		return null;
	}

	private ImageGenerator concatBorder() {
		for (int i = 0; i < out.getHeight(); i++) {
			out.setRGB(i, 0, new Color(0).getRed());
		}
		return this;
	}
	public ImageGenerator loadFromConfig() throws FileNotFoundException, ParseException, IOException{
		parse.parser();
		this.MAX_SMOOTH_FILTER = parse.MSF;
		this.TERRAIN_TRESHOLD = parse.TTH;
		this.TERRAIN_MIN_VALUE = parse.TMV;
		this.TERRAIN_MAX_DIFFERENCE = parse.TMD;
		return this.matrixIterator().smooth(MAX_SMOOTH_FILTER).writeFile();
		
	}
	private int getTerrainReference(int lastValue) {
//		System.out.println("LV: " + lastValue);
		int count = 0;
		Random generator = new Random();
		int aux;
		aux = generator.nextInt(TERRAIN_TRESHOLD) + TERRAIN_MIN_VALUE;
		if (lastValue == NO_LAST_VALUE) {
			return aux;
		} 
		while (Math.abs(aux - lastValue) > TERRAIN_MAX_DIFFERENCE) {
			count++;
			aux = generator.nextInt(TERRAIN_TRESHOLD) + TERRAIN_MIN_VALUE;
		}
//		System.out.println("Count: " + count);
		return aux;		
	}
	
	private ImageGenerator matrixIterator() { 
		int c = getTerrainReference(NO_LAST_VALUE);
		int rgb = new Color(c,c,c).getRGB();
		for (int i = 0; i < out.getHeight(); i++) {
			if(i == 0){
				out.setRGB(i, i, rgb);
				continue;
			}
//			System.out.println(i);
			c = getTerrainReference(c);
			rgb = new Color(c,c,c).getRGB();
			out.setRGB(i, 0, rgb);
			c = getTerrainReference(c);
			rgb = new Color(c,c,c).getRGB();
			out.setRGB(0, i, rgb);
			c = getTerrainReference(c);
			rgb = new Color(c,c,c).getRGB();
			out.setRGB(i, i, rgb);
			for (int j = i; j > 1; j--) {
				c = getTerrainReference(c);
				rgb = new Color(c,c,c).getRGB();
				out.setRGB(i, j, rgb);
				c = getTerrainReference(c);
				rgb = new Color(c,c,c).getRGB();
				out.setRGB(j, i, rgb);
			}

		}
		return this;
	}
	
	private ImageGenerator smooth(int iterations) {
		float [][]kernel = new float[][] {
        	{1.0f/16, 2.0f/16, 1.0f/16},
        	{2.0f/16, 4.0f/16, 2.0f/16},
        	{1.0f/16, 2.0f/16, 1.0f/16}
        };
        
//		float [][]kernel = new float[][] {
//			{1.0f/9, 1.0f/9, 1.0f/9},
//			{1.0f/9, 1.0f/9, 1.0f/9},
//			{1.0f/9, 1.0f/9, 1.0f/9}        	
//        };
        
        int width = out.getWidth();
        int depth = out.getHeight(); 
        for (int i = 0; i < iterations; i++) {
//        	System.out.println(i);
        	for (int z = 0; z < depth; z++) {
        		for (int x = 0; x < width; x++) {
        			float tone1 = 0;
        			for(int ky = 0; ky < 3; ky++) {
        				for(int kx = 0; kx < 3; kx++) {
        					int px = x + (kx - 1);
        					int py = z + (ky - 1);
        					
        					if(px < 0 || px >= width || py < 0 || py >= depth) {
        						continue;
        					}
        					
        					Color pixel = new Color(out.getRGB(px, py));
        					float r = (pixel.getRed() * kernel[kx][ky]);
        					tone1 += r;
        				}
        			}
        			out.setRGB(z, x, new Color((int)tone1, (int)tone1, (int)tone1).getRGB());
        		}
        	}
		}
        return this;
	}
}

 
