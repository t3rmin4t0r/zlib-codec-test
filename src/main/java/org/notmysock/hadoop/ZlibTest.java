package org.notmysock.hadoop;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.io.compress.zlib.ZlibDecompressor;

public class ZlibTest {

  public static final int[] raw = { 0xe3, 0x92, 0xe1, 0x62, 0x66, 0x60, 0x60,
      0x10, 0x12, 0xe5, 0x98, 0xe0, 0x27, 0xc4, 0xc7, 0xf1, 0xe8, 0x12, 0x8f,
      0x40, 0xc3, 0x7b, 0x5e, 0x89, 0x09, 0x7f, 0x6e, 0x74, 0x73, 0x04, 0x30,
      0x70, 0xc9, 0x72, 0xb1, 0x30, 0x14, 0x4d, 0x60, 0x82, 0x49, 0x37, 0xbd,
      0xe7, 0x15, 0x58, 0xd0, 0xcd, 0x2f, 0x31, 0xa1, 0xa1, 0xe3, 0x35, 0x4c,
      0xfa, 0x15, 0xa3, 0x02, 0x4c, 0x7a, 0x51, 0x37, 0xbf, 0xc0, 0x81, 0xe5,
      0x02, 0x12, 0x13, 0x5a, 0xb6, 0x9f, 0xe2, 0x04, 0xea, 0x96, 0xe3, 0x62,
      0x65, 0xb8, 0xc3, 0xb4, 0x01, 0xae, 0xfd, 0xd0, 0x72, 0x01, 0x81, 0x07,
      0x87, 0x05, 0x25, 0x26, 0x74, 0x3c, 0x5b, 0xc9, 0x05, 0x35, 0xfd, 0x0a,
      0xb3, 0x03, 0x50, 0x7b, 0x83, 0x11, 0xc8, 0xf2, 0xc3, 0x82, 0x02, 0x0f,
      0x96, 0x0b, 0x49, 0x34, 0x7c, 0xfa, 0xff, 0x9f, 0x2d, 0x80, 0x01, 0x00 };
  
  public static final byte[] data = new byte[raw.length];
  
  static {
    for (int i = 0; i < raw.length; i++) {
      data[i] = (byte) (raw[i] & 0xff);
    }
  }

  public static void main(String[] args) throws IOException {
    org.apache.hadoop.io.compress.zlib.ZlibDecompressor.ZlibDirectDecompressor zlibDirectDecompressor = new ZlibDecompressor.ZlibDirectDecompressor
    (ZlibDecompressor.CompressionHeader.NO_HEADER, 0);
    ByteBuffer src = ByteBuffer.allocateDirect(data.length);
    src.put(data);
    src.flip();
    ByteBuffer dst = ByteBuffer.allocateDirect(4096);
    zlibDirectDecompressor.decompress(src, dst);
    System.out.println(dst);
  }

}
