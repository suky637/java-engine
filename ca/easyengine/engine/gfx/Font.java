package ca.easyengine.engine.gfx;

public class Font
{
    private Image fontImage;
    private int[] offsets;
    private int[] widths;

    public Font(String path, int charactersNum)
    {
        fontImage = new Image(path);

        offsets = new int[charactersNum];
        widths = new int[charactersNum];

        int unicode = 0;

        for (int i = 0; i < fontImage.getW(); i++)
        {
            if (fontImage.getP()[i] == 0xff0000ff)
            {
                offsets[unicode] = i;
            }
            if (fontImage.getP()[i] == 0xffffff00)
            {
                widths[unicode] = i - offsets[unicode];
                unicode++;
            }
        }
    }

    public Image getFontImage() {
        return fontImage;
    }

    public void setFontImage(Image fontImage) {
        this.fontImage = fontImage;
    }

    public int[] getOffsets() {
        return offsets;
    }

    public void setOffsets(int[] offsets) {
        this.offsets = offsets;
    }

    public int[] getWidths() {
        return widths;
    }

    public void setWidths(int[] widths) {
        this.widths = widths;
    }
}
