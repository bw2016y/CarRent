package org.teamwe.carrent.controller.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author FDws
 * Created on 2018/6/13 23:11
 */

public class VerifyCodeImage {
    public static String NAME = VerifyCodeImage.class.getName();
    public static String TIME = VerifyCodeImage.class.getName() + "-Time";
    private static final char[] chars = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final Random random = new Random();

    private static String generateVerityCode(int verifySize) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < verifySize; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        return sb.toString();
    }

    public static String outputVerifyImage(int w, int h, OutputStream os, int verifySize) throws IOException {
        String verifyCode = generateVerityCode(verifySize);
        outputImage(w, h, os, verifyCode);
        return verifyCode;
    }

    private static void outputImage(int w, int h, OutputStream os, String code) throws IOException {
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[]{
                Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA,
                Color.ORANGE, Color.PINK, Color.YELLOW
        };
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
        }
        graphics2D.setColor(Color.GRAY);
        graphics2D.fillRect(0, 0, w, h);

        Color c = getRandColor(200, 255);
        graphics2D.setColor(c);
        graphics2D.fillRect(0, 2, w, h - 4);


        for (int i = 0; i < 20; i++) {
            graphics2D.setColor(getRandColor(0, 200));
            int x = rand.nextInt(w - 1);
            int y = rand.nextInt(h - 1);
            int x1 = rand.nextInt(w) + 10;
            int y1 = rand.nextInt(w) + 10;
            graphics2D.drawLine(x, y, x1, y1);
        }

        float noiseRate = 0.1f;
        int area = (int) (noiseRate * w * h);
        for (int i = 0; i < area; i++) {
            int x = rand.nextInt(w);
            int y = rand.nextInt(h);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }
        shear(graphics2D, w, h, c);

        int fontSize = h - 4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        graphics2D.setFont(font);
        char[] ch = code.toCharArray();
        for (int i = 0; i < verifySize; i++) {
            graphics2D.setColor(getRandColor(100, 160));
            AffineTransform aff = new AffineTransform();
            aff.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
                    (w / verifySize) * i + fontSize / 2, h / 2);
            graphics2D.drawChars(ch, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + fontSize / 2 - 10);
        }

        graphics2D.dispose();
        ImageIO.write(image, "jpg", os);
    }

    private static Color getRandColor(int fc, int bc) {
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {
        int period = random.nextInt(2);
        int frames = 1;
        int phase = random.nextInt(2);
        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1) * Math.sin((double) i / (double) period
                    + (Math.PI * 2 * (double) phase) / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            g.setColor(color);
            g.drawLine((int) d, i, 0, i);
            g.drawLine((int) d + w1, i, w1, i);
        }
    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {
        int period = random.nextInt(40) + 10;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1) * Math.sin((double) i / (double) period
                    + (Math.PI * 2 * (double) phase) / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            g.setColor(color);
            g.drawLine(i, (int) d, i, 0);
            g.drawLine(i, (int) d + h1, i, h1);
        }
    }
}

