package com.hrj.text.bottomtitle;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.hrj.kuanjia.bottomtab5.Bottomtab5_Util;
import com.hrj.kuanjia.bottomtab5.MyViewBack;
import com.hrj.kuanjia.bottomtab5.MyViewIndex;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 初始化框架，5个标签就填5
		Bottomtab5_Util.init(this, R.layout.bottomtab_main, 5);

		// 设置headfragment，可以不设置头那部分的fragment
		// 设置所有有一样的headfragment
		// Bottomtab5_Util.setHeadAllFragment(new MyHeadFragment());
		// 只设置1，3号标签，有头部
		Bottomtab5_Util.setHeadFragment(1, new HeadFragment());
		Bottomtab5_Util.setHeadFragment(3, new HeadFragment());

		// 设置contentfragment
		Bottomtab5_Util.setContentFragment(1, new ContentFragment());
		Bottomtab5_Util.setContentFragment(2, new ContentFragment());
		Bottomtab5_Util.setContentFragment(3, new ContentFragment());
		Bottomtab5_Util.setContentFragment(4, new ContentFragment());
		Bottomtab5_Util.setContentFragment(5, new ContentFragment());

		// 设置标签图片,press_image_id 按下的时候显示的图片 normal_image_id 正常时候显示的图片
		Bottomtab5_Util.setImageResource(1, R.drawable.icon_home_pre,
				R.drawable.icon_home_nor);
		Bottomtab5_Util.setImageResource(2, R.drawable.icon_me_pre,
				R.drawable.icon_me_nor);
		Bottomtab5_Util.setImageResource(3, R.drawable.icon_shequ_pre,
				R.drawable.icon_shequ_nor);
		Bottomtab5_Util.setImageResource(4, R.drawable.icon_xiaoquan_pre,
				R.drawable.icon_xiaoquan_nor);
		Bottomtab5_Util.setImageResource(5, R.drawable.icon_xiaotan_pre,
				R.drawable.icon_xiaotan_nor);

		// 设置标签文本
		Bottomtab5_Util.setText(1, "知道");
		Bottomtab5_Util.setText(2, "我");
		Bottomtab5_Util.setText(3, "想知道");
		Bottomtab5_Util.setText(4, "测试4");
		Bottomtab5_Util.setText(5, "测试5");

		// 设置标签文本颜色normal 正常时的颜色press 按下时的颜色
		Bottomtab5_Util.setTextColor(Color.BLACK, Color.BLUE);

		// 如果是第几个界面被点击
		Bottomtab5_Util.setOnBackViewIndex(new MyViewIndex() {
			public void onIndex(int index) {
				Toast.makeText(getApplicationContext(), index + "", 0).show();
			}
		});

		// 1.启动框架,得到View对象
		Bottomtab5_Util.startForView(new MyViewBack() {
			public void onViewBack(View v) {
				setContentView(v);
			}
		});

		// 2.启动框架,以activity的方式启动
		// 需要在manifest声明com.hrj.kuanjia.bottomtab5.Bottomtab5Activity
		// Bottomtab5_Util.startForActivity();
	}

}
