package t2g;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class AuthorizeCheck {

	public static void main(String[] args) {
		Twitter twitter = TwitterFactory.getSingleton();

		try {
			User verifyCredentials = twitter.verifyCredentials();

			System.out.println("Twitterの認証に成功しました。");
			System.out.println("ユーザー名：" + verifyCredentials.getScreenName());

		} catch (TwitterException e) {
			System.out.println("Twitterの認証に失敗しました。");
			System.out.println("twitter4j.propertiesの設定項目を再確認してください。");
			System.out.println("設定項目の詳細については以下のアドレスをご参照ください。");
			System.out.println("http://hako-fuusen.github.io/Tweet2Growl/accesskey.html");
		};
	}

}
