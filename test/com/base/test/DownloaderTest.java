package com.base.test;


import com.com.spider.downloader.DownloadManager;
import com.com.spider.downloader.Downloader;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kai.wang on 12/26/13.
 */
public class DownloaderTest {
	public static void main(String args[]) throws MalformedURLException {
//		Downloader downloader = new D
		String[] urls = {
				"https://0.gravatar.com/avatar/061f62c318e67ac73d058ba6a49a092b?d=https%3A%2F%2Fidenticons.github.com%2F8af0430da559cb498895b635500c799e.png&r=x&s=440",
				"https://www.google.com.hk/logos/doodles/2013/holiday-series-2013-3-4504416610680832-res.png",
				"http://personal-demo.u.qiniudn.com/FqfsMLeewqw_7EIk6t1r2app3Gvx",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3",
				"http://personal-demo.u.qiniudn.com/Fvo0dUF8kAWNUAjm8Xjv3FWgudj3"
		};
		for(int i=0;i<10;i++){
			URL url = new URL(urls[i]);
			Downloader download = DownloadManager.getInstance().createDownload(url, "a"+i+".png", "/");
			download.run();
		}
	}
}
