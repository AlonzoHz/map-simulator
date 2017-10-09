import java.awt.Color;

public class Population extends WorldObject {
    double density;
    double averageAge = 30;

    int settleFactor = 800;
    
    boolean settled;

    WorldObject under;

    public Population(int x, int y, double density) {
        super(x, y);
        this.density = density;
        under = new WorldObject(x, y);
    }

    public Color getColor() {
        return new Color(250 - (int) density, 0, 0);
    }

    public void update(World world) {
        if (!settled) {
            int max = 0;
            int maxX = x;
            int maxY = y;
            WorldObject[] adjacent = world.getAdjacent(x, y);
            for (WorldObject obj: adjacent) {
                if (obj != null) {
                    int quality = settlementValue(world, obj.x, obj.y, 2);
                    if (quality * Math.random() > max) {
                        max = quality;
                        maxX = obj.x;
                        maxY = obj.y;
                    }
                    if (quality > settleFactor && !(obj instanceof Water)) {
                        settled = true;
                    }
                }
            }
            moveTo(world, maxX, maxY);
            settleFactor -= (int) Math.round(density);
            //todo: create settler class
            //todo: settle when been in an area for a long time
            //todo: emphasize exploration of new places vs going back
            //todo: remember best places visited
        } else {
            //improve growth curve
            double growthFactor = density / (2 + (((averageAge - 30) * (averageAge - 30))) + Math.random());
            averageAge = ((density * (averageAge + 1)) / (density + growthFactor));
            density = Math.min(density + growthFactor, 100);
        }
    }

    private int settlementValue(World world, int settleX, int settleY, int radius) {
        if (world.map[x][y] instanceof Water) {
            return 0;
        }

        int value = 0;
        for (int i = Math.max(0, settleX - radius); i < Math.min(world.width, settleX + radius); i++) {
            for (int j = Math.max(0, settleY - radius); j < Math.min(world.height, settleY + radius); j++) {
                WorldObject obj = world.map[i][j];
                if (obj instanceof Water) {
                    value += 100;
                } else if (obj instanceof Land) {
                    value += ((Land) obj).thickness / 2;
                } else if (obj instanceof Mountain) {
                    value += 20;
                }
                //todo: add value for interest in settling near other people
            }
        }
        return value;
    }

    private void moveTo(World world, int newX, int newY) {
        WorldObject newLocation = world.map[newX][newY];
        if (!(newLocation instanceof Water) || ((Water) newLocation).depth <= 20) {
            world.map[x][y] = under;
            x = newX;
            y = newY;
            under = world.map[x][y];
            world.map[x][y] = this;
        }
    }
}