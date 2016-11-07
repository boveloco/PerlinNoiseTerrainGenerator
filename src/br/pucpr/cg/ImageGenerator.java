package br.pucpr.cg;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageGenerator {
	
	private PerlinNoiseGen p;
	
	public ImageGenerator(){
		p = new PerlinNoiseGen();
	}
	
	
	private int perlin[][];
	private final int NO_LAST_VALUE = -1;
	private final int TERRAIN_TRESHOLD = 50;
	private final int TERRAIN_MEDIAN_VALUE = 20;
	private final int TERRAIN_MAX_DIFFERENCE = 3;
		
	private boolean writeFile(String path, BufferedImage out, String name) {
		try {
			ImageIO.write(out, "png", new File(path + name + ".png"));
			System.out.println(path + name + ".png");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro Writing File");
		}
		return false;
	}
	
	private BufferedImage generateTerrain(int x, int y) {
		BufferedImage image = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				z = getTerrainReference(z);
				System.out.println(z);
				image.setRGB(i, j, new Color(z,z,z).getRGB());
			}
		}
		return image;
	}
	
	private int getTerrainReference(int lastValue) {
		System.out.println("LV: " + lastValue);
		int count = 0;
		Random generator = new Random();
		int aux;
		aux = generator.nextInt(TERRAIN_TRESHOLD) + TERRAIN_MEDIAN_VALUE;
		if (lastValue == NO_LAST_VALUE) {
			return aux;
		} 
		while (Math.abs(aux - lastValue) > TERRAIN_MAX_DIFFERENCE) {
			count++;
			aux = generator.nextInt(TERRAIN_TRESHOLD) + TERRAIN_MEDIAN_VALUE;
		}
		System.out.println("Count: " + count);
		if (count > 100) {
			System.out.println(count);			
		}
		return aux;		
	}
	
	private BufferedImage matrixIterator(int size) { 
		BufferedImage out = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		int c = getTerrainReference(NO_LAST_VALUE);
		int rgb = new Color(c,c,c).getRGB();
		for (int i = 0; i < size; i++) {
			if(i == 0){
				out.setRGB(i, i, rgb);
				continue;
			}
			System.out.println(i);
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
		return out;
	}
	
	public static void main(String[] args) {
		ImageGenerator h = new ImageGenerator();
//		h.writeFile("../", h.generateCompletePerlin(h.p.GeneratePerlin(200, 200)), "perlin");
//		h.writeFile("../",	h.p.GeneratePerlin(1000, 1000), "perlin");
//		h.writeFile("../", h.p.GeneratePerlin(1000, 1000), "perlinh");
//		h.getTerrainReference(40);
		h.writeFile("../", h.matrixIterator(1000), "perlin");
//		h.writeFile("../", h.generateTerrain(1000, 1000), "perlin");
		System.out.println("Foi");
	}

}

 
