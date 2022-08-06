/**
 * Create a gray scale version of an image by setting all color components of each pixel to the same value.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    //I started with the image I wanted (inImage)
    public ImageResource makeGray(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //divide that sum by 3 (call it average)
            int avg = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            //set pixel's red to average
            pixel.setRed(avg);
            //set pixel's green to average
            pixel.setGreen(avg);
            //set pixel's blue to average
            pixel.setBlue(avg);
        }
        return outImage;
        //outImage is your answer
    }

    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            ImageResource inverted = makeInversion(inImage);
            
            String filename = inImage.getFileName();
            String grayName = "gray-" + filename;
            String invertedName = "inverted-" + filename;
            
            gray.setFileName(grayName);
            gray.draw();
            gray.save();
            
            inverted.setFileName(invertedName);
            inverted.draw();
            inverted.save();
        }
    }
    
    public ImageResource makeInversion(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //divide that sum by 3 (call it average)
            int avg = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            //set pixel's red to average
            pixel.setRed(255 - inPixel.getRed());
            //set pixel's green to average
            pixel.setGreen(255 - inPixel.getGreen());
            //set pixel's blue to average
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return outImage;
        //outImage is your answer
    }
}
