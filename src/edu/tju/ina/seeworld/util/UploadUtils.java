package edu.tju.ina.seeworld.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.thebuzzmedia.imgscalr.Scalr;

public class UploadUtils {
	private static final Logger log = Logger.getLogger(UploadUtils.class);

	/**
	 * 上传照片的工具类
	 * 
	 * @param file
	 * @param savePath
	 * @param photoName
	 * @return
	 */
	public static String uploadPhoto(File file, String savePath,
			String photoName) throws IOException {
		String extName = photoName.substring(photoName.lastIndexOf('.') + 1);
		String newfilename = "" + System.nanoTime() + "." + extName;
		String address = savePath + File.separator + newfilename;
		if (log.isDebugEnabled()) {
			log.debug("save :" + photoName + " to " + address);
		}
		File target = new File(address);
		FileUtils.copyFile(file, target);
		return newfilename;
	}

	public static String uploadPoster(File file, String savePath,
			String photoName) throws IOException {
		String fileType = "jpg";

		String newfilename = "" + System.nanoTime();
		// System.out.println(newfilename);
		String address = savePath + File.separator + newfilename;
		if (log.isDebugEnabled()) {
			log.debug("save :" + photoName + " to " + address);
		}
		File target = new File(address + '.' + fileType);
		File rawFile = new File(address + "_raw." + fileType);
		// 原始图象
		FileUtils.copyFile(file, rawFile);
		// 压缩后
		BufferedImage bi = ImageIO.read(file);
		BufferedImage scaledImage = Scalr.resize(bi, Scalr.Method.BALANCED,
				Constant.AVATAR_WIDTH, Constant.AVATAR_HEIGHT);
		ImageIO.write(scaledImage, fileType, new FileOutputStream(target));

		return newfilename;
	}
	
	public static String uploadPosterInMainpage(File file, String savePath,
			String photoName) throws IOException {
		String fileType = "jpg";

		String newfilename = "" + System.nanoTime() + "." + fileType;
		// System.out.println(newfilename);
		String address = savePath + File.separator + newfilename;
		if (log.isDebugEnabled()) {
			log.debug("save :" + photoName + " to " + address);
		}
		File target = new File(address);
		// 原始图象
		FileUtils.copyFile(file, target);

		return newfilename;
	}

	public static String saveAvatar(String source, int x, int y, int w, int h,
			String destination) throws IOException {
		File sPath = new File(source);
		String fileType = sPath.getName().substring(sPath.getName().lastIndexOf(".") + 1);
		File dir = new File(destination);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String newFileName = System.nanoTime() + "." + fileType;
		File destFile = new File(dir.getAbsolutePath() + File.separator
				+ newFileName);
		BufferedImage bi = ImageIO.read(sPath);
		int init_windth = bi.getWidth();
		int init_height = bi.getHeight();
		if (log.isDebugEnabled()) {
			log.debug("Original height：" + init_height + " width:"
					+ init_windth + ":" + bi.getMinX() + ":" + bi.getMinY());
		}

		BufferedImage clipImage = bi.getSubimage(x, y, w, h);
		if (log.isDebugEnabled()) {
			log.debug("Croped x:" + clipImage.getMinX() + ":"
					+ clipImage.getMinY() + ":" + clipImage.getWidth() + ":"
					+ clipImage.getHeight());
		}
		// 利用imagescalr进行图片压缩处理
		BufferedImage scaledImage = Scalr.resize(clipImage,
				Scalr.Method.BALANCED, Constant.AVATAR_WIDTH,
				Constant.AVATAR_HEIGHT);
		ImageIO.write(scaledImage, fileType, new FileOutputStream(destFile));
		return newFileName;
	}
}
