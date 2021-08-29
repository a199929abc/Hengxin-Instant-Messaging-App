window.app ={
	/**
	 * back end server port and url
	 */
	nettyServerUrl: 'ws://192.168.0.12:8081/ws',
	serverUrl : 'http://192.168.0.12:8080',
	/**
	 * Image server port and url
	 */
	imgServerUrl : 'http://18.220.84.169:88/group1/',
	/**
	 * @description  descide whether the user entered string is empty
	 * 
	 * @param {Object} str
	 * @return true is not empty false otherwise 
	 */
	isNotNull: function(str) {
		if (str != null && str != "" && str != undefined) {
			return true;
		}
		return false;
	},
	
	/**
	 * package DEFAULT HTML5 提示框
	 * @param {Object} msg
	 * @param {Object} type
	 */
	showToast : function(msg,type){
		plus.nativeUI.toast(msg,{icon:"image/"+type+".png",verticalAlign:"bottom" },{duration:"long"})
		 
	},
	/**
	 * @param {Object} user Store user global object 
	 */
	setUserGlobalInfo : function(user){
		var userInfoStr = JSON.stringify(user);
		plus.storage.setItem("userInfo",userInfoStr);
	},
	getUserGlobalInfo : function(){
		var userInfoStr = plus.storage.getItem("userInfo");
		return JSON.parse(userInfoStr);
	},
	
	userLogout: function(){
		plus.storage.removeItem("userInfo");
		
	},
	/**
	 * 保存用户的联系人列表
	 * @param {Object} contactList
	 */
		setContactList: function(contactList) {
		var contactListStr = JSON.stringify(contactList);
		plus.storage.setItem("contactList", contactListStr);
	},
	
	/**
	 * 获取本地缓存中的联系人列表
	 */
		getContactList: function() {
		var contactListStr = plus.storage.getItem("contactList");
		
		if (!this.isNotNull(contactListStr)) {
			return [];
		}
		
		return JSON.parse(contactListStr);
	},
	/**
	 * 和后端枚举对应
	 */
	CONNECT: 1, 
	CHAT: 2,
	SIGNED: 3,
	KEEPALIVE: 4,
	
	ChatMsg: function(senderId,receiverId, msg, msgId){
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.msg = msg;
		this.msgId =msgId;
	},
	/**
	 * @param {Object} action
	 * @param {Object} extand
	 * @param {Object} chatMsg 构建消息模型对象
	 */
	DataContent: function(action,extand, chatMsg){
		this.action = action;
		this.extand = extand;
		this.chatMsg = chatMsg;
	
	}
	
}