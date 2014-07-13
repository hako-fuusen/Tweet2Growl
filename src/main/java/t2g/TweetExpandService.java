package t2g;

import java.util.Arrays;
import java.util.regex.Pattern;

import twitter4j.Status;

public enum TweetExpandService {
	INSTANCE;

	public String expandTweet (String text, Status status) {

		String expandTweetText = Arrays
				.stream(status.getURLEntities())
				.reduce(text,
						(expandingText, entity) ->  expandingText.replaceAll(Pattern.quote(entity.getURL()), entity.getExpandedURL()) ,
						(expandingText1, expandingText2) -> expandingText2
						);

		return expandTweetText;
	}
}
