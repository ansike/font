package com.fd.font.cloud.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangjian on 2016/11/18.
 */
public class ImgUtils {
	public static class PictureInfo{
		private Integer width;
		private Integer height;
		private String picUrl;
		private String picUri;
		public Integer getWidth() {
			return width;
		}
		public void setWidth(Integer width) {
			this.width = width;
		}
		public Integer getHeight() {
			return height;
		}
		public void setHeight(Integer height) {
			this.height = height;
		}
		public String getPicUrl() {
			return picUrl;
		}
		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}
		public String getPicUri() {
			return picUri;
		}
		public void setPicUri(String picUri) {
			this.picUri = picUri;
		}
		
	}
	private static Logger log = LoggerFactory.getLogger(ImgUtils.class);
	private static String domain=PropertiesUtil.getContextProperty("upload.path.prefix");
	private static String outPath=PropertiesUtil.getContextProperty("upload.path");
	private static String ttfPath=PropertiesUtil.getContextProperty("ttf.path");
	/**
	 * 生成字体图片
	 * 
	 * @param fontPath
	 * @param kindPath
	 * @param outPath
	 * @return
	 * @throws IOException
	 * @throws FontFormatException
	 */
	public static PictureInfo createFontPic(String fontPath,String kindPath, String name) {
		ImgUtils.PictureInfo pic=new ImgUtils.PictureInfo();
		try {
			String uri=kindPath+name+".png";
			InputStream is = new FileInputStream(new File(ttfPath+fontPath));
			Font font = Font.createFont(java.awt.Font.TRUETYPE_FONT, is);
			File out = new File(outPath+uri);
			BufferedImage img=null;
			if (out.isFile()) {
				img=ImageIO.read(out);
				pic.setWidth(img.getWidth());
				pic.setHeight(img.getHeight());
				pic.setPicUri(uri);
				pic.setPicUrl(domain+uri);
				return pic;
			}
			if (!out.getParentFile().exists()) {
				out.getParentFile().mkdirs();
			}
			img=createFrontFontPic(font, name);
			pic.setWidth(img.getWidth());
			pic.setHeight(img.getHeight());
			pic.setPicUri(uri);
			pic.setPicUrl(domain+uri);
			ImageIO.write(img, "png", new File(outPath+uri));
			log.info("生成应用图片:" + name + "  " + outPath+uri);
			return pic;
		} catch (Exception e) {
			e.printStackTrace();
			return pic;
		}
	}

	public static BufferedImage createFrontFontPic(java.awt.Font font, String words) throws IOException, FontFormatException {
		BufferedImage image = null;
		float fontBigSize = 46f;
		font = font.deriveFont(fontBigSize);
		FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font);
		image = new BufferedImage(fm.stringWidth(words), 90, BufferedImage.TYPE_3BYTE_BGR);

		// 图片：宽950 高450 字体字号：汉 42px 英26
		Graphics2D g = image.createGraphics();
		font = font.deriveFont(fontBigSize);
		image = g.getDeviceConfiguration().createCompatibleImage(fm.stringWidth(words), 90, Transparency.TRANSLUCENT);
		g.dispose();
		g = image.createGraphics();
		g.setColor(Color.black);// 在换成黑色
		int y = (image.getHeight() / 2 + 15);
		font = font.deriveFont(fontBigSize);
		int fontSize = (int) fontBigSize;// 其他字体大小
		Font kaiti = new Font("宋体", Font.PLAIN, fontSize);
		kaiti = kaiti.deriveFont(fontBigSize);
		int startIndex = 0;
		for (int i = 0; i < words.length(); i++) {
			char c = words.charAt(i);
			if (font.canDisplay(words.charAt(i))) {
				g.setFont(font);// 设置画笔字体
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.drawString(words.charAt(i) + "", startIndex, y);// 画出字符串
			} else {
				g.setFont(kaiti);// 设置画笔字体
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.drawString(words.charAt(i) + "", startIndex, y);// 画出字符串
			}
			startIndex += fm.charWidth(c);
		}
		g.dispose();
		return image;
	}

	public static void main(String args[]) throws IOException, FontFormatException {
		InputStream is = new FileInputStream(new File("E:\\home\\download\\e5495e7957579ebe015757a3a0de00bb\\aimabangbangti.ttf"));
		Font font = Font.createFont(java.awt.Font.TRUETYPE_FONT, is);
		// Long start=System.currentTimeMillis();
		// for(int i=0;i<100;i++){
		// Long s=System.currentTimeMillis();
		ImageIO.write(createFrontFontPic(font, "歰来已豳躔5_6_7"), "png", new File("C:\\Users\\zengxiaowei\\Desktop\\123.png"));
		// System.out.println(System.currentTimeMillis()-s);
		// }
		// System.out.println(System.currentTimeMillis()-start);
	}

}
