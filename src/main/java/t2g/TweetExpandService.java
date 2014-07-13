package t2g;

import java.util.Arrays;
import java.util.regex.Pattern;

import twitter4j.Status;
import twitter4j.URLEntity;

public enum TweetExpandService {
	INSTANCE;

	public String expandTweet (String text, Status status) {

		String expandTweetText = Arrays
				.stream(status.getURLEntities())
				.map(ExpandURLEntity::new)
				.reduce(text,
						(expandingText, entity) ->  expandingText.replaceFirst(entity.tcoURL, entity.originalURL) ,
						(expandingText1, expandingText2) -> expandingText2
						);

		return expandTweetText;
	}

	private class ExpandURLEntity {
		/** 置換前URL(t.co形式) */
		final String tcoURL;

		/** 置換後URL */
		final String originalURL;

		public ExpandURLEntity(URLEntity entity) {
			// String#replaceXXX を用いて文字列置換を行うため、置換前文字列をエスケープ処理が必要
			this.tcoURL = Pattern.quote(entity.getURL());
			this.originalURL = entity.getExpandedURL();
		}

	}
}
