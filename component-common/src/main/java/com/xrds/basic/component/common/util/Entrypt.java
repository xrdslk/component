package com.xrds.basic.component.common.util;

/**
 *
 * Created by WangGang on 2016/6/21.
 */
public class Entrypt {

  /**
   * 加密字符串 sha-1 hash
   * 
   * @param str 需要加密的字符串
   * @param salt
   * @return
   */
  public static String entrypt(String str, String salt) {
    byte[] bytes = Encodes.decodeHex(salt);
    byte[] hashPassword = Digests.sha1(str.getBytes(), bytes, 1024);
    return Encodes.encodeHex(hashPassword);
  }

  public static String entryptWithoutIterate(String str, String salt) {
    byte[] bytes = Encodes.decodeHex(salt);
    byte[] hashPassword = Digests.sha1(str.getBytes(), bytes, 0);
    return Encodes.encodeHex(hashPassword);
  }

  public static String entryptOnlyIterate(String sha1Str) {
    byte[] hashPassword = Digests.digest(Encodes.decodeHex(sha1Str), "SHA-1", 1024);
    return Encodes.encodeHex(hashPassword);
  }

  public static void main(String[] args) {
    String s = "111111";
    String salt = "b6027d1b835e6048";
    System.out.println(entrypt(s, salt));
    String t = entryptWithoutIterate(s, salt);
    System.out.println(entryptOnlyIterate(t));
  }
}
