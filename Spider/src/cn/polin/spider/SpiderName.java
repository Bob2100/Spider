package cn.polin.spider;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SpiderName {

	// 主面板
	JFrame jFrame = new JFrame("蜘蛛点名");

	// 名字
	String[] stuName = { "刘德华", "张家辉", "林青霞", "梁朝伟", "范冰冰" };

	// 用于存储名字的标签
	JLabel nameJlabel = new JLabel();

	// 按钮
	JButton jButton = new JButton("开始");

	// 采用的是伪随机数，大家也可以不用这个，这个在网上可以找到java随机数的设置
	Random random = new Random();

	public void init() {
		// 窗口叉掉，程自动关闭
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 提示标签页面
		JLabel jt = new JLabel("欢迎使用蜘蛛点名器");

		// 设置标签居中
		jt.setHorizontalAlignment(SwingConstants.CENTER);

		// 设置字体大小
		jt.setFont(new Font(null, 3, 20));

		// 设置名字显示的标签居中
		nameJlabel.setHorizontalAlignment(SwingConstants.CENTER);


		// 去除按钮文字边框
		jButton.setFocusPainted(false);

		// 设置按钮大小
		jButton.setSize(300, 200);

		// jButton绑定监听事件
		jButton.addActionListener(new ActionListener() {

			// 控制变量
			boolean flag = false;
			Timer timer;

			// 点击jButton按钮，执行该方法
			@Override
			public void actionPerformed(ActionEvent e) {

				// 创建定时器
				TimerTask timerTask = new TimerTask() {
					@Override
					public void run() {
						
						// 点名
						randomRollCall(nameJlabel);

					}

				};
				
				if (flag) {// 停止
					timer.cancel();
					timer = null;// 通知虚拟机回收垃圾
					flag = false;

					// 设置按钮的值
					jButton.setText("开始");

				} else {// 开始

					// 每50毫秒执行一次
					timer = new Timer();// timer cancel后没法再schedule，需要重新赋值
					timer.schedule(timerTask, 0, 50);
					flag = true;
					jButton.setText("停止");
				}
			}

		});
		// 获取JFrame的面板
		Container p = jFrame.getContentPane();

		// 设置布局方式，我采用的BorderLayout布局
		p.setLayout(new BorderLayout(3, 1));

		// 添加提示标签在北方
		p.add(jt, BorderLayout.NORTH);

		// 添加姓名标签在中央
		p.add(nameJlabel, BorderLayout.CENTER);

		// 添加按钮控件在南方
		p.add(jButton, BorderLayout.SOUTH);

		// 运行jFrame
		jFrame.pack();

		// 窗口居中
		setJFrameCenter(jFrame, 400, 247);

		// 设置可以显示
		jFrame.setVisible(true);

	}

	// 设置JFrame在显示器居中
	private void setJFrameCenter(JFrame jFrame, int width, int height) {
		jFrame.setSize(width, height);// 窗口大小
		int frameWidth = jFrame.getWidth(); // 获得窗口宽
		int frameHeight = jFrame.getHeight(); // 获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		jFrame.setLocation(screenWidth / 2 - frameWidth / 2, screenHeight / 2
				- frameHeight / 2);// 设置窗口居中显示
	}

	// 获取随机的姓名
	private String getRandomName() {
		int a = 0;
		// random类去实现随机数时，只能设置上限，也就是说随机数产生的都是0-stuName.length之间的数字
		a = random.nextInt(stuName.length);
		// rd.setSeed();
		// a = (int)Math.random()*stuName.length;
		return stuName[a];
	}

	// 随机点名
	private void randomRollCall(JLabel nameJlabel) {
		// 获取随机的姓名
		String n = getRandomName();
		// 设置nameJlabel标签的文字
		nameJlabel.setText(n);

		// 设置字体
		nameJlabel.setFont(new java.awt.Font(n, 1, 35));
		// 设置字体颜色
		nameJlabel.setForeground(Color.red);
	}
}
