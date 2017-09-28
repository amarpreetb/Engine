package com.AMax.Engine.gfx;

public class Font {

    public static final Font STANDARD = new Font("Standard.png");

    private Image fontImage;
    private int[] offset;
    private int[] widths;

    public Font(String path){
        fontImage = new Image(path);

        offset = new int[59];
        widths = new int[59];

        int unicode = 0;

        for(int i = 0; i < fontImage.getWidth(); i++){
            if(fontImage.getPixel()[i] == 0xff0000ff){
                offset[unicode] = i;
            }

            if (fontImage.getPixel()[i] == 0xff0000ff) {
                    widths[unicode] = i - offset[unicode];
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

    public int[] getOffset() {
        return offset;
    }

    public void setOffset(int[] offset) {
        this.offset = offset;
    }

    public int[] getWidths() {
        return widths;
    }

    public void setWidths(int[] widths) {
        this.widths = widths;
    }
}
