import java.awt.Color;

public class Land extends WorldObject {
    double thickness;
    private static final int YEAR_FACTOR = 1;

    public Land(int x, int y, double thickness) {
        super(x, y);
        this.thickness = thickness;
    }

    public Color getColor() {
        return new Color(0, (int) (250 - Math.round(thickness)), 0);
        //return new Color(0, (int) (250 - 2.5 * Math.round(thickness)), 0); //Poison
    }

    //todo: think about how edges are effected by having a 0 neighbor
    public void update(World world) {
        Double random = Math.random();
        Double factor = thickness * (100.0 - thickness);
        WorldObject[] adjacents = world.getAdjacent(x, y);
        for (WorldObject object: adjacents) {
            if (object instanceof Land) {
                Land adjLand = (Land) object;
                factor += (adjLand.thickness * 4);
            }
        }
        factor /= (5000.0 * YEAR_FACTOR);
        thickness += factor * Math.max(random, 0.5);
        thickness = Math.min(thickness, 100);
    }

    public int getCost() {
        return (int) thickness;
    }
}