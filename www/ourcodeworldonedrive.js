/*global cordova, module*/

module.exports = {
    createOneDriveManager: function(){
        var _settings = {
            appId : null
        };

        return {
            setAppId: function(id){
                _settings.appId = id;
            },
            showPicker: function(){
                var instance = this;
                if(!_settings.appId){
                    throw new Error("You need to give an onedriveapp id, use setAppId method");
                }

                cordova.exec(function(data){
                    var _processedData = JSON.parse(data);
                    if(typeof(instance.onFileSelected) == "function"){
                        instance.onFileSelected(_processedData);
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
