package com.Tab.TT.Entity;

public class Group {
		private   int groupId;
		private   String groupName;
		private   String groupType;
		private   int  groupNumber;
		private   int  userId;
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getGroupNumber() {
			return groupNumber;
		}
		public void setGroupNumber(int groupNumber) {
			this.groupNumber = groupNumber;
		}
		public int getGroupId() {
			return groupId;
		}
		public void setGroupId(int groupId) {
			this.groupId = groupId;
		}
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public String getGroupType() {
			return groupType;
		}
		public void setGroupType(String groupType) {
			this.groupType = groupType;
		}
}
