package work.problem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class ConvertImg {

    // JGP格式
    public static final String JPG = "jpeg";
    // GIF格式
    public static final String GIF = "gif";
    // PNG格式
    public static final String PNG = "png";
    // BMP格式
    public static final String BMP = "bmp";
 
   
    public static void converter(String imgUrl,String format,File formatFile)
            throws IOException{
    	URL url = new URL(imgUrl);
    	InputStream input = url.openStream();
        System.out.println(url.getContent());
    	//File file = new File(imgUrl);
        BufferedImage bIMG =ImageIO.read(input);
        System.out.println(bIMG);
        ImageIO.write(bIMG, format, formatFile);
    }
 
 
   
    public static void main(String[] args) {
        try {
        	String url = "http://www.muu.com.cn//img/o/fd50992c78801d81b9bf0e832a73bfa3f53fb55a7e42c958ce97dc4170d8d26a5f0b62fea58d5c6a.jpg";
            String url2 = "http://www.muu.com.cn//img/o/fd50992c78801d81faac51f0ffadbadfb24a3a96a986f7b9e4cb5fce77c6af332b52aad68a8a018a.jpg";
        	//String url = "D:\\WorkData\\Coffee\\0.jpg";
        	// 转换为JGP
        	ConvertImg.converter(url2,GIF, new File("D:\\WorkData\\Coffee\\2.jpg"));
            /*// 转换为GIF
        	ConvertImg.converter(new File("c:\\psb.jpg"),GIF, new File("c:\\psb2.gif"));
            // 转换为PNG
        	ConvertImg.converter(new File("c:\\psb.jpg"),PNG, new File("c:\\psb2.png"));
            // 转换为BMP
        	ConvertImg.converter(new File("c:\\psb.jpg"),BMP, new File("c:\\psb2.bmp"));*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}