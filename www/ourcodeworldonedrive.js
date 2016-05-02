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
                //if(!_settings.appId){
                //    throw new Error("You need to give an onedriveapp id, use setAppId method");
                //}

                cordova.exec(function(data){
                    console.info(data);
                }, function(err){
                    console.error(err);
                }, "OurCodeWorldOneDrive", "showpicker", [_settings]);
            }
        };
    }
};
