import java.awt.Color;

public class Water extends WorldObject {
    int depth;

    public Water(int x, int y, int depth) {
        super(x, y);
        this.depth = depth;
    }

    public Color getColor() {
        return new Color(0, 0, 250 - depth);
    }

    public int getCost() {
        if (depth > 20) {
            return super.getCost();
        }
        return depth * 2;
    }
}