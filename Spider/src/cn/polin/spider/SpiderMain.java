package cn.polin.spider;

import javax.swing.SwingUtilities;

public class SpiderMain {
	public static void main(String[] args) {

		// 显示应用 GUI
		// 创建并显示GUI。出于线程安全的考虑， 这个方法在事件调用线程中调用。
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SpiderName sn = new SpiderName();
				sn.init();
			}
		});
	}
}
