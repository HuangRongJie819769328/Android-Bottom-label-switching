package com.hrj.kuanjia.bottomtab5;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * 框架启动类
 * 
 * @author 黄荣洁
 * 
 */
public class Bottomtab5_Util {

	private static FragmentActivity context;
	/**
	 * 下方标签个数
	 */
	private static int count;

	/**
	 * 存放Fragment
	 */
	private static HashMap<Integer, Fragment> fragmentmap;
	/**
	 * 存放image的id
	 */
	private static HashMap<Integer, ArrayList<Integer>> imagermap;
	/**
	 * 存放text
	 */
	private static HashMap<Integer, String> textmap;
	/**
	 * 存放headfragment
	 */
	private static HashMap<Integer, Fragment> headfragmentmap;

	/**
	 * 存放字体颜色normal
	 */
	static int text_color_normal = Color.parseColor("#c8bca5");

	/**
	 * 存放字体颜色press
	 */
	static int text_color_press = Color.parseColor("#8b572a");

	static MyViewIndex myViewIndex;

	/**
	 * 当前页数
	 */
	private static int index = 0;

	/**
	 * 资源Layout引用
	 */
	static int r_layout = 0;

	/**
	 * 初始化框架
	 * 
	 * @param context
	 *            上下文
	 * @param count
	 *            你要创建多少个底部标签
	 */
	public static void init(FragmentActivity context, int r_layout, int count) {
		// 设置上下文
		setContext(context);
		// 每个项目的r资源路径不同，需要设置
		Bottomtab5_Util.r_layout = r_layout;
		// 设置个数
		setCount(count);
		// 初始化数据
		setHeadFragmentmap(new HashMap<Integer, Fragment>());
		setFragmentmap(new HashMap<Integer, Fragment>());
		setImagermap(new HashMap<Integer, ArrayList<Integer>>());
		setTextmap(new HashMap<Integer, String>());
	}

	/**
	 * 启动框架，返回view对象
	 */
	public static void startForView(MyViewBack myViewBack) {
		myViewBack.onViewBack(new MyKuangjiaView(context).getView());
	}

	/**
	 * 启动框架，以activity方式
	 */
	public static void startForActivity() {
		getContext().startActivity(
				new Intent(context, Bottomtab5Activity.class));
		((Activity) getContext()).finish();
	}

	/**
	 * 设置fragment
	 * 
	 * @param index
	 *            下标从1-5
	 * @param fragment
	 */
	public static void setContentFragment(int index, Fragment fragment) {
		getFragmentmap().put(index, fragment);
	}

	/**
	 * 设置下面组件的图片资源
	 * 
	 * @param index
	 *            那个位置的图片 1-5
	 * @param press_image_id
	 *            按下的时候显示的图片
	 * @param normal_image_id
	 *            正常时候显示的图片
	 */
	public static void setImageResource(int index, int press_image_id,
			int normal_image_id) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(press_image_id);
		list.add(normal_image_id);
		getImagermap().put(index, list);
	}

	/**
	 * 设置下面组件的text文本
	 * 
	 * @param index
	 * @param text
	 */
	public static void setText(int index, String text) {
		getTextmap().put(index, text);
	}

	/**
	 * 设置下面组件的text文本颜色
	 * 
	 * @param normal
	 *            正常时的颜色
	 * @param press
	 *            按下时的颜色
	 */
	public static void setTextColor(int normal, int press) {
		text_color_normal = normal;
		text_color_press = press;
	}

	/**
	 * 设置所有的头fragment都一样
	 * 
	 * @param fragment
	 */
	public static void setHeadAllFragment(Fragment fragment) {
		for (int i = 1; i <= getCount(); i++)
			getHeadFragmentmap().put(i, fragment);
	}

	/**
	 * 设置头fragment
	 * 
	 * @param index
	 *            下标1-5
	 * @param fragment
	 */
	public static void setHeadFragment(int index, Fragment fragment) {
		getHeadFragmentmap().put(index, fragment);
	}

	/**
	 * 设置当前所在页的回调事件
	 * 
	 * @param myViewIndex
	 */
	public static void setOnBackViewIndex(MyViewIndex myViewIndex) {
		Bottomtab5_Util.myViewIndex = myViewIndex;
	}

	public static void setIndex(int index) {
		Bottomtab5_Util.index = index;
		if (null != myViewIndex)
			myViewIndex.onIndex(index);
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(FragmentActivity context) {
		Bottomtab5_Util.context = context;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Bottomtab5_Util.count = count;
	}

	public static HashMap<Integer, Fragment> getFragmentmap() {
		return fragmentmap;
	}

	public static void setFragmentmap(HashMap<Integer, Fragment> fragmentmap) {
		Bottomtab5_Util.fragmentmap = fragmentmap;
	}

	public static HashMap<Integer, String> getTextmap() {
		return textmap;
	}

	public static void setTextmap(HashMap<Integer, String> textmap) {
		Bottomtab5_Util.textmap = textmap;
	}

	public static HashMap<Integer, ArrayList<Integer>> getImagermap() {
		return imagermap;
	}

	public static void setImagermap(
			HashMap<Integer, ArrayList<Integer>> imagermap) {
		Bottomtab5_Util.imagermap = imagermap;
	}

	public static HashMap<Integer, Fragment> getHeadFragmentmap() {
		return headfragmentmap;
	}

	public static void setHeadFragmentmap(
			HashMap<Integer, Fragment> headfragment) {
		Bottomtab5_Util.headfragmentmap = headfragment;
	}

	public static MyViewIndex getMyViewIndex() {
		return myViewIndex;
	}

	public static void setMyViewIndex(MyViewIndex myViewIndex) {
		Bottomtab5_Util.myViewIndex = myViewIndex;
	}

	public static int getIndex() {
		return index;
	}

}
