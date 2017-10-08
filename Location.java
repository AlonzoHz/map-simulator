public class Location {
    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Location other) {
        return x == other.x && y == other.y;
    }
}