Ext.ns('Ext.ux');
Ext.ns('Ext.ux.ajax');

Ext.ux.Element = function(_url, _success, _method) {
    this.url = _url;
    this.success = _success;
    this.method = (_method != null) ? _method : 'post';
};

Ext.ux.Element.prototype = {	
    load: function(params){
        Ext.Ajax.request({
            url: this.url,
            method:this.method,
            success:this.success,
            callback: params.callback,
            scope: params.scope
        });
    }, getStore: function(){
        return null;
    }
};

Ext.ux.ajax.pool = function(){
    var elements = new Array();
    var currentIndex = 0;
    var length;
    var response;
	
    var call = function(){
        var object = elements[currentIndex];
        var storeOb;
        if( object != null){
            try {
                storeOb =object.getStore();
            }catch(e) {
                storeOb = object.getStore();
            }
            if( storeOb != null) {
                storeOb.load({
                    scope:this, 
                    callback: function(){
                        currentIndex++;
                        if( currentIndex < length) call();
                    }
                });
            } else {
                object.load({
                    scope:this, 
                    callback: function(){
                        currentIndex++;
                        if( currentIndex < length) call();
                    }
                });
            }
        } else { 
            currentIndex++; 
            if( currentIndex < length) call(); 
        }
    };
	
    this.init = function(){
        length = elements.length;
        call();
    };
	
    this.appendObject = function(object){
        elements.push( object );
    };
};