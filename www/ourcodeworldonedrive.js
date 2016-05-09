/*global cordova, module*/

module.exports = {
    createOneDriveManager: function(){
        var _settings = {
            appId : null
        };

        var _callbacks = {
            onFileSelected: null,
            onCancel:null,
            onError:null
        };

        return {
            setAppId: function(id){
                _settings.appId = id;
            },
            showPicker: function(){
                if(!_settings.appId){
                    throw new Error("You need to give an onedriveapp id, use setAppId method");
                }

                cordova.exec(function(data){
                    console.info("Succeso !");
                    var _processedData = JSON.parse(data);
                    if(typeof(_callbacks.onFileSelected) == "function"){
                        _callbacks.onFileSelected(_processedData);
                    }
                }, function(err){
                    console.error(err);
                }, "OurCodeWorldOneDriveFilePicker", "showpicker", [_settings]);
            },
            onFileSelected: undefined,
            onCancel: undefined,
            onError: undefined
        };
    }
};
