;(function() {

	function Base64Binary () {
		var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

		var exports = {};
	
		exports.decodeArrayBuffer = function(input) {
			var bytes = (input.length/4) * 3;
			var ab = new ArrayBuffer(bytes);
			this.decode(input, ab);
			return ab;
		};
		
		exports.decode = function(input, arrayBuffer) {
			//get last chars to see if are valid
			var lkey1 = keyStr.indexOf(input.charAt(input.length-1));		 
			var lkey2 = keyStr.indexOf(input.charAt(input.length-2));		 
		
			var bytes = (input.length/4) * 3;
			if (lkey1 == 64) bytes--; //padding chars, so skip
			if (lkey2 == 64) bytes--; //padding chars, so skip
			
			var uarray;
			var chr1, chr2, chr3;
			var enc1, enc2, enc3, enc4;
			var i = 0;
			var j = 0;
			
			if (arrayBuffer)
				uarray = new Uint8Array(arrayBuffer);
			else
				uarray = new Uint8Array(bytes);
			
			input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
			
			for (i=0; i<bytes; i+=3) {	
				//get the 3 octects in 4 ascii chars
				enc1 = keyStr.indexOf(input.charAt(j++));
				enc2 = keyStr.indexOf(input.charAt(j++));
				enc3 = keyStr.indexOf(input.charAt(j++));
				enc4 = keyStr.indexOf(input.charAt(j++));
		
				chr1 = (enc1 << 2) | (enc2 >> 4);
				chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
				chr3 = ((enc3 & 3) << 6) | enc4;
		
				uarray[i] = chr1;			
				if (enc3 != 64) uarray[i+1] = chr2;
				if (enc4 != 64) uarray[i+2] = chr3;
			}
			return uarray;	
		};
		return exports;
	}
    var moduleName = new Base64Binary();
    if (typeof module !== 'undefined' && typeof exports === 'object') {
        module.exports = moduleName;
    } else if (typeof define === 'function' && (define.amd || define.cmd)) {
        define(function() { return moduleName; });
    } else {
        this.moduleName = moduleName;
    }
}).call(function() {
    return this || (typeof window !== 'undefined' ? window : global);
});