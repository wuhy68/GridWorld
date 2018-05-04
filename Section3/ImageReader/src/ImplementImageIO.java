import imagereader.IImageIO;
import imagereader.IImageProcessor;
import imagereader.Runner;
import java.awt.Image;
import java.io.*;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Color.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImplementImageIO implements IImageIO {
  /* 利用二进制流读取Bitmap位图文件 */
  public Image myRead(String filePath) throws IOException {
    Image image;

    try {
    FileInputStream inputStream = new FileInputStream(filePath);

    inputStream.skip(18);

    /* 创建4个byte用于存放位图宽度 */
    byte imagewidth[] = new byte[4];
    inputStream.read(imagewidth, 0, 4);
    int width = ByteArrayToInt(imagewidth);
    //System.out.println("Width: " + width);

    /* 创建4个byte用于存放位图高度*/
    byte imageheight[] = new byte[4];
    inputStream.read(imageheight, 0, 4);
    int height = ByteArrayToInt(imageheight);
    //System.out.println("Height: " + height);
    
    inputStream.skip(2);
    byte bits[] = new byte[2];
    inputStream.read(bits, 0, 2);
    int bitnumber = ByteArrayToInt(bits);

    inputStream.skip(4);

    /* 获取位图数据大小 */
    byte imagesize[] = new byte[4];
    inputStream.read(imagesize, 0, 4);
    int size = ByteArrayToInt(imagesize);

    //System.out.println("Size: " + size);

    inputStream.skip(16);

    // used to store pixel
    int data[] = new int[height*width];
    
    // Calculate the sum of bits, if it is the multiply of 4, that no skip
    int npad = (bitnumber * width / 8) % 4;
   
    
    if (npad != 0) {
      npad = 4 - npad;
    }


    for (int i = height - 1; i >= 0; i--) {
      for (int j = 0; j < width; j++) {
        byte[] temp = new byte[1];
        inputStream.read(temp,0,1);
        int blue = ByteArrayToInt(temp);
        //System.out.println("blue: " + blue);

        inputStream.read(temp,0,1);
        int green = ByteArrayToInt(temp);
        //System.out.println("green: " + green);

        inputStream.read(temp, 0 ,1);
        int red = ByteArrayToInt(temp);
        //System.out.println("red: " + red);

        Color c = new Color(red, green, blue);
        data[i*width + j] = c.getRGB();
      }
      /* check if a skip is needed */
      if (npad != 0) {
        inputStream.skip(npad);
      }
    }

    image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, data, 0, width));

    // close inputstream
    inputStream.close();
    return image;
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  /* 把处理完的图像保存为bmp格式图像 */
  public Image myWrite(Image image, String filePath) {
    try {
      BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

      // print the image into bimage
      bimage.getGraphics().drawImage(image, 0,0,null);

      // Save the picture as .png type
      File file = new File(filePath + ".bmp");

      // write the bimage into file
      ImageIO.write(bimage, "bmp", file);
      return image;
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  //将低字节在前,高字节在后的byte数组转为int
  private int ByteArrayToInt(byte[] bArr) {    
    if(bArr.length == 1) {
      return (int) ((int)((bArr[0] & 0xff) << 0));
    }    
    else if (bArr.length == 2) {
      return (int) (((int)((bArr[1] & 0xff) << 8)  
                 | (int)((bArr[0] & 0xff) << 0)));     
    }
    else if (bArr.length == 3) {
      return (int) (((int)((bArr[2] & 0xff) << 16)      
                 | (int)((bArr[1] & 0xff) << 8)  
                 | (int)((bArr[0] & 0xff) << 0)));     
    }
    else {
      return (int) (((int)((bArr[3] & 0xff) << 24)      
                 | (int)((bArr[2] & 0xff) << 16)      
                 | (int)((bArr[1] & 0xff) << 8)  
                 | (int)((bArr[0] & 0xff) << 0)));     
    }   
  }
}