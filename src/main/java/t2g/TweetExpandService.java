package t2g;

import twitter4j.Status;

public enum TweetExpandService {
	INSTANCE;

	public String expandTweet (String text, Status status) {
		return text;
	}
}
