
function facebookLogin() {
        // get current login status from facebook.com
        FB.getLoginStatus(function(response) {
            if (response.status === 'connected') {
                // the user is logged in and connected to your app
                window.location = "@{HomeController.facebookLogin()}";
            } else {
                window.location = "@{HomeController.facebookLogout()}";
            }
         });
    }