/**
 * 
 *	坤普
 * Copyright (c) 2016-2019 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.test.common;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author GSZY
 * @version $Id: CommonTest.java, v 0.1 2019年4月3日 上午9:29:43 GSZY Exp $
 */
public class CommonTest {
  protected static final Logger LOGGER = LoggerFactory.getLogger(CommonTest.class);

  @Test
  public void testZkclient() {
    ZkClient zkClient = new ZkClient(new ZkConnection("192.168.1.201:2181"), 5000);

    /**
     * 添加
     */
    zkClient.create("/testZkclient", "zkclient_Test", CreateMode.EPHEMERAL);
    zkClient.createPersistent("/testZkclient/aaa");// 持久化节点创建(递归创建)
    zkClient.createEphemeral("");// 临时节点创建

    /**
     * 删除
     */
    zkClient.delete("/testZkclient");// 删除节点
    zkClient.deleteRecursive("/testZkclient");// 递归删除

  }

//  public static void main(String[] args) throws IOException, InterruptedException {
//    String exe = "python";
//    String command = "D:\\develop\\pythonworkspace\\facenet_test\\common_test.py";
//    String num1 = "1";
//    String num2 = "2";
//    String[] cmdArr = new String[] {exe, command, num1, num2};
//    Process process = Runtime.getRuntime().exec(cmdArr);
//    InputStream is = process.getInputStream();
//    DataInputStream dis = new DataInputStream(is);
//    String str = dis.readLine();
//    process.waitFor();
//    System.out.println(str);
//}
//
//  @Test
//  public void testPythonCmd() {
//    try {
//      StopWatch watch = new StopWatch();
//      watch.start();
//      String exe = "python";
//      String command = "D:\\develop\\pyworkspace\\facenet_test\\facenet_test.py";
//      String num1 = "D://tmp//photo";
//      String num2 = "D://soft//facenet//facenet_models//20170512-110547";
//      String[] cmdArr = new String[] {exe, command, num1, num2};
//      Process process = Runtime.getRuntime().exec(cmdArr);
//      InputStream is = process.getInputStream();
//      DataInputStream dis = new DataInputStream(is);
//      String str = dis.readLine();
//      process.waitFor();
//      LOGGER.info(str);
//      watch.stop();
//      LOGGER.info("执行时间：{}", watch.getTime() / 1000);
//    } catch (Exception e) {
//      e.printStackTrace();
//
//    }
//  }



//  @Test
//  public void testJpython(){
//    System.setProperty("python.home", "C:\\ProgramData\\Anaconda3\\envs\\tensorflow");
//    // 1. Python面向函数式编程: 在Java中调用Python函数
//    String pythonFunc = "D:\\develop\\pyworkspace\\facenet_test\\facenet_test.py";
//
//    PythonInterpreter pi1 = new PythonInterpreter();
//    // 加载python程序
//    pi1.execfile(pythonFunc);
//    // 调用Python程序中的函数
//    PyFunction pyf = pi1.get("main", PyFunction.class);
//    PyObject dddRes = pyf.__call__(Py.newInteger(2), Py.newInteger(3));
//    LOGGER.info("调用：{}", dddRes);
//    pi1.cleanup();
//    pi1.close();
//
//    // // 2. 面向对象式编程: 在Java中调用Python对象实例的方法
//    // String pythonClass = "D:\\calculator_clazz.py";
//    // // python对象名
//    // String pythonObjName = "cal";
//    // // python类名
//    // String pythonClazzName = "Calculator";
//    // PythonInterpreter pi2 = new PythonInterpreter();
//    // // 加载python程序
//    // pi2.execfile(pythonClass);
//    // // 实例化python对象
//    // pi2.exec(pythonObjName + "=" + pythonClazzName + "()");
//    // // 获取实例化的python对象
//    // PyObject pyObj = pi2.get(pythonObjName);
//    // // 调用python对象方法,传递参数并接收返回值
//    // PyObject result = pyObj.invoke("power", new PyObject[] {Py.newInteger(2), Py.newInteger(3)});
//    // double power = Py.py2double(result);
//    // System.out.println(power);
//    //
//    // pi2.cleanup();
//    // pi2.close();
//    
//  }
}
