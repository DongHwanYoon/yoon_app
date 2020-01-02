package com.edu.test;

import java.io.UnsupportedEncodingException;

public class HanUtil {

	  public static String convert1(String str){
		 try {
			str =  new String(str.getBytes("8859_1"),"ksc5601");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		 return str;
	  }
	  public static String convert2(String str){
		  try {
			  str =  new String(str.getBytes("8859_1"),"UTF-8");
		  } catch (UnsupportedEncodingException e) {
			  e.printStackTrace();
		  }
		  return str;
	  }
	  public static String convert3(String str){
		  try {
			  str =  new String(str.getBytes("8859_1"),"euc-kr");
		  } catch (UnsupportedEncodingException e) {
			  e.printStackTrace();
		  }
		  return str;
	  }
	  public static String convert4(String str){
		  try {
			  str =  new String(str.getBytes("ksc5601"),"8859_1");
		  } catch (UnsupportedEncodingException e) {
			  e.printStackTrace();
		  }
		  return str;
	  }
	  public static String convert5(String str){
		  try {
			  str =  new String(str.getBytes("ksc5601"),"euc-kr");
		  } catch (UnsupportedEncodingException e) {
			  e.printStackTrace();
		  }
		  return str;
	  }
	  public static String convert6(String str){
		  try {
			  str =  new String(str.getBytes("ksc5601"),"UTF-8");
		  } catch (UnsupportedEncodingException e) {
			  e.printStackTrace();
		  }
		  return str;
	  }
	  public static String convert7(String str){
		  try {
			  str =  new String(str.getBytes("UTF-8"),"8859_1");
		  } catch (UnsupportedEncodingException e) {
			  e.printStackTrace();
		  }
		  return str;
	  }
	  public static String convert8(String str){
		  try {
			  str =  new String(str.getBytes("UTF-8"),"ksc5601");
		  } catch (UnsupportedEncodingException e) {
			  e.printStackTrace();
		  }
		  return str;
	  }
	  public static String convert9(String str){
		  try {
			  str =  new String(str.getBytes("UTF-8"),"euc-kr");
		  } catch (UnsupportedEncodingException e) {
			  e.printStackTrace();
		  }
		  return str;
	  }
	  public static String convert10(String str){
		  try {
			  str =  new String(str.getBytes("euc-kr"),"8859_1");
		  } catch (UnsupportedEncodingException e) {
			  e.printStackTrace();
		  }
		  return str;
	  }
	  public static String convert11(String str){
		  try {
			  str =  new String(str.getBytes("euc-kr"),"ksc5601");
		  } catch (UnsupportedEncodingException e) {
			  e.printStackTrace();
		  }
		  return str;
	  }
	  public static String convert12(String str){
		  try {
			  str =  new String(str.getBytes("euc-kr"),"UTF-8");
		  } catch (UnsupportedEncodingException e) {
			  e.printStackTrace();
		  }
		  return str;
	  }
}
