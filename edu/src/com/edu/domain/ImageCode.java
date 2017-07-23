package com.edu.domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component("imageCode")
public class ImageCode {
	/*
     * Spring 3֧��@valueע��ķ�ʽ��ȡproperties�ļ��е�����ֵ������˶�ȡ�����ļ��Ĵ��롣
     * ��applicationContext.xml�ļ�������properties�ļ�,��bean��ʹ��@valueע���ȡ�����ļ���ֵ
     * ��ʹ���������˳�ֵҲ���������ļ���ֵΪ׼��
     */
    @Value("${ImageCode.width}")
    private int width;

    @Value("${ImageCode.height}")
    private int height;

    @Value("${ImageCode.codeLength}")
    private int codeLength;

    @Value("${ImageCode.randomString}")
    private String randomString;

    @Value("${ImageCode.sessionKey}")
    private String sessionKey;

    @Value("${ImageCode.font.name}")
    private String fontName;

    @Value("${ImageCode.font.style}")
    private int fontStyle;

    @Value("${ImageCode.font.size}")
    private int fontSize;

    public BufferedImage getImage(HttpServletRequest request) {
        // ���ڴ��д���ͼƬ
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // ��ȡͼƬ������
        Graphics g = image.getGraphics();
        // ���������
        Random random = new Random();
        // �趨������ɫ
        g.setColor(getRandColor(100, 250));
        g.fillRect(0, 0, width, height);
        // �趨����
        g.setFont(new Font(fontName, fontStyle, fontSize));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, xl, y + yl);
        }
        // ȡ�����������֤��
        String sRand = randomRand(codeLength);
        int strWidth = width / 2 - g.getFontMetrics().stringWidth(sRand) / codeLength - fontSize;
        int strHeight = height / 2 + g.getFontMetrics().getHeight() / 4;
        for (int i = 0; i < codeLength; i++) {
            String rand = sRand.substring(i, i + 1);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6 + strWidth, strHeight);
        }
        //System.out.println(sRand);
        request.getSession().setAttribute(sessionKey, sRand);
        request.setAttribute("sRand", sRand);
        g.dispose();
        return image;
    }

    public static String randomResult(int length) {
        String[] i = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
        List<String> l = new ArrayList<String>();
        l.addAll(Arrays.asList(i));
        Random ran = new Random();
        String s = "";
        while (l.size() > 10 - length)
            s += l.remove(ran.nextInt(l.size()));
        s = s.replaceAll("^(0)(\\d)", "$2$1");
        return s;
    }

    // ������Χ��ȡ�����ɫ
    public Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if(fc > 255) {
            fc = 255;
        }
        if(bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);

    }

    private String randomRand(int n) {
        String rand = "";
        int len = randomString.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = ((Math.random())) * len;
            rand = rand + randomString.charAt((int) r);
        }
        return rand;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public String getRandomString() {
        return randomString;
    }

    public void setRandomString(String randomString) {
        this.randomString = randomString;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

}
