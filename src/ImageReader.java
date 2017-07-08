package src;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Observable;

/**
 * Created by orrko_000 on 08/07/2017.
 */
public class ImageReader extends Observable implements Runnable {
    static final File dir = new File("C:\\Users\\orrko_000\\Desktop\\inputFolder");
    static final String[] EXTENSIONS = new String[]{"gif", "png", "bmp","jpeg","jpg","JPG","PNG" };

    @Override
    public void run() {


        Thread t = new Thread(() -> {
            while (true) {
                if (dir.isDirectory()) {
                    for (final File f : dir.listFiles((IMAGE_FILTER) )) {
                        BufferedImage img = null;
                        try {
                            img = ImageIO.read(f);
                            setChanged();
                            notifyObservers(new ImagePresentation(img,f));

                        } catch(IOException e) {
                            e.printStackTrace();
                        }


                    }
                }
            }
        });

        t.start();

    }
    public static void delete(File f) {
        try {


            if (f.delete()) {
            } else {
                System.out.println("Delete operation is failed.");
            }

        } catch (Exception e) {

            e.printStackTrace();


        }
    }
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter(){
        @Override
        public boolean accept(final File dir, final String name)
        {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
}
