package src;

import java.util.Arrays;

/**
 * Created by orrko_000 on 16/06/2017.
 */
public final class Utils {

    public enum COLOR{
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

    public static  void Print(String s) {
      System.out.println(s);
    }
    /*
        get some pixel and then recognize which color is it ;black,green or red.
        then use fixer to update the new ESTIMATED_COLOR
        also print the new updated color.
     */
    public  void improveEstimatedColor(float[] estimatedColor,COLOR colorValue){
            switch (colorValue){
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
    public float getProportion(float[] pixel,COLOR color){
        switch (color){
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
            return t*100;
        }
        else
            return 0;

    }
    private float getDifference(float a,float b){
        float x = a-b;
        float y =b-a;
        float normalizeNUmber=(x*y-MIN)/(MAX-MIN);
        return Math.abs(normalizeNUmber);
    }
}
