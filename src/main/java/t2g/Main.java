package t2g;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class Main {

	public static void main(String[] args) throws AWTException, IOException, URISyntaxException {
		setSystemTrayIcon();

		GNTP.INSTANCE.initialize();
		NGWordService.INSTANCE.initialize();

		TwitterStream twitterStream = TwitterStreamFactory.getSingleton();
		twitterStream.addListener(new UserStreamListenerImpl());
		twitterStream.user();
	}

	private static void setSystemTrayIcon() throws AWTException, IOException {
		// 参考ソース http://d.hatena.ne.jp/fuzzhead/20081027/p1
		Image image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("icon-tray.png"));

		// それ以降は基本的にjdk8のドキュメントのサンプルから
		MenuItem exitMenuItem = new MenuItem("終了");
		exitMenuItem.addActionListener(e -> System.exit(0));

		PopupMenu popup = new PopupMenu();
		popup.add(exitMenuItem);

		TrayIcon trayIcon = new TrayIcon(image, "Tweet2Growl", popup);

		SystemTray systemTray = SystemTray.getSystemTray();
		systemTray.add(trayIcon);
	}

}
