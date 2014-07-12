package t2g;

import java.awt.image.RenderedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.code.jgntp.Gntp;
import com.google.code.jgntp.GntpApplicationInfo;
import com.google.code.jgntp.GntpClient;
import com.google.code.jgntp.GntpNotificationInfo;

public enum GNTP {
	INSTANCE;

	private GntpApplicationInfo applicationInfo = null;

	private GntpClient client = null;

	@SuppressWarnings("unused")
	private GntpNotificationInfo status = null;

	public void initialize() throws IOException {
		RenderedImage image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("icon-growl.png"));

		this.applicationInfo = Gntp.appInfo("Tweet2Growl").icon(image).build();
		this.client = Gntp.client(this.applicationInfo).forHost("localhost").build();

		// GntpNotificationInfo を1つ以上定義しないと、register()しても登録されないので暫定で設定
		this.status = Gntp.notificationInfo(applicationInfo, "status").build();

		this.client.register();
	}

}
