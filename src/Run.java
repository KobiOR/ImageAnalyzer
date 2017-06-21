/**
 * Created by orrko_000 on 16/06/2017.
 */

package src;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Run {

    static ArrayList<Point> points=new ArrayList();
    static Utils util = new Utils();
    static Point minPoint=new Point(0,0);

    public static void main(String[] args) throws Exception {

    //    Print(String.valueOf(util.getProportion(new float[]{1, 1, 1}, Utils.T_L_COLOR.BLACK)));

        final File dir = new File("green.jpg");
        BufferedImage image = ImageIO.read(dir);
        imageAnalyze(image);

    }
    static void imageAnalyze(BufferedImage image) {
        BufferedImage img=new BufferedImage(image.getWidth(),image.getHeight(),image.getType());
        final int width = image.getWidth();
        final int height = image.getHeight();
        for (int i = 0; i <width ; i++)
            for (int j = 0; j <height ; j++)
                img.setRGB(i,j,16777215);

        float []minimum={100,0,0};
        for (int i = 0; i < width; i += 2) {
            for (int j = 0; j < height; j += 2) {
                int pixel = image.getRGB(i, j);
                float chance=checkPixel(pixel);
                if (chance<50) {
                    img.setRGB(i, j, pixel);
                    points.add(new Point(i,j));
                }
                if (chance<minimum[0]) {
                    minPoint.height=j;
                    minPoint.width=i;
                    minimum[0] = chance;
                    minimum[1]=i;
                    minimum[2]=j;
                }
            }

            Utils.T_L_COLOR result=util.checkRecursive(minPoint,points,img);
        }
        System.out.println(minimum[0]);
        System.out.println(minimum[1]);
        System.out.println(minimum[2]);
//        File outputfile = new File("d.jpg");
//        try {
//            ImageIO.write(img, "jpg", outputfile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
    static float checkPixel(int pixel) {
        float alpha = (pixel >> 24) & 0xff;
        float red = (pixel >> 16) & 0xff;
        float green = (pixel >> 8) & 0xff;
        float blue = (pixel) & 0xff;

        float redChance=util.getProportion(new float[]{red,green,blue}, Utils.T_L_COLOR.RED);
        float greenChance=util.getProportion(new float[]{red,green,blue}, Utils.T_L_COLOR.GREEN);
        return Math.min(redChance,greenChance);
    }

}