package com.moment.common.util;

import java.io.*;
import java.util.Iterator;
import java.awt.*;  
import java.awt.image.*;  
import java.awt.Graphics;  
import java.awt.color.ColorSpace;  
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.web.multipart.MultipartFile;  

public class PicCropper {  

   //裁剪图片
    public static MultipartFile cut(MultipartFile file,int x, int y, int destWidth,   int destHeight) throws IOException {  
    	InputStream is = null;  
        ImageInputStream iis = null;  
        if(x<0){
        	x=0;
        }else if(y<0){
        	y=0;
        }
        try {  
            // 读取图片文件  
	            is = file.getInputStream();
  
            /** 
             *  
             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 
             *  
             * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 . 
             *  
             * (例如 "jpeg" 或 "tiff")等 。 
             * 
             */  
	        System.out.println( file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')+1));
            Iterator<ImageReader> it = ImageIO  
                    .getImageReadersByFormatName( file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')+1));  
            
            ImageReader reader = it.next();  
  
            // 获取图片流  
            iis = ImageIO.createImageInputStream(is);  
            System.out.println(iis.length());
            /** 
             *  
             * <p> 
             * iis:读取源。true:只向前搜索 
             * </p> 
             * .将它标记为 ‘只向前搜索’。 
             *  
             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader 
             *  
             * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。 
             */  
            reader.setInput(iis, true);  
  
            /** 
             *  
             * <p> 
             * 描述如何对流进行解码的类 
             * <p> 
             * .用于指定如何在输入时从 Java Image I/O 
             *  
             * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 
             *  
             * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回 
             *  
             * ImageReadParam 的实例。 
             */  
            ImageReadParam param = reader.getDefaultReadParam();  
  
            /** 
             *  
             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象 
             *  
             * 的左上顶点的坐标(x，y)、宽度和高度可以定义这个区域。 
             */  
            Rectangle rect = new Rectangle(x, y, destWidth,destHeight);  
  
            // 提供一个 BufferedImage，将其用作解码像素数据的目标。  
            param.setSourceRegion(rect);  
  
            /** 
             *  
             * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 
             *  
             * 它作为一个完整的 BufferedImage 返回。 
             */  
            BufferedImage bi = reader.read(0, param); 
            System.out.println(bi.getWidth());
            File outputfile=new File("h:/imgupload/a.jpg");
            // 保存新图片  
            ImageIO.write(bi,file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')+1),outputfile);  
            
            return File2MultipartFile.convert2Multi(outputfile);
        } finally {  
            if (is != null)  
                is.close();  
            if (iis != null)  
                iis.close();  
        }  
  
    }  
    /** 
     * 缩放图像 
     *  
     * @param srcImageFile       源图像文件地址 
     * @param result             缩放后的图像地址 
     * @param scale              缩放比例 
     * @param flag               缩放选择:true 放大; false 缩小; 
     */  
    public static void scale(String srcImageFile, String result, int scale,  
            boolean flag) {  
        try {  
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件  
            int width = src.getWidth(); // 得到源图宽  
            int height = src.getHeight(); // 得到源图长  
            if (flag) {  
                // 放大  
                width = width * scale;  
                height = height * scale;  
            } else {  
                // 缩小  
                width = width / scale;  
                height = height / scale;  
            }  
            Image image = src.getScaledInstance(width, height,Image.SCALE_DEFAULT);  
            BufferedImage tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
            Graphics g = tag.getGraphics();  
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
            g.dispose();  
            ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  

    /** 
     * 重新生成按指定宽度和高度的图像 
     * @param srcImageFile       源图像文件地址 
     * @param result             新的图像地址 
     * @param _width             设置新的图像宽度 
     * @param _height            设置新的图像高度 
     */  
    public static void scale(String srcImageFile, String result, int _width,int _height) {        
        scale(srcImageFile,result,_width,_height,0,0);  
    }  

    public static void scale(String srcImageFile, String result, int _width,int _height,int x,int y) {  
        try {  

            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件  

            int width = src.getWidth(); // 得到源图宽  
            int height = src.getHeight(); // 得到源图长  

            if (width > _width) {  
                 width = _width;  
            }  
            if (height > _height) {  
                height = _height;  
            }             
            Image image = src.getScaledInstance(width, height,Image.SCALE_DEFAULT);  
            BufferedImage tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
            Graphics g = tag.getGraphics();  
            g.drawImage(image, x, y, null); // 绘制缩小后的图  
            g.dispose();              
            ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  

    /** 
     * 图像类型转换 GIF->JPG GIF->PNG PNG->JPG PNG->GIF(X) 
     */  
    public static void convert(String source, String result) {  
        try {  
            File f = new File(source);  
            f.canRead();  
            f.canWrite();  
            BufferedImage src = ImageIO.read(f);  
            ImageIO.write(src, "JPG", new File(result));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  

    /** 
     * 彩色转为黑白 
     *  
     * @param source 
     * @param result 
     */  
    public static void gray(String source, String result) {  
        try {  
            BufferedImage src = ImageIO.read(new File(source));  
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);  
            ColorConvertOp op = new ColorConvertOp(cs, null);  
            src = op.filter(src, null);  
            ImageIO.write(src, "JPEG", new File(result));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }     
}  