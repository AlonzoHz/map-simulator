import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MapMaker {
    private BufferedImage image;
    World world;
    JLabel label;

    public MapMaker() {
        image = Util.loadImage("map.png");
        world = World.constructWorldFromImage(image);
        //simulate(365);
        image = world.constructImageFromWorld();
        image = Util.scaleImage(image, 8);
        ImageIcon icon = new ImageIcon(image);
        JFrame frame = new JFrame();
        //frame.setSize(200, 300);
        label = new JLabel();
        //label.setMinimumSize(new java.awt.Dimension(320, 320));
        label.setIcon(icon);
        frame.add(label);
        frame.pack();
        frame.setVisible(true);
        //frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simulateWithVisual(10000, 100);
    }

    public static void main(String[] args) {
        new MapMaker();
    }

    public void simulate(int years) {
        for (int i = 0; i < years; i++) {
            for (int x = 0; x < world.width; x++) {
                for (int y = 0; y < world.height; y++) {
                    world.map[x][y].update(world);
                }
            }
        }
    }

    public void simulateWithVisual(int years, int wait) {
        for (int i = 0; i < years; i++) {
            for (int x = 0; x < world.width; x++) {
                for (int y = 0; y < world.height; y++) {
                    world.map[x][y].update(world);
                }
            }
            BufferedImage image = Util.scaleImage(world.constructImageFromWorld(), 8);
            image.getGraphics().drawString(Integer.toString(i), 10, 10);
            label.setIcon(new ImageIcon(image));
            try {
                Thread.sleep(wait);
            } catch(Exception e) {
            }
        }
    }
}