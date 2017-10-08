import java.awt.Color;

public class WorldObject {
    int x;
    int y;

    public WorldObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(World world) {
    }

    public Color getColor() {
        return new Color(x, y, 0);
    }

    public int getCost() {
        return Integer.MAX_VALUE;
    }
}