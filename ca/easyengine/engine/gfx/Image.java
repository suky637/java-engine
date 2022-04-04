package ca.easyengine.engine.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Image {
    private int w, h;
    private int[] p;
    private boolean alpha = false;

    public Image(String path)
    {
        BufferedImage image = null;

        try {
            File f = Paths.get(path).toFile();
            image = ImageIO.read(f);
        }catch (IOException e)
        {
            System.err.println("Can't load image");
        }
        w = image.getWidth();
        h = image.getHeight();
        p = image.getRGB(0, 0, w, h, null, 0, w);

        image.flush();
    }

    public Image(int[] p, int w, int h)
    {
        this.p = p;
        this.w = w;
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int[] getP() {
        return p;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setP(int[] p) {
        this.p = p;
    }

    public boolean isAlpha() {
        return alpha;
    }

    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }
}
