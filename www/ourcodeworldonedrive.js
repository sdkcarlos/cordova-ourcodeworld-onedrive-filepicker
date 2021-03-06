/*global cordova, module*/

module.exports = {
    createOneDriveManager: function(){
        var _settings = {
            appId : null,
            linkMode: "download"
        };

        return {
            setAppId: function(id){
                _settings.appId = id;
            },
            setLinkMode: function(mode){
                _settings.linkMode = mode;
            },
            showPicker: function(){
                var instance = this;

                if(!_settings.appId){
                    throw new Error("You need to give an onedriveapp id, use setAppId method");
                }

                cordova.exec(function(data){
                    if(!data){
                        if(typeof(instance.onCancel) == "function"){
                            instance.onCancel();
                        }
                        return;
                    }

                    var _processedData = JSON.parse(data);

                    if(typeof(instance.onFileSelected) == "function"){
                        instance.onFileSelected(_processedData);
                    }

                }, function(err){
                    if(typeof(instance.onError) == "function"){
                        instance.onError(err);
                    }
                    console.error(err);
                }, "OurCodeWorldOneDriveFilePicker", "showpicker", [_settings]);
            },
            showSaver:function(filename,filepath){
                cordova.exec(function(data){
                     console.log(data);
                }, function(err){
                    if(typeof(instance.onError) == "function"){
                        instance.onError(err);
                    }
                    console.error(err);
                }, "OurCodeWorldOneDriveFilePicker", "savefile", [_settings]);
            },
            onFileSelected: undefined,
            onCancel: undefined,
            onError: undefined
        };
    }
};
