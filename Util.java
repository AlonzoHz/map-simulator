import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Util {
    public static BufferedImage loadImage(String name)
    {
        try {
            BufferedImage bi = ImageIO.read(ImageLoader.class.getResourceAsStream(name));
            
            System.out.println("Image loaded: " + bi);
            
            return bi;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static BufferedImage scaleImage(BufferedImage image, int factor) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage scaledImage = new BufferedImage(width * factor, height * factor, BufferedImage.TYPE_INT_RGB);
        scaledImage.getGraphics().drawImage(image, 0, 0, width * factor, height * factor, null);
        return scaledImage;
    }
}