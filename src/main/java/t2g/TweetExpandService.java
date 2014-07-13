package t2g;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import twitter4j.Status;
import twitter4j.URLEntity;

public enum TweetExpandService {
	INSTANCE;

	/** 本文中の短縮URL( t.co 形式のみ) を展開する */
	public String expandTweet(String text, Status status) {

		String expandTweetText = Stream
				.concat(Arrays.stream(status.getURLEntities()), Arrays.stream(status.getMediaEntities()))
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
			// String#replaceXXX を用いて文字列置換を行うため、置換前文字列に対してエスケープ処理が必要
			this.tcoURL = Pattern.quote(entity.getURL());
			this.originalURL = entity.getExpandedURL();
		}

	}
}
