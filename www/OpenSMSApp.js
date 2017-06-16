var exec = require('cordova/exec');

var OpenSMSApp = {
	share : function(options, successCallback, errorCallback) {

		for(var options_key in options){
			if(typeof options[options_key] === 'object'){
				for(var key in options[options_key]){
					options[options_key][key] = options[options_key][key] || '';
				};
			}else{
				options[options_key] = options[options_key] || '';
			}
		};
	    exec(successCallback, errorCallback, "OpenSMSApp", "share", [options]);
	}
};

module.exports = OpenSMSApp;