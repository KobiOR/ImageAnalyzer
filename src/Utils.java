package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by orrko_000 on 16/06/2017.
 */
 class Point{
    public int width;
    public int height;
    public boolean checked=false;
    Point(int width,int height){
        this.width=width;
        this.height=height;

    }

}
public final class Utils {

    public Utils() {
    }

    public enum T_L_COLOR {
        RED,
        GREEN,
        BLACK;

    };

    float CONSTANT_DEVIATION=0.00001f;
    private final float[] ESTIMATED_BLACK={0,0,0};
    private final  float[] ESTIMATED_GREEN={0,255,0};
    private final  float[] ESTIMATED_RED={255,0,0};
    private final int MIN=0;
    private final int MAX=255;
    private int boundaryWidth;
    private int boundaryHeight;
    private byte[][] matrix;

    public static  void Print(String s) {
      System.out.println(s);
    }
    /*
        get some pixel and then recognize which color is it ;black,green or red.
        then use fixer to update the new ESTIMATED_COLOR
        also print the new updated color.
     */
    public  void improveEstimatedColor(float[] estimatedColor,T_L_COLOR TLColorValue){
            switch (TLColorValue){
                case BLACK:{
                    for (int i = 0; i <3 ; i++) {
                        float temp= (float) (ESTIMATED_BLACK[i]-estimatedColor[i]);
                        fixer(ESTIMATED_BLACK,i,temp>0);
                    }
                    Print("New Black Values: "+ Arrays.toString(ESTIMATED_BLACK));
                    break;
                }
                case GREEN:{
                    for (int i = 0; i <3 ; i++) {
                        float temp= (float) (ESTIMATED_GREEN[i]-estimatedColor[i]);
                        fixer(ESTIMATED_GREEN,i,temp>0);
                    }
                    Print("New Green Values: "+ Arrays.toString(ESTIMATED_GREEN));
                    break;
                }
                case RED:{
                    for (int i = 0; i <3 ; i++) {
                        float temp= (float) (ESTIMATED_RED[i]-estimatedColor[i]);
                        fixer(ESTIMATED_RED,i,temp>0);
                    }
                    Print("New Red Values: "+ Arrays.toString(ESTIMATED_GREEN));
                    break;
                }

                default:
                    Print("Bad Enum");
            }


    }
    /*
        get ONE value by array and specific index and boolean sign.
         the sign determined by check if one parameter of the pixel is greater then the estimated

 */
    private final void fixer(float[] color,int index,boolean sign){
        float signer;
        if (sign)signer=-1;
        else signer=1;
        color[index]= (float) (color[index]+signer*(((float)color[index])*CONSTANT_DEVIATION));


    }
    /*
    get pixel and decide if is a suspicious as a red green or blue
 */
    public boolean pixelDetective(int pixel){

        return false;
    }

    //TODO change to private
    public float getProportion(float[] pixel,T_L_COLOR TLColor){
        switch (TLColor){
            case BLACK: return getPercentageDifference(ESTIMATED_BLACK,pixel);
            case GREEN: return getPercentageDifference(ESTIMATED_GREEN,pixel);
            case RED: return getPercentageDifference(ESTIMATED_RED,pixel);
            default:Print("Bad Color");return 0;
        }



    }
    //http://www.mathsisfun.com/data/percentage-difference-vs-error.html
    private float getPercentageDifference(float[] origin,float[] approx){
        float a=getDifference(origin[0],approx[0]);
        float b=getDifference(origin[1],approx[1]);
        float c=getDifference(origin[2],approx[2]);
        float d=(origin[0]+origin[1]+origin[2]+approx[0]+approx[1]+approx[2])/3;

        if (d!=0) {
            float t = Math.abs((a + b + c) / d);
            float percentageT=t*100;
            if (percentageT>100)
                return 100;
            return t*100;
        }
        else
            return 0;

    }
    private float getDifference(float a,float b){
        float x = a-b;
        float y =b-a;
        float normalizeNumber=(x*y-MIN)/(MAX-MIN);//Normalize value to range 0-255
        return Math.abs(normalizeNumber);
    }
    public T_L_COLOR checkRecursive(Point minimumPoint, ArrayList<Point> points, BufferedImage filteringImage){
        boundaryWidth=filteringImage.getWidth();
        boundaryHeight = filteringImage.getHeight();
        matrix=new byte[boundaryWidth][boundaryHeight];
        ArrayList<Point> suspiciousPoints=  findSuspiciousPoints(filteringImage);
        for (Point point:suspiciousPoints){

        }
        return T_L_COLOR.BLACK;
    }
    private void recursive(Point p,byte[][] matrix){

        Thread t1 = new Thread(new Runnable() {
            public void run()
            {
                // code goes here.
            }});
        t1.start();






    }
    private ArrayList findSuspiciousPoints(BufferedImage image){
        ArrayList<Point> points=new ArrayList<>();
        for (int i = 0; i <image.getWidth() ; i++)
            for (int j = 0; j <image.getHeight() ; j++)
                if(new Color(image.getRGB(i,j)).getRed()==new Color(255,255,255)){
                    image.setRGB(i,j,16711680);
                    points.add(new Point(i,j));
                        break;
              }

        for (int i = image.getWidth()-1; i>=0 ; i--)
            for (int j = image.getHeight()-1; j >=0 ; j--)
                if(new Color(image.getRGB(i,j))==new Color(255,255,255)){
                    image.setRGB(i,j,16711680);
                    points.add(new Point(i,j));
                    break;
                }
        File outputfile = new File("d.jpg");
        try {
            ImageIO.write(image, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return points;
    }



    boolean pixelsCompare(int redA,ing)
}
