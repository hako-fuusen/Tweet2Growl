package t2g;

import java.awt.image.RenderedImage;
import java.io.IOException;

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

	@SuppressWarnings("unused")
	private GntpNotificationInfo status = null;

	private GntpNotificationInfo t2gNotification = null;

	public void initialize() throws IOException {
		RenderedImage image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("icon-growl.png"));

		this.applicationInfo = Gntp.appInfo("Tweet2Growl").icon(image).build();
		this.client = Gntp.client(this.applicationInfo).forHost("localhost").build();

		// GntpNotificationInfo を1つ以上定義しないと、register()しても登録されないので暫定で設定
		this.status = Gntp.notificationInfo(applicationInfo, "status").build();
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
}
