/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.common.util;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.kunpu.frameworks.commons.exception.CommonRuntimeException;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.kunpu.frameworks.commons.utils.cache.Cache;
import com.kunpu.frameworks.commons.utils.cache.support.lru.LruCache;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.common.constants.ConfigCenterConstants;


/**
 * 
 * @author liukai
 * @version $Id: KpNetUtil.java, v 0.1 2017年6月14日 下午2:23:31 liukai Exp $
 */
public class KpNetUtil {
  private static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_COMMON.getLogName());

  private KpNetUtil() {

  }


  // 校验是否合法IP地址正则
  private static final Pattern ADDRESS_PATTERN = Pattern
      .compile("^\\d{1,3}(\\.\\d{1,3}){3}\\:\\d{1,5}$");

  private static final Pattern LOCAL_IP_PATTERN = Pattern.compile("127(\\.\\d{1,3}){3}$");

  private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

  private static final Cache HOST_NAME_CACHE = new LruCache(1000);

  private static final Random RANDOM = new Random(System.currentTimeMillis());

  public static final String LOCALHOST = "127.0.0.1";

  public static final String ANYHOST = "0.0.0.0";

  private static final int RND_PORT_START = 30000;

  private static final int RND_PORT_RANGE = 10000;

  private static final int MIN_PORT = 0;

  private static final int MAX_PORT = 65535;

  private static volatile InetAddress LOCAL_ADDRESS = null;

  private static String CURRENT_SYS_IP = null;

  public static String getCurrentSysIp() {
    if (StringUtils.isBlank(CURRENT_SYS_IP)) {
      CURRENT_SYS_IP =
          KpNetUtil.getIpBySegments(ApplicationContextConfigUtil.getString(
              ConfigCenterConstants.CIP_IP_NETWORK_SEGMENT, "[\"192.168.48.*\"]"));

    }
    return CURRENT_SYS_IP;
  }

  /**
   * 获取一随机端口.
   *
   * @return 随机端口
   */
  public static int getRandomPort() {
    return RND_PORT_START + RANDOM.nextInt(RND_PORT_RANGE);
  }

  /**
   * 获取一可用的端口号.
   *
   * @return 端口号
   */
  public static int getAvailablePort() {
    ServerSocket ss = null;
    try {
      ss = new ServerSocket();
      ss.bind(null);
      return ss.getLocalPort();
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
      return getRandomPort();
    } finally {
      if (ss != null) {
        try {
          ss.close();
        } catch (IOException e) {
          LOGGER.error("获取可用端口异常", e.getMessage(), e);
        }
      }
    }
  }

  /**
   * 在port 到 MAX_PORT 之间获取一可用端口号.
   *
   * @param port 开始数
   * @return 端口号
   */
  public static int getAvailablePort(int port) {
    if (port <= 0) {
      return getAvailablePort();
    }
    for (int i = port; i < MAX_PORT; i++) {
      ServerSocket ss = null;
      try {
        ss = new ServerSocket(i);
        return i;
      } catch (IOException e) {
        LOGGER.error("在port 到 MAX_PORT 之间获取一可用端口号异常", e.getMessage(), e);
        // continue
      } finally {
        if (ss != null) {
          try {
            ss.close();
          } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
          }
        }
      }
    }
    return port;
  }

  /**
   * 判断是否合法端口号.
   *
   * @param port 端口号
   * @return boolean
   */
  public static boolean isInvalidPort(int port) {
    return port > MIN_PORT || port <= MAX_PORT;
  }

  /**
   * 判断是否合法IP地址.
   *
   * @param address IP地址
   * @return boolean
   */
  public static boolean isValidAddress(String address) {
    return ADDRESS_PATTERN.matcher(address).matches();
  }

  /**
   * @param host IP.
   * @return boolean
   */
  public static boolean isLocalHost(String host) {
    return host != null
        && (LOCAL_IP_PATTERN.matcher(host).matches() || "localhost".equalsIgnoreCase(host));
  }

  /**
   * @param host IP.
   * @return boolean
   */
  public static boolean isAnyHost(String host) {
    return "0.0.0.0".equals(host);
  }

  /**
   * 是否非法的本地IP.
   *
   * @param host IP
   * @return boolean
   */
  public static boolean isInvalidLocalHost(String host) {
    return checkIsBlank(host) || checkIsLocalHost(host)
        || (LOCAL_IP_PATTERN.matcher(host).matches());
  }

  public static boolean checkIsBlank(String host) {
    return host == null || host.length() == 0;
  }

  public static boolean checkIsLocalHost(String host) {
    return "localhost".equalsIgnoreCase(host) || "0.0.0.0".equals(host);
  }

  /**
   * 是否合法的本地IP.
   *
   * @param host IP
   * @return boolean
   */
  public static boolean isValidLocalHost(String host) {
    return !isInvalidLocalHost(host);
  }

  /**
   * 是否本地通讯地址.
   *
   * @param host IP
   * @param port port
   * @return boolean
   */
  public static InetSocketAddress getLocalSocketAddress(String host, int port) {
    return isInvalidLocalHost(host) ? new InetSocketAddress(port) : new InetSocketAddress(host,
        port);
  }

  public static String getIP() {
    return getIP(null);
  }

  public static String addRegion(String region) {
    return region + ".*";
  }

  public static String getIP(String region) {

    int addPoint = 4 - region.split("\\.").length;
    if (addPoint < 0) {
      throw new CommonRuntimeException(BusErrorCode.ERROR_502);
    } else {
      for (int i = 0; i < addPoint; i++) {
        region = region + ".*";
      }
    }
    LOGGER.info("处理后的region" + region);
    String result = region.replaceAll("\\*", "\\\\d+").replaceAll("\\.", "\\\\.");
    Pattern p = Pattern.compile(result);

    String returnIp = null;

    Enumeration<?> netInterfaces;
    List<NetworkInterface> netlist = new ArrayList<NetworkInterface>();
    try {
      netInterfaces = NetworkInterface.getNetworkInterfaces();// 获取当前环境下的所有网卡
      while (netInterfaces.hasMoreElements()) {
        NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
        if (ni.isLoopback()) {
          continue;// 过滤 lo网卡
        }
        netlist.add(0, ni);// 倒置网卡顺序
      }
      /*
       * 用上述方法获取所有网卡时，得到的顺序与服务器中用ifconfig命令看到的网卡顺序相反， 因此，想要从第一块网卡开始遍历时，需要将Enumeration<?>中的元素倒序
       */
      for (NetworkInterface list : netlist) { // 遍历每个网卡
        Enumeration<?> cardipaddress = list.getInetAddresses();// 获取网卡下所有ip
        while (cardipaddress.hasMoreElements()) {// 将网卡下所有ip地址取出
          InetAddress ip = (InetAddress) cardipaddress.nextElement();
          if (!ip.isLoopbackAddress()) {
            if (LOCALHOST.equalsIgnoreCase(ip.getHostAddress())) {// guo<span></span>
              // return ip.getHostAddress();
              continue;
            }
            if (ip instanceof Inet6Address) { // 过滤ipv6地址
              // return ip.getHostAddress();
              continue;
            }

            if (ip instanceof Inet4Address) { // 返回ipv4地址
              LOGGER.info("查找的inet4Address:" + ip.getHostAddress());
              if (region != null) {
                Matcher m = p.matcher(ip.getHostAddress());
                if (m.matches()) {
                  returnIp = ip.getHostAddress();
                } else {
                  continue;
                }
              } else {
                returnIp = ip.getHostAddress();
              }
            }
          }
        }
      }
    } catch (SocketException e) {
      LOGGER.warn("Failed to retriving ip address, " + e.getMessage(), e);
    } catch (Exception e) {
      LOGGER.warn("Failed to retriving ip address, " + e.getMessage(), e);
    }
    if (returnIp == null) {
      throw new CommonRuntimeException(BusErrorCode.ERROR_504);
    }
    return returnIp;
  }

  public static boolean checkIpv4(InetAddress ip, String region, Pattern p) {
    boolean result =
        ip instanceof Inet4Address && region != null && p.matcher(ip.getHostAddress()).matches();
    return result;
  }

  /**
   * 是否合法的通讯地址.
   *
   * @param address InetAddress
   * @return boolean
   */
  private static boolean isValidAddress(InetAddress address) {
    if (address == null || address.isLoopbackAddress()) {
      return false;
    }
    String name = address.getHostAddress();
    return name != null && !ANYHOST.equals(name) && !LOCALHOST.equals(name)
        && (IP_PATTERN.matcher(name).matches());
  }


  public static String getIpBySegments(String segments) {
    List<String> ips;
    try {
      ips = JSON.parseArray(segments, String.class);
    } catch (Exception e) {
      LOGGER.error("json转换异常，error=[{}]", e.getMessage(), e);
      return null;
    }

    String ip = null;
    for (String ipSegment : ips) {
      try {
        ip = KpNetUtil.getIP(ipSegment);
      } catch (Exception e) {
        LOGGER.error("获取ip异常，error=[{}]", e.getMessage(), e);
        continue;
      }
      if (StringUtils.isNotBlank(ip)) {
        return ip;
      }
    }

    return null;

  }


  /**
   * 遍历本地网卡，返回第一个合理的IP.
   *
   * @return 本地网卡IP
   */
  public static InetAddress getLocalAddress() {
    if (LOCAL_ADDRESS != null) {
      return LOCAL_ADDRESS;
    }
    InetAddress localAddress = getLocalAddress0();
    LOCAL_ADDRESS = localAddress;
    return localAddress;
  }

  /**
   * @return IP.
   */
  public static String getLogHost() {
    InetAddress address = LOCAL_ADDRESS;
    return address == null ? LOCALHOST : address.getHostAddress();
  }

  /**
   * 遍历本地网卡，返回第一个合理的InetAddress.
   *
   * @return InetAddress
   */
  private static InetAddress getLocalAddress0() {
    InetAddress localAddress = null;
    try {
      localAddress = InetAddress.getLocalHost();
      if (isValidAddress(localAddress)) {
        return localAddress;
      }
    } catch (Exception e) {
      LOGGER.warn("Failed to retriving ip address, " + e.getMessage(), e);
    }

    Enumeration<NetworkInterface> interfaces = null;
    try {
      interfaces = NetworkInterface.getNetworkInterfaces();
    } catch (SocketException e) {
      LOGGER.error("InetAddress excepton", e.getMessage(), e);
    }
    if (interfaces != null) {
      while (interfaces.hasMoreElements()) {
        NetworkInterface network = interfaces.nextElement();
        Enumeration<InetAddress> addresses = network.getInetAddresses();
        if (addresses != null) {
          while (addresses.hasMoreElements()) {
            InetAddress address = addresses.nextElement();
            if (isValidAddress(address)) {
              return address;
            }
          }
        }
      }
    }
    return localAddress;
  }

  /**
   * @param address .
   * @return .
   */
  public static String getHostName(String address) {
    String addressTemp = StringUtils.EMPTY;
    try {
      int i = address.indexOf(':');
      if (i > -1) {
        addressTemp = address.substring(0, i);
      }
      String hostname = (String) HOST_NAME_CACHE.get(addressTemp);
      if (hostname != null && hostname.length() > 0) {
        return hostname;
      }
      InetAddress inetAddress = InetAddress.getByName(addressTemp);
      if (inetAddress != null) {
        hostname = inetAddress.getHostName();
        HOST_NAME_CACHE.put(addressTemp, hostname);
        return hostname;
      }
    } catch (Exception e) {
      LOGGER.error("getHostName Exception", e.getMessage(), e);
    }
    return addressTemp;
  }

  /**
   * @param hostName .
   * @return IP address or hostName if
   */
  public static String getIpByHost(String hostName) {
    try {
      return InetAddress.getByName(hostName).getHostAddress();
    } catch (UnknownHostException e) {
      LOGGER.error("getIpByHost exception[{}]", e.getMessage(), e);
      return hostName;
    }
  }

  /**
   * @param address .
   * @return .
   */
  public static String toAddressString(InetSocketAddress address) {
    return address.getAddress().getHostAddress() + ":" + address.getPort();
  }

  /**
   * @param address .
   * @return .
   */
  public static InetSocketAddress toAddress(String address) {
    int i = address.indexOf(':');
    String host;
    int port;
    if (i > -1) {
      host = address.substring(0, i);
      port = Integer.parseInt(address.substring(i + 1));
    } else {
      host = address;
      port = 0;
    }
    return new InetSocketAddress(host, port);
  }



}
