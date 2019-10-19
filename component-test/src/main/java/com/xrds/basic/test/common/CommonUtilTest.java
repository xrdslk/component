package com.xrds.basic.test.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;

import com.xrds.basic.component.common.seetaface.face.FaceHelper;
import com.xrds.basic.component.common.seetaface.face.SeetaFace2JNI;
import com.xrds.basic.component.common.seetaface.face.SeetafaceBuilder;
import com.xrds.basic.component.common.seetaface.face.bean.Result;
import com.xrds.basic.component.common.seetaface.face.model.RecognizeResult;
import com.xrds.basic.component.common.seetaface.face.model.SeetaImageData;
import com.xrds.basic.component.common.seetaface.face.model.SeetaRect;
import com.xrds.basic.component.common.seetaface.face.utils.ImageUtils;

public class CommonUtilTest {
  private static Logger logger = LoggerFactory.getLogger(CommonUtilTest.class);

  private void init() {
    SeetafaceBuilder.build();// 系统启动时先调用初始化方法

    // 等待初始化完成
    while (SeetafaceBuilder.getFaceDbStatus() == SeetafaceBuilder.FacedbStatus.LOADING
        || SeetafaceBuilder.getFaceDbStatus() == SeetafaceBuilder.FacedbStatus.READY) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void testFile() {
    File file = new File("D:\\tmp\\tmpphoto2");
    File[] files = file.listFiles();
    init();
    int i = 0;
    StopWatch watch = new StopWatch();
    watch.start();
    // BufferedImage image1 =
    // ImageIO.read(new File("D:\\tmp\\tmpphoto\\C201906261159585810000062back.jpg"));
    // SeetaImageData imageData = new SeetaImageData(image1.getWidth(), image1.getHeight(), 3);
    // imageData.data = ImageUtils.getMatrixBGR(image1);
    File filefront = new File("D:\\tmp\\photo\\abc.jpg");
    File fileliving = new File("D:\\tmp\\photo\\7273.jpg");

    for (File fileTmp : files) {
      String pathString = fileTmp.getPath();
      if (pathString.endsWith("front.jpg")) {
        fileTmp.delete();
      }
    }

  }


  @Test
  public void testPhoto2() {
    File file = new File("D:\\tmp\\tmpphoto2");
    File[] files = file.listFiles();

    // SeetafaceBuilder sf1 = new SeetafaceBuilder();
    // SeetafaceBuilder sf2 = new SeetafaceBuilder();
    //
    // sf1.build();
    // sf2.build();

    // SeetaFace2JNI sf1 = initSF();
    // // sf1.initModel("F:\\doc\\seetaface\\bindata");
    // SeetaFace2JNI sf2 = initSF();
    // // sf2.initModel("F:\\doc\\seetaface\\bindata");
    // int i = 0;
    // for (File fileTmp : files) {
    // i++;
    // String pathString = fileTmp.getPath();
    // try {
    // // float f1 = FaceHelper.compare(filefront, fileliving);
    // // float f2 = FaceHelper.compare(fileliving, fileTmp);
    // //
    // //
    // // logger.info("比对结果{},{}", f1, f2);
    // // boolean registerFlag = FaceHelper.register("" + i, ImageIO.read(fileTmp));
    // BufferedImage image = ImageIO.read(fileTmp);
    // SeetaImageData imageData = new SeetaImageData(image.getWidth(), image.getHeight(), 3);
    // imageData.data = ImageUtils.getMatrixBGR(image);
    // if (i <= 10) {
    // int index = sf1.register(imageData);
    // logger.info("key:{},注册结果:{},file:{}：", i, index, pathString);
    // } else {
    // int index = sf2.register(imageData);
    // logger.info("key:{},注册结果:{},file:{}：", i, index, pathString);
    // if (index > 498) break;
    // }
    //
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    File filefront = new File("D:\\tmp\\photo\\abc.jpg");
    File fileliving = new File("D:\\tmp\\photo\\7273.jpg");
    try {
      StopWatch watch2 = new StopWatch();
      watch2.start();
      BufferedImage image1 = ImageIO.read(filefront);
      SeetaImageData imageData = new SeetaImageData(image1.getWidth(), image1.getHeight(), 3);
      imageData.data = ImageUtils.getMatrixBGR(image1);

      RecognizeResult rr = FaceHelper.seeta.recognize(imageData);
      logger.info("查询结果{}", rr);
      watch2.stop();
      logger.info("检索耗时：" + watch2.getTime() + "ms");



      SeetaRect[] seetaRects1 = FaceHelper.seeta.detect(imageData);
      logger.info("人脸特征1:{}", seetaRects1);

      SeetaRect[] seetaRects2 = FaceHelper.seeta.detect(imageData);
      logger.info("人脸特征2:{}", seetaRects2);

      // BufferedImage image2 = ImageIO.read(fileliving);
      // SeetaImageData imageData2 = new SeetaImageData(image2.getWidth(), image2.getHeight(), 3);
      // imageData2.data = ImageUtils.getMatrixBGR(image2);
      // SeetaRect[] seetaRects2 = FaceHelper.seeta.detect(imageData2);
      //
      // logger.info("人脸特征2{}", seetaRects2);
      // Result result = new Result(rr);
      // result.setKey(FaceDao.findKeyByIndex(rr.index));
      // return result;
      // FaceHelper.seeta.search(ImageIO.read(new File(
      // "D:\\tmp\\tmpphoto\\C201906261159585810000062back.jpg")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void testPhoto() {
    File file = new File("D:\\tmp\\tmpphoto2");
    File[] files = file.listFiles();
    init();
    int i = 0;
    StopWatch watch = new StopWatch();
    watch.start();
    File filefront = new File("D:\\tmp\\photo\\abc.jpg");
    File fileliving = new File("D:\\tmp\\photo\\7273.jpg");

    for (File fileTmp : files) {
      i++;
      String pathString = fileTmp.getPath();
      try {
        // float f1 = FaceHelper.compare(filefront, fileliving);
        // float f2 = FaceHelper.compare(fileliving, fileTmp);
        //
        //
        // logger.info("比对结果{},{}", f1, f2);
        // boolean registerFlag = FaceHelper.register("" + i, ImageIO.read(fileTmp));
        BufferedImage image = ImageIO.read(fileTmp);
        SeetaImageData imageData = new SeetaImageData(image.getWidth(), image.getHeight(), 3);
        imageData.data = ImageUtils.getMatrixBGR(image);
        int index = FaceHelper.seeta.register(imageData);
        // if (index < 0) {
        // logger.info("register face fail: key={}, index={}", i, index);
        // }
        if (index == 498) break;
        logger.info("key:{},注册结果:{},file:{}：", i, index, pathString);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    watch.stop();
    logger.info("注册耗时：" + watch.getTime() + "ms");

    try {
      StopWatch watch2 = new StopWatch();
      watch2.start();
      BufferedImage image1 = ImageIO.read(filefront);
      SeetaImageData imageData = new SeetaImageData(image1.getWidth(), image1.getHeight(), 3);
      imageData.data = ImageUtils.getMatrixBGR(image1);

      RecognizeResult rr = FaceHelper.seeta.recognize(imageData);
      logger.info("查询结果{}", rr);
      watch2.stop();
      logger.info("检索耗时：" + watch2.getTime() + "ms");



      SeetaRect[] seetaRects1 = FaceHelper.seeta.detect(imageData);
      logger.info("人脸特征1{}", seetaRects1);

      BufferedImage image2 = ImageIO.read(fileliving);
      SeetaImageData imageData2 = new SeetaImageData(image2.getWidth(), image2.getHeight(), 3);
      imageData2.data = ImageUtils.getMatrixBGR(image2);
      SeetaRect[] seetaRects2 = FaceHelper.seeta.detect(imageData2);

      logger.info("人脸特征2{}", seetaRects2);
      // Result result = new Result(rr);
      // result.setKey(FaceDao.findKeyByIndex(rr.index));
      // return result;
      // FaceHelper.seeta.search(ImageIO.read(new File(
      // "D:\\tmp\\tmpphoto\\C201906261159585810000062back.jpg")));
    } catch (IOException e) {
      e.printStackTrace();
    }



  }

  private static Properties getConfig() {
    Properties properties = new Properties();
    String location = "classpath:/seetaface.properties";
    try (InputStream is = new DefaultResourceLoader().getResource(location).getInputStream()) {
      properties.load(is);
      logger.debug("seetaface config: {}", properties.toString());
    } catch (IOException ex) {
      logger.error("Could not load property file:" + location, ex);
    }
    return properties;
  }

  private SeetaFace2JNI initSF() {
    Properties prop = getConfig();
    String separator = System.getProperty("path.separator");
    String sysLib = System.getProperty("java.library.path");
    if (sysLib.endsWith(separator)) {
      System.setProperty("java.library.path", sysLib + prop.getProperty("libs.path", ""));
    } else {
      System.setProperty("java.library.path",
          sysLib + separator + prop.getProperty("libs.path", ""));
    }
    try {// 使java.library.path生效
      Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
      sysPathsField.setAccessible(true);
      sysPathsField.set(null, null);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }

    String[] libs = prop.getProperty("libs", "").split(",");
    for (String lib : libs) {
      logger.debug("load library: {}", lib);
      System.loadLibrary(lib);
    }
    String bindata = prop.getProperty("bindata.dir");
    logger.debug("bindata dir: {}", bindata);

    SeetaFace2JNI seeta = new SeetaFace2JNI();
    seeta.initModel(bindata);
    logger.info("Seetaface init completed!!!");
    return seeta;
  }


  @Test
  public void testCompare() throws Exception {
    init();



    String basePicPath = "D:\\tmp\\photo\\";
    Map<String, String> photoMap = new HashMap<>();

    photoMap.put("1", "123.jpg,123.png");
    photoMap.put("2", "123.jpg,234.jpg");
    photoMap.put("3", "123.jpg,2345.jpg");
    photoMap.put("4", "123.jpg,456.png");
    photoMap.put("5", "123.png,345.jpg");
    photoMap.put("6", "123.png,456.png");
    photoMap.put("7", "123.png,234.jpg");
    photoMap.put("8", "123.png,1234.jpg");
    photoMap.put("9", "123.png,2345.jpg");
    photoMap.put("10", "7273.JPG,abc.jpg");
    photoMap.put("11", "7273.JPG,bcd.jpg");
    photoMap.put("12", "abc.jpg,bcd.jpg");
    photoMap.put("13", "123.jpg,1234.jpg");

    for (String string : photoMap.keySet()) {
      String[] strings = photoMap.get(string).split(",");
      try {
        StopWatch watch = new StopWatch();
        watch.start();
        double compareHist =
            FaceHelper.compare(new File(basePicPath + strings[0]), new File(basePicPath
                + strings[1])); // compare_image(basePicPath + strings[0], basePicPath +
                                // strings[1]);
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



    // String img1 = "D:\\tmp\\photo\\1234.jpg";
    // String img2 = "D:\\tmp\\photo\\2345.jpg";
    // StopWatch watch = new StopWatch();
    //
    // watch.start();
    // System.out.println("result:" + FaceHelper.compare(new File(img1), new File(img2)));
    // watch.stop();
    // System.out.println("耗时：" + watch.getTime() + "ms");

  }

  @Test
  public void testRegister() throws IOException {
    // 将F:\ai\star目录下的jpg、png图片都注册到人脸库中，以文件名为key
    Collection<File> files =
        FileUtils.listFiles(new File("F:\\ai\\star"), new String[] {"jpg", "png"}, false);
    for (File file : files) {
      String key = file.getName();
      try {
        FaceHelper.register(key, FileUtils.readFileToByteArray(file));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    System.out.println(1);
  }

  @Test
  public void testSearch() throws IOException {
    init();

    long l = System.currentTimeMillis();
    Result result = FaceHelper.search(FileUtils.readFileToByteArray(new File("F:\\ai\\gtl.jpg")));
    System.out.println("搜索结果：" + result + "， 耗时：" + (System.currentTimeMillis() - l));
  }

  @Test
  public void testDetect() throws IOException {
    SeetaRect[] rects =
        FaceHelper.detect(FileUtils.readFileToByteArray(new File(
            "F:\\ai\\刘诗诗-bbbbbbbbbbbbbbbbbb.jpg")));
    if (rects != null) {
      for (SeetaRect rect : rects) {
        System.out.println("x=" + rect.x + ", y=" + rect.y + ", width=" + rect.width + ", height="
            + rect.height);
      }
    }
  }

  @Test
  public void testCorp() throws IOException {
    BufferedImage image =
        FaceHelper.crop(FileUtils
            .readFileToByteArray(new File("F:\\ai\\刘诗诗-bbbbbbbbbbbbbbbbbb.jpg")));
    if (image != null) {
      ImageIO.write(image, "jpg", new File("F:\\ai\\corp-face1.jpg"));
    }
  }

  @Test
  public void testDelete() {
    FaceHelper.removeRegister("Angelababy.jpg", "乔欣.jpg");
  }

}
