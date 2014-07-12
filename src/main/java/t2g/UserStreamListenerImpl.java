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
		// TODO 自動生成されたメソッド・スタブ

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
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUnfavorite(User source, User target, Status unfavoritedStatus) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onFollow(User source, User followedUser) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onUnfollow(User source, User unfollowedUser) {
		// TODO 自動生成されたメソッド・スタブ

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
