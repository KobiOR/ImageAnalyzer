/**
 * Created by orrko_000 on 16/06/2017.
 */

package src;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Run implements Observer {

    private static ArrayList<Point> points=new ArrayList();
    private static Utils util = new Utils();
    private static Point minPoint=new Point(0,0);

    private void start(){
        ImageReader reader = new ImageReader();
        reader.addObserver(this);
        reader.run();
    }
    private void imageAnalyze(BufferedImage image) {
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
                if (chance<70) {
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

            //Utils.T_L_COLOR result=util.checkRecursive(minPoint,points,img);
        }
        System.out.println(minimum[0]);
        System.out.println(minimum[1]);
        System.out.println(minimum[2]);
        File outputfile = new File("red.jpg");
        try {
            ImageIO.write(img, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private float checkPixel(int pixel) {
        float alpha = (pixel >> 24) & 0xff;
        float red = (pixel >> 16) & 0xff;
        float green = (pixel >> 8) & 0xff;
        float blue = (pixel) & 0xff;

        float redChance=util.getProportion(new float[]{red,green,blue}, Utils.T_L_COLOR.RED);
        float greenChance=util.getProportion(new float[]{red,green,blue}, Utils.T_L_COLOR.GREEN);
        //float blackChance=util.getProportion(new float[]{red,green,blue}, Utils.T_L_COLOR.BLACK);

        return Math.min(redChance,greenChance);
    }
    public static void main(String[] args){
        Run programm = new Run();
        programm.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        ImagePresentation IP= (ImagePresentation) arg;
        BufferedImage image = IP.bufferedImage;
        File f=IP.imageFile;

        this.imageAnalyze(image);

        delete(f);
    }
    private static void delete(File f) {
        try {


            if (f.delete()) {
            } else {
                System.out.println("Delete operation is failed.");
            }

        } catch (Exception e) {

            e.printStackTrace();


        }
    }

}


