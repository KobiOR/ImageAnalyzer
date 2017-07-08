package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;

/**
 * Created by orrko_000 on 08/07/2017.
 */
public class ImagePresentation {
    BufferedImage bufferedImage;
    File imageFile;

    ImagePresentation(BufferedImage bufferedImage, File imageFile){

        this.imageFile=imageFile;
        this.bufferedImage=bufferedImage;
    }
}
