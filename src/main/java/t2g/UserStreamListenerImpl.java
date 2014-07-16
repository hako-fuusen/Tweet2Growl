package t2g;

import twitter4j.DirectMessage;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;

public class UserStreamListenerImpl implements UserStreamListener {

	@Override
	public void onStatus(Status status) {
		if (status.isRetweet()) {
			onRetweet(status, status.getRetweetedStatus());
			return;
		}

		final User user = status.getUser();
		final String screenName = user.getScreenName();
		final String profileImageUrl = user.getProfileImageURL();
		final String text = status.getText();

		// t.co 展開
		final String expandText = TweetExpandService.INSTANCE.expandTweet(text, status);

		// NGワードチェック
		if(NGWordService.INSTANCE.constainNGWord(expandText)) {
			return;
		}

		GNTP.INSTANCE.status(screenName, profileImageUrl, expandText);
	}

	private void onRetweet(Status status, Status retweetedStatus) {
		// 発言をRTしたユーザーの情報
		final User user = status.getUser();
		final String screenName = user.getScreenName();
		final String profileImageUrl = user.getProfileImageURL();

		// 発言をRTされたユーザーの情報
		final User rtUser = retweetedStatus.getUser();
		final String rtScreenName = rtUser.getScreenName();
		final String rtText = retweetedStatus.getText();

		// t.co 展開
		final String expandText = TweetExpandService.INSTANCE.expandTweet(rtText, status);

		// NGワードチェック
		if(NGWordService.INSTANCE.constainNGWord(expandText)) {
			return;
		}

		// 最終的にGNTPで通知される文字列
		final String text = String.format("[Retweet] @%s : %s", rtScreenName, expandText);

		GNTP.INSTANCE.retweet(screenName, profileImageUrl, text);
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onScrubGeo(long userId, long upToStatusId) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onStallWarning(StallWarning warning) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onException(Exception ex) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onDeletionNotice(long directMessageId, long userId) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onFriendList(long[] friendIds) {
		GNTP.INSTANCE.t2gNotification("Streaming start.");
	}

	@Override
	public void onFavorite(User source, User target, Status favoritedStatus) {
		final String srcUserScreenName = source.getScreenName();
		final String dstUserScreenName = target.getScreenName();
		final String statusText = favoritedStatus.getText();
		final String srcUserProfileImageUrl = source.getProfileImageURL();

		final String text = String.format("[Favorite] @%s :\n %s", dstUserScreenName, statusText);

		GNTP.INSTANCE.favoriteAction(srcUserScreenName, srcUserProfileImageUrl, text);
	}

	@Override
	public void onUnfavorite(User source, User target, Status unfavoritedStatus) {
		final String srcUserScreenName = source.getScreenName();
		final String dstUserScreenName = target.getScreenName();
		final String statusText = unfavoritedStatus.getText();
		final String srcUserProfileImageUrl = source.getProfileImageURL();

		final String text = String.format("[Unfavorite] @%s :\n %s", dstUserScreenName, statusText);

		GNTP.INSTANCE.favoriteAction(srcUserScreenName, srcUserProfileImageUrl, text);
	}

	@Override
	public void onFollow(User source, User followedUser) {
		final String srcUserScreenName = source.getScreenName();
		final String followedUserScreenName = followedUser.getScreenName();

		String text = String.format("@%s was followed @%s", srcUserScreenName, followedUserScreenName);

		GNTP.INSTANCE.followAction(text);
	}

	@Override
	public void onUnfollow(User source, User unfollowedUser) {
		final String srcUserScreenName = source.getScreenName();
		final String unfollowedUserScreenName = unfollowedUser.getScreenName();

		String text = String.format("@%s was unfollowed @%s", srcUserScreenName, unfollowedUserScreenName);

		GNTP.INSTANCE.followAction(text);
	}

	@Override
	public void onDirectMessage(DirectMessage directMessage) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListMemberAddition(User addedMember, User listOwner,
			UserList list) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListMemberDeletion(User deletedMember, User listOwner,
			UserList list) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListSubscription(User subscriber, User listOwner,
			UserList list) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListUnsubscription(User subscriber, User listOwner,
			UserList list) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListCreation(User listOwner, UserList list) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListUpdate(User listOwner, UserList list) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserListDeletion(User listOwner, UserList list) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUserProfileUpdate(User updatedUser) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onBlock(User source, User blockedUser) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUnblock(User source, User unblockedUser) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
