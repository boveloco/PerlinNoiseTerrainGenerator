package br.pucpr.cg;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageGenerator {
	
	private PerlinNoiseGen p;
	
	public ImageGenerator(){
		p = new PerlinNoiseGen();
	}
	
	public BufferedImage generateBlackImage(int w, int h, int c) {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < w; i++) {
			for( int j = 0; j < h; j++){
				image.setRGB(i, j, new Color(c,c,c).getRGB());
			}
		}
		return image;
	}
		
	public boolean writeFile(String path, BufferedImage out, String name) {
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
	
	
	public BufferedImage generateCompletePerlin(BufferedImage b) {
		BufferedImage out = new BufferedImage(b.getWidth() * 2 ,b.getHeight() * 2, b.getType());
		int x = 0;
		int y = 0;
		
		for(int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				out.setRGB(i, j, b.getRGB(i, j));
			}
		}
		x = b.getWidth();
		//faz a parte de baixo
		
		for(int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				out.setRGB(i, j + x, b.getRGB(i, j));
			}
		}
		x = 0;
		y = b.getHeight();
		for(int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				out.setRGB(i + y, j + x, b.getRGB(i, j));
			}
		}
		x = b.getWidth();
		for(int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				out.setRGB(i + y, j + x, b.getRGB(i, j));
			}
		}
		return out;
	}
	
	public static void main(String[] args) {
		ImageGenerator h = new ImageGenerator();
		h.writeFile("../", h.generateCompletePerlin(h.p.GeneratePerlin(200, 200)), "perlin");
//		h.writeFile("../",	h.p.GeneratePerlin(1000, 1000), "perlin");
		System.out.println("Foi");
	}

}


