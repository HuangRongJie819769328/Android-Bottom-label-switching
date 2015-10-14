package com.hrj.kuanjia.bottomtab5;

import java.util.ArrayList;
import java.util.HashMap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hrj.text.bottomtitle.R;

/**
 * 返回view对象给调用者
 * 
 * @author 黄荣洁
 * 
 */
public class MyKuangjiaView implements OnClickListener {

	View view;
	FragmentActivity context;

	// 5个tab布局
	private RelativeLayout bottomtab_rl_one, bottomtab_rl_two,
			bottomtab_rl_thr, bottomtab_rl_four, bottomtab_rl_five;

	// headfragmentmap
	private View bottomtab_top_tab;

	// 底部标签切换的Fragment
	private Fragment oneFragment, twoFragment, thrFragment, fourFragment,
			fiveFragment, currentFragment;
	// 底部标签图片
	private ImageView iv_one, iv_two, iv_thr, iv_four, iv_five;
	// 底部标签的文本
	private TextView tv_one, tv_two, tv_thr, tv_four, tv_five;

	// 用户图片
	HashMap<Integer, ArrayList<Integer>> imagermap;
	// 用户标签文字
	HashMap<Integer, String> textmap;
	// 用户headfragmentmap
	HashMap<Integer, Fragment> headfragmentmap;
	// 用户Contentfragment
	private static HashMap<Integer, Fragment> fragmentmap;

	public MyKuangjiaView(FragmentActivity context) {
		this.context = context;
		creatview();
	}

	private void creatview() {
		LayoutInflater inflater = LayoutInflater.from(context);
		view = inflater.inflate(Bottomtab5_Util.r_layout, null);

		// 获取用户给框架的数据
		imagermap = Bottomtab5_Util.getImagermap();
		textmap = Bottomtab5_Util.getTextmap();
		headfragmentmap = Bottomtab5_Util.getHeadFragmentmap();
		fragmentmap = Bottomtab5_Util.getFragmentmap();

		// bug不足五个时，填充空数据
		int imsize = imagermap.size();
		if (imsize < 5) {
			for (int i = imsize; i < 5; i++)
				imagermap.put(i + 1, imagermap.get(1));
		}
		int tmsize = textmap.size();
		if (tmsize < 5) {
			for (int i = tmsize; i < 5; i++)
				textmap.put(i + 1, textmap.get(1));
		}

		// 初始化界面数据
		initUI();
		// 初始化headfragmentmap
		initheadfragmentmap();
		// 初始化底部标签
		initTab();
		Bottomtab5_Util.setIndex(1);
	}

	/**
	 * 初始化UI
	 */
	private void initUI() {

		// 找到布局
		bottomtab_rl_one = (RelativeLayout) view
				.findViewById(R.id.bottomtab_rl_one);
		bottomtab_rl_two = (RelativeLayout) view
				.findViewById(R.id.bottomtab_rl_two);
		bottomtab_rl_thr = (RelativeLayout) view
				.findViewById(R.id.bottomtab_rl_thr);
		bottomtab_rl_four = (RelativeLayout) view
				.findViewById(R.id.bottomtab_rl_four);
		bottomtab_rl_five = (RelativeLayout) view
				.findViewById(R.id.bottomtab_rl_five);

		bottomtab_rl_one.setOnClickListener(this);
		bottomtab_rl_two.setOnClickListener(this);
		bottomtab_rl_thr.setOnClickListener(this);
		bottomtab_rl_four.setOnClickListener(this);
		bottomtab_rl_five.setOnClickListener(this);

		iv_one = (ImageView) view.findViewById(R.id.bottomtab_iv_one);
		iv_two = (ImageView) view.findViewById(R.id.bottomtab_iv_two);
		iv_thr = (ImageView) view.findViewById(R.id.bottomtab_iv_thr);
		iv_four = (ImageView) view.findViewById(R.id.bottomtab_iv_four);
		iv_five = (ImageView) view.findViewById(R.id.bottomtab_iv_five);

		tv_one = (TextView) view.findViewById(R.id.bottomtab_tv_one);
		tv_two = (TextView) view.findViewById(R.id.bottomtab_tv_two);
		tv_thr = (TextView) view.findViewById(R.id.bottomtab_tv_thr);
		tv_four = (TextView) view.findViewById(R.id.bottomtab_tv_four);
		tv_five = (TextView) view.findViewById(R.id.bottomtab_tv_five);

		// 根据用户数据选择显示个数
		if (Bottomtab5_Util.getCount() < 5)
			bottomtab_rl_five.setVisibility(View.GONE);
		if (Bottomtab5_Util.getCount() < 4)
			bottomtab_rl_four.setVisibility(View.GONE);
		if (Bottomtab5_Util.getCount() < 3)
			bottomtab_rl_thr.setVisibility(View.GONE);
		if (Bottomtab5_Util.getCount() < 2)
			bottomtab_rl_two.setVisibility(View.GONE);

	}

	/**
	 * 初始化headfragmentmap
	 */
	private void initheadfragmentmap() {
		// headfragmentmap，用户没有设置，则不显示
		bottomtab_top_tab = view.findViewById(R.id.bottomtab_top_tab);
		if (headfragmentmap.size() == 0)
			bottomtab_top_tab.setVisibility(View.GONE);
		// 用户默认打开是第一个fragment界面,如果没有，则隐藏
		if (headfragmentmap.get(1) == null)
			bottomtab_top_tab.setVisibility(View.GONE);
		else {// 切换成用户设置的headfragmentmap
			context.getSupportFragmentManager().beginTransaction()
					.replace(R.id.bottomtab_top_tab, headfragmentmap.get(1))
					.commitAllowingStateLoss();
			bottomtab_top_tab.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 初始化底部标签
	 */
	private void initTab() {

		if (oneFragment == null) {
			if ((oneFragment = fragmentmap.get(1)) == null)
				oneFragment = new MyFragment();
		}

		if (!oneFragment.isAdded()) {
			// 提交事务
			context.getSupportFragmentManager().beginTransaction()
					.add(R.id.bottomtab_content_layout, oneFragment)
					.commitAllowingStateLoss();

			// 记录当前Fragment
			currentFragment = oneFragment;

			// 根据用户数据设置图片文本的变化
			// 设置图片
			iv_one.setImageResource(imagermap.get(1).get(0));
			iv_two.setImageResource(imagermap.get(2).get(1));
			iv_thr.setImageResource(imagermap.get(3).get(1));
			iv_four.setImageResource(imagermap.get(4).get(1));
			iv_five.setImageResource(imagermap.get(5).get(1));

			// 设置字体颜色
			tv_one.setTextColor(Bottomtab5_Util.text_color_press);
			tv_two.setTextColor(Bottomtab5_Util.text_color_normal);
			tv_thr.setTextColor(Bottomtab5_Util.text_color_normal);
			tv_four.setTextColor(Bottomtab5_Util.text_color_normal);
			tv_five.setTextColor(Bottomtab5_Util.text_color_normal);

			// 设置字体文本
			tv_one.setText(textmap.get(1));
			tv_two.setText(textmap.get(2));
			tv_thr.setText(textmap.get(3));
			tv_four.setText(textmap.get(4));
			tv_five.setText(textmap.get(5));
		}

	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.bottomtab_rl_one)
			clickTab1Layout();
		else if (id == R.id.bottomtab_rl_two)
			clickTab2Layout();
		else if (id == R.id.bottomtab_rl_thr)
			clickTab3Layout();
		else if (id == R.id.bottomtab_rl_four)
			clickTab4Layout();
		else if (id == R.id.bottomtab_rl_five)
			clickTab5Layout();

	}

	/**
	 * 点击第一个tab
	 */
	private void clickTab1Layout() {
		Bottomtab5_Util.setIndex(1);
		if (oneFragment == null) {
			if ((oneFragment = fragmentmap.get(1)) == null)
				oneFragment = new MyFragment();
		}
		addOrShowFragment(context.getSupportFragmentManager()
				.beginTransaction(), oneFragment);

		// 设置图片
		iv_one.setImageResource(imagermap.get(1).get(0));
		iv_two.setImageResource(imagermap.get(2).get(1));
		iv_thr.setImageResource(imagermap.get(3).get(1));
		iv_four.setImageResource(imagermap.get(4).get(1));
		iv_five.setImageResource(imagermap.get(5).get(1));

		// 设置字体颜色
		tv_one.setTextColor(Bottomtab5_Util.text_color_press);
		tv_two.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_thr.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_four.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_five.setTextColor(Bottomtab5_Util.text_color_normal);

		// 设置头部变化 用户打开是第一个fragment界面,如果没有，则隐藏
		if (headfragmentmap.get(1) == null)
			bottomtab_top_tab.setVisibility(View.GONE);
		else {// 切换成用户设置的headfragmentmap
			context.getSupportFragmentManager().beginTransaction()
					.replace(R.id.bottomtab_top_tab, headfragmentmap.get(1))
					.commitAllowingStateLoss();
			bottomtab_top_tab.setVisibility(View.VISIBLE);
		}

	}

	/**
	 * 点击第二个tab
	 */
	private void clickTab2Layout() {
		Bottomtab5_Util.setIndex(2);
		if (twoFragment == null) {
			if ((twoFragment = fragmentmap.get(2)) == null)
				twoFragment = new MyFragment();
		}
		addOrShowFragment(context.getSupportFragmentManager()
				.beginTransaction(), twoFragment);

		// 设置图片
		iv_one.setImageResource(imagermap.get(1).get(1));
		iv_two.setImageResource(imagermap.get(2).get(0));
		iv_thr.setImageResource(imagermap.get(3).get(1));
		iv_four.setImageResource(imagermap.get(4).get(1));
		iv_five.setImageResource(imagermap.get(5).get(1));

		// 设置字体颜色
		tv_one.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_two.setTextColor(Bottomtab5_Util.text_color_press);
		tv_thr.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_four.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_five.setTextColor(Bottomtab5_Util.text_color_normal);

		// 设置头部变化 用户打开是第二个fragment界面,如果没有，则隐藏
		if (headfragmentmap.get(2) == null)
			bottomtab_top_tab.setVisibility(View.GONE);
		else {// 切换成用户设置的headfragmentmap
			context.getSupportFragmentManager().beginTransaction()
					.replace(R.id.bottomtab_top_tab, headfragmentmap.get(2))
					.commitAllowingStateLoss();
			bottomtab_top_tab.setVisibility(View.VISIBLE);
		}

	}

	/**
	 * 点击第三个tab
	 */
	private void clickTab3Layout() {
		Bottomtab5_Util.setIndex(3);
		if (thrFragment == null) {
			if ((thrFragment = fragmentmap.get(3)) == null)
				thrFragment = new MyFragment();
		}

		addOrShowFragment(context.getSupportFragmentManager()
				.beginTransaction(), thrFragment);

		// 设置图片
		iv_one.setImageResource(imagermap.get(1).get(1));
		iv_two.setImageResource(imagermap.get(2).get(1));
		iv_thr.setImageResource(imagermap.get(3).get(0));
		iv_four.setImageResource(imagermap.get(4).get(1));
		iv_five.setImageResource(imagermap.get(5).get(1));

		// 设置字体颜色
		tv_one.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_two.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_thr.setTextColor(Bottomtab5_Util.text_color_press);
		tv_four.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_five.setTextColor(Bottomtab5_Util.text_color_normal);

		// 设置头部变化 用户打开是第三个fragment界面,如果没有，则隐藏
		if (headfragmentmap.get(3) == null)
			bottomtab_top_tab.setVisibility(View.GONE);
		else {// 切换成用户设置的headfragmentmap
			context.getSupportFragmentManager().beginTransaction()
					.replace(R.id.bottomtab_top_tab, headfragmentmap.get(3))
					.commitAllowingStateLoss();
			bottomtab_top_tab.setVisibility(View.VISIBLE);
		}

	}

	/**
	 * 点击第四个tab
	 */
	private void clickTab4Layout() {
		Bottomtab5_Util.setIndex(4);
		if (fourFragment == null) {
			if ((fourFragment = fragmentmap.get(4)) == null)
				fourFragment = new MyFragment();
		}

		addOrShowFragment(context.getSupportFragmentManager()
				.beginTransaction(), fourFragment);

		// 设置图片
		iv_one.setImageResource(imagermap.get(1).get(1));
		iv_two.setImageResource(imagermap.get(2).get(1));
		iv_thr.setImageResource(imagermap.get(3).get(1));
		iv_four.setImageResource(imagermap.get(4).get(0));
		iv_five.setImageResource(imagermap.get(5).get(1));

		// 设置字体颜色
		tv_one.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_two.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_thr.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_four.setTextColor(Bottomtab5_Util.text_color_press);
		tv_five.setTextColor(Bottomtab5_Util.text_color_normal);

		// 设置头部变化 用户打开是第四个fragment界面,如果没有，则隐藏
		if (headfragmentmap.get(4) == null)
			bottomtab_top_tab.setVisibility(View.GONE);
		else {// 切换成用户设置的headfragmentmap
			context.getSupportFragmentManager().beginTransaction()
					.replace(R.id.bottomtab_top_tab, headfragmentmap.get(4))
					.commitAllowingStateLoss();
			bottomtab_top_tab.setVisibility(View.VISIBLE);
		}

	}

	/**
	 * 点击第5个tab
	 */
	private void clickTab5Layout() {
		Bottomtab5_Util.setIndex(5);
		if (fiveFragment == null) {
			if ((fiveFragment = fragmentmap.get(5)) == null)
				fiveFragment = new MyFragment();
		}

		addOrShowFragment(context.getSupportFragmentManager()
				.beginTransaction(), fiveFragment);

		// 设置图片
		iv_one.setImageResource(imagermap.get(1).get(1));
		iv_two.setImageResource(imagermap.get(2).get(1));
		iv_thr.setImageResource(imagermap.get(3).get(1));
		iv_four.setImageResource(imagermap.get(4).get(1));
		iv_five.setImageResource(imagermap.get(5).get(0));

		// 设置字体颜色
		tv_one.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_two.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_thr.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_four.setTextColor(Bottomtab5_Util.text_color_normal);
		tv_five.setTextColor(Bottomtab5_Util.text_color_press);

		// 设置头部变化 用户打开是第四个fragment界面,如果没有，则隐藏
		if (headfragmentmap.get(5) == null)
			bottomtab_top_tab.setVisibility(View.GONE);
		else {// 切换成用户设置的headfragmentmap
			context.getSupportFragmentManager().beginTransaction()
					.replace(R.id.bottomtab_top_tab, headfragmentmap.get(5))
					.commitAllowingStateLoss();
			bottomtab_top_tab.setVisibility(View.VISIBLE);
		}

	}

	/**
	 * 添加或者显示碎片
	 * 
	 * @param transaction
	 * @param fragment
	 */
	private void addOrShowFragment(FragmentTransaction transaction,
			Fragment fragment) {
		if (currentFragment == fragment)
			return;

		if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
			transaction.hide(currentFragment)
					.add(R.id.bottomtab_content_layout, fragment)
					.commitAllowingStateLoss();
		} else {
			transaction.hide(currentFragment).show(fragment)
					.commitAllowingStateLoss();
		}

		currentFragment = fragment;
	}

	public View getView() {
		return view;
	}
}
