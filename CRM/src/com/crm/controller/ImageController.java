package com.crm.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ImageController {

	private static Random r = new Random();
	private static char[] chs = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
			.toCharArray();
	private static final int NUMBER_OF_CHS = 4;
	private static final int IMG_WIDTH = 65;
	private static final int IMG_HEIGHT = 25;

	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public void image(HttpServletRequest request, HttpServletResponse response) {
		BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,
				BufferedImage.TYPE_INT_RGB); // ʵ����BufferedImage
		Graphics g = image.getGraphics();
		Color c = new Color(200, 200, 255); // ��֤��ͼƬ�ı�����ɫ
		g.setColor(c);
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT); // ͼƬ�ı߿�

		StringBuffer sb = new StringBuffer(); // ���ڱ�����֤���ַ���
		int index; // ������±�
		for (int i = 0; i < NUMBER_OF_CHS; i++) {
			index = r.nextInt(chs.length); // ���һ���±�
			g.setColor(new Color(r.nextInt(88), r.nextInt(210), r.nextInt(150))); // ���һ����ɫ
			g.drawString(chs[index] + "", 15 * i + 3, 18); // �����ַ�
			sb.append(chs[index]); // ��֤���ַ���
		}

		request.getSession().setAttribute("yzm", sb.toString()); // ����֤���ַ������浽session��
		try {
			ImageIO.write(image, "jpg", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
