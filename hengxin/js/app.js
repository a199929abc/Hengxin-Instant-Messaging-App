window.app ={
	/**
	 * server port and url
	 */
	serverUrl : 'http://192.168.0.18:8080',
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
	
	showToast : function(msg,type){
		plus.nativeUI.toast(msg,{icon:"image/"+type+".png",verticalAlign:"bottom" },{duration:"long"})
		 
	}
}