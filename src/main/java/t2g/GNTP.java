package t2g;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import com.google.code.jgntp.Gntp;
import com.google.code.jgntp.GntpApplicationInfo;
import com.google.code.jgntp.GntpClient;
import com.google.code.jgntp.GntpNotification;
import com.google.code.jgntp.GntpNotificationBuilder;
import com.google.code.jgntp.GntpNotificationInfo;

public enum GNTP {
	INSTANCE;

	private GntpApplicationInfo applicationInfo = null;

	private GntpClient client = null;

	private GntpNotificationInfo status = null;

	private GntpNotificationInfo retweet = null;

	private GntpNotificationInfo favorite = null;

	private GntpNotificationInfo t2gNotification = null;

	public void initialize() throws IOException {
		RenderedImage image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("icon-growl.png"));

		this.applicationInfo = Gntp.appInfo("Tweet2Growl").icon(image).build();
		this.client = Gntp.client(this.applicationInfo).forHost("localhost").build();

		// GntpNotificationInfo を1つ以上定義しないと、register()しても登録されない
		this.status = Gntp.notificationInfo(applicationInfo, "status").build();
		this.retweet = Gntp.notificationInfo(applicationInfo, "retweet").build();
		this.favorite = Gntp.notificationInfo(applicationInfo, "favorite").build();
		this.t2gNotification = Gntp.notificationInfo(applicationInfo, "t2gNotification").build();

		this.client.register();
	}

	/** ストリーム受信開始等、アプリケーションからの通知に使用する */
	public void t2gNotification(String text) {
		GntpNotification notification = new GntpNotificationBuilder(t2gNotification, "Tweet2Growl").text(text).build();

		this.client.notify(notification);
	}

	/** フォロー、アンフォローの通知に使用する */
	public void followAction(String text) {
		GntpNotification notification = new GntpNotificationBuilder(t2gNotification, "Tweet2Growl").text(text).sticky(true).build();

		this.client.notify(notification);
	}

	/** favorite系統の通知に使用する */
	public void favoriteAction(String screenName, String icon, String text) {
		
		GntpNotification notification = null;
		try {
			URI iconUrl = new URI(icon);
			notification = new GntpNotificationBuilder(favorite, screenName).text(text).icon(iconUrl).build();
		} catch (URISyntaxException e) {
			notification = new GntpNotificationBuilder(favorite, screenName).text(text).build();
		}
		
		this.client.notify(notification);
	}

	/** status系統の通知に使用する */
	public void status(String screenName, String icon, String text) {

		GntpNotification notification = null;
		try {
			URI iconUrl = new URI(icon);
			notification = new GntpNotificationBuilder(status, screenName).text(text).icon(iconUrl).build();
		} catch (URISyntaxException e) {
			notification = new GntpNotificationBuilder(status, screenName).text(text).build();
		}

		this.client.notify(notification);
	}

	/** retweet系統の通知に使用する */
	public void retweet(String screenName, String icon, String text) {

		GntpNotification notification = null;
		try {
			URI iconUrl = new URI(icon);
			notification = new GntpNotificationBuilder(retweet, screenName).text(text).icon(iconUrl).build();
		} catch (URISyntaxException e) {
			notification = new GntpNotificationBuilder(retweet, screenName).text(text).build();
		}

		this.client.notify(notification);
	}
}
