/**
 * 
 */
package com.xrds.basic.component.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.time.StopWatch;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


/**
 * @author GSZY
 *
 */
public class OpencvUtil {
  // 初始化人脸探测器
  static CascadeClassifier faceDetector;

  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    faceDetector =
        new CascadeClassifier(
            "D:\\soft\\opencv\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
    // new CascadeClassifier(
    // "D:\\soft\\opencv\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_default.xml");


    // new CascadeClassifier(
    // "D:\\soft\\opencv\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt_tree.xml");



        // new CascadeClassifier(
        // "D:\\soft\\opencv\\opencv\\sources\\data\\haarcascades_cuda\\haarcascade_frontalface_default.xml");



    // new CascadeClassifier(
    // "D:\\soft\\opencv\\opencv\\sources\\data\\haarcascades_cuda\\haarcascade_frontalface_alt2.xml");
  }


  // 灰度化人脸
  public static Mat conv_Mat(String img) throws IOException {
    Mat image0 = Imgcodecs.imread(img);

    Mat image1 = new Mat();

    int type = ImageIO.read(new File(img)).getType();

    // 灰度化
    Imgproc.cvtColor(image0, image1, Imgproc.COLOR_BGR2GRAY);
    // 探测人脸
    MatOfRect faceDetections = new MatOfRect();
    faceDetector.detectMultiScale(image1, faceDetections);
    // rect中人脸图片的范围
    for (Rect rect : faceDetections.toArray()) {
      Mat face = new Mat(image1, rect);
      // System.out.println("" + face.toString() + face.nativeObj + face.dataAddr() +
      // face.channels()
      // + face.depth() + face.size());
      return face;
    }
    return null;
  }

  public static double compare_image(String img_1, String img_2) throws IOException {
    Mat mat_1 = conv_Mat(img_1);
    Mat mat_2 = conv_Mat(img_2);
    Mat hist_1 = new Mat();
    Mat hist_2 = new Mat();

    // 颜色范围
    MatOfFloat ranges = new MatOfFloat(0f, 256f);
    // 直方图大小， 越大匹配越精确 (越慢)
    MatOfInt histSize = new MatOfInt(10000000);

    Imgproc.calcHist(Arrays.asList(mat_1), new MatOfInt(0), new Mat(), hist_1, histSize, ranges);
    Imgproc.calcHist(Arrays.asList(mat_2), new MatOfInt(0), new Mat(), hist_2, histSize, ranges);

    // CORREL 相关系数
    double res = Imgproc.compareHist(hist_1, hist_2, Imgproc.CV_COMP_CORREL);
    return res;
  }

  public static void main(String[] args) {

    String basePicPath = "D:\\tmp\\photo\\";
    Map<String, String> photoMap = new HashMap<>();

    // photoMap.put("1", "123.jpg,123.png");
    // photoMap.put("2", "123.jpg,234.jpg");
    // photoMap.put("3", "123.jpg,2345.jpg");
    // photoMap.put("4", "123.jpg,456.png");
    // photoMap.put("5", "123.png,345.jpg");
    // photoMap.put("6", "123.png,456.png");
    // photoMap.put("7", "123.png,234.jpg");
    // photoMap.put("8", "123.png,1234.jpg");
    // photoMap.put("9", "123.png,2345.jpg");
    photoMap.put("10", "7273.JPG,abc.jpg");
    // photoMap.put("11", "7273.JPG,bcd.jpg");
    // photoMap.put("12", "abc.jpg,bcd.jpg");
    photoMap.put("13", "123.jpg,1234.jpg");

    for (String string : photoMap.keySet()) {
      String[] strings = photoMap.get(string).split(",");
      try {
        StopWatch watch = new StopWatch();
        watch.start();
        double compareHist = compare_image(basePicPath + strings[0], basePicPath + strings[1]);
        System.out.println(strings[0] + "和" + strings[1] + "比对结果为：" + compareHist);


        // if (compareHist > 0.72) {
        // System.out.println("人脸匹配");
        // } else {
        // System.out.println("人脸不匹配");
        // }
        watch.stop();
        System.out.println("耗时：" + watch.getTime() + "ms");
      } catch (Exception e) {
        e.printStackTrace();
      }


    }



  }



}
