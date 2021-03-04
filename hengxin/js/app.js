window.app ={
	/**
	 * server port and url
	 */
	serverUrl : 'http://142.104.17.46:8080',
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
	}
	
	
}