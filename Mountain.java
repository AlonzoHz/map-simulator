import java.awt.Color;

public class Mountain extends WorldObject {
    int height;

    public Mountain(int x, int y, int height) {
        super(x, y);
        this.height = height;
    }

    public Color getColor() {
        return new Color(250 - height, 250 - height, 250 - height);
    }

    public int getCost() {
        return height * 4 + 100;
    }
}