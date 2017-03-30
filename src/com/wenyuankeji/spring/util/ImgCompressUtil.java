package com.wenyuankeji.spring.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * @ClassName LoginIntercept
 * @Description 
 * @author lnn
 * @email lnnlsn@163.com
 * @date 2015-11-3 下午02:30:12
 */
@SuppressWarnings("restriction")
public class ImgCompressUtil {
	/*
	 * 使用spring value注解获取proper文件中的值时 需要在spring配置文件中加入<util:properties id="common"
	 * location="classpath:Common.properties" />
	 **/
	//保存路径
//	String img128=PropertiesUtils.getCommonString("img128");
//	String imgSaveUrl=PropertiesUtils.getCommonString("imgSaveUrl");
	//图显示路径
	String imgShowUrl=PropertiesUtils.getCommonString("ipHost");
	private String imgSuffix=".png";
	private Image img;
	private int height;
	private int width;
	public ImgCompressUtil(InputStream is) throws IOException {
		this.img = ImageIO.read(is);
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	/**
	 * 获取图片,等比
	 * @return 图片存放路径
	 * @throws IOException
	 */
	public void getImg(int w,int h,String imgSaveUrl) throws IOException {
		String num=getImgName();
//		String newImgSaveUrl = imgSaveUrl +"/"+ num + imgSuffix;
//		resizeFix(w, h, newImgSaveUrl);
		resizeFix(w, h, imgSaveUrl);
//		return imgShowUrl+num + imgSuffix;
	}
	/**
	 * 获取图片,自由
	 * @return 图片存放路径
	 * @throws IOException
	 */
	public void getImgFreedom(int w,int h,String imgSaveUrl) throws IOException {
		String num=getImgName();
//		String newImgSaveUrl = imgSaveUrl + num + imgSuffix;
//		resize(w, h, newImgSaveUrl);
		resize(w, h, imgSaveUrl);
//		return imgShowUrl+num + imgSuffix;
	}
	/**
	 * 按照宽度还是高度进行压缩
	 * @param w
	 *            int 最大宽度
	 * @param h
	 *            int 最大高度
	 */
	private void resizeFix(int w, int h, String newImgSaveUrl) throws IOException {
		if (width / height > w / h) {
			resizeByWidth(w, newImgSaveUrl);
		} else {
			resizeByHeight(h, newImgSaveUrl);
		}
	}
	/**
	 * 以宽度为基准，等比例放缩图片
	 * 
	 * @param w
	 *            int 新宽度
	 */
	public void resizeByWidth(int w, String newImgSaveUrl) throws IOException {
		 int h = (int) (height * w / width);  
		resize(w, h, newImgSaveUrl);
	}
	/**
	 * 以高度为基准，等比例缩放图片
	 * @param h int 新高度
	 * @param ImgSaveUrl 新图片存放路径
	 */
	public void resizeByHeight(int h, String newImgSaveUrl) throws IOException {
		 int w = (int) (width * h / height);  
		resize(w, h, newImgSaveUrl);
	}

	/**
	 * 强制压缩/放大图片到固定的大小
	 * 
	 * @param w
	 *            int 新宽度
	 * @param h
	 *            int 新高度
	 * @param newImgSaveUrl
	 *            新图片存放路径
	 */
	private void resize(int w, int h, String newImgSaveUrl) throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
        File destFile = new File(newImgSaveUrl); 
        File fileParent=new File(destFile.getParent());
        if (!fileParent.exists()) {
        	fileParent.mkdirs();
		}
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
        // 可以正常实现bmp、png、gif转jpg  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        encoder.encode(image); // JPEG编码  
        out.close();  
	}
	private static String getImgName() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + getRandom();
	}
	private static int getRandom() {
		Random random = new Random();
		return random.nextInt(89999) + 10000;
	}
}
