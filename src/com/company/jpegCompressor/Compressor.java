package com.company.jpegCompressor;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.File;
import java.io.IOException;

public class Compressor {

    public static void main(String[] args) {
        File originalImage = new File("C:\\Users\\a.dachkinova\\Desktop\\four.jpg");
        File compressedImage = new File("C:\\Users\\a.dachkinova\\Desktop\\compressedImage.jpg");
	    try {
            compressJpegImage(originalImage, compressedImage, 0.7f);
            System.out.println("Done!");
        }
        catch(IOException e){

        }
    }

    public static void  compressJpegImage(File originalImage, File compressedImage, float compressionQuality) throws IOException {
        RenderedImage image = ImageIO.read(originalImage);
        ImageWriter jpegWriter = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam jpegWriteParam = jpegWriter.getDefaultWriteParam();
        jpegWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpegWriteParam.setCompressionQuality(compressionQuality);

        try(ImageOutputStream outputStream = ImageIO.createImageOutputStream(compressedImage)) {
            jpegWriter.setOutput(outputStream);
            IIOImage outputImage = new IIOImage(image, null, null);
            jpegWriter.write(null, outputImage, jpegWriteParam);
        }

        jpegWriter.dispose();
    }
}
