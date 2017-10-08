import java.awt.image.BufferedImage;

public class World {

    WorldObject[][] map;
    int width;
    int height;

    public World(WorldObject[][] map) {
        this.map = map;
        width = map[0].length;
        height = map.length;
    }

    public BufferedImage constructImageFromWorld() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, map[x][y].getColor().getRGB());
            }
        }
        return image;
    }

    public WorldObject[] getAdjacent(int x, int y) {
        WorldObject[] objects = new WorldObject[4];
        if (x > 0) {
            objects[0] = map[x - 1][y];
        }
        if (x < width - 1) {
            objects[1] = map[x + 1][y];
        }
        if (y > 0) {
            objects[2] = map[x][y - 1];
        }
        if (y < height - 1) {
            objects[3] = map[x][y + 1];
        }
        return objects;
    }

    public static World constructWorldFromImage(BufferedImage worldImage) {
        int w = worldImage.getWidth();
        int h = worldImage.getHeight();
        WorldObject[][] worldMap = new WorldObject[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int color = worldImage.getRGB(x, y);
                int red = ((color & 0xFF0000) >> 16);
                int green = ((color & 0xFF00) >> 8);
                int blue = (color & 0xFF);
                
                if (red == 0 && green == 0 && blue != 0) {
                    worldMap[x][y] = new Water(x, y, 250 - blue);
                } else if (red == 0 && green != 0 && blue == 0) {
                    worldMap[x][y] = new Land(x, y, 250 - green);
                } else if (red != 0 && green == 0 && blue == 0) {
                    worldMap[x][y] = new Population(x, y, 250 - red);
                } else {
                    worldMap[x][y] = new Mountain(x, y, 250 - blue);
                }
            }
        }
        return new World(worldMap);
    }
}