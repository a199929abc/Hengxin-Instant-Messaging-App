window.app ={
	/**
	 * back end server port and url
	 */
	serverUrl : 'http://142.104.17.117:8080',
	/**
	 * Image server port and url
	 */
	imgServerUrl : 'http://142.104.17.103:88/group1/',
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
	
}