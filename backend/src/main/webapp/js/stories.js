learningApi.controller('NewStoryCtrl', function($scope,$window,$timeout,GApi) {

    /** Fields variables **/

    $scope.components = ['RAS','LSMS','EDI'];
    $scope.participants = ['1','2','3','4','5','6','7','8','9','10','11','12'];
    $scope.results = null;
    $scope.updateFlag = false;
    $scope.showError = false;
    $scope.doFade = false;
    $scope.story = {
        id : null,
        component : null,
        storyName : null,
        sprint : null,
        participants : null,
        reg_date : null
    };

    /** Angular functions **/

    $scope.fakeError = function(msg) {
        //reset
        $scope.showError = false;
        $scope.doFade = false;

        $scope.showError = true;
        $scope.errorMessage = msg;
        $timeout(function() {
          $scope.doFade = true;
        }, 2500);
    };

    $scope.stories = function() {
      $scope.updateFlag = false;
      GApi.executeAuth('storyApi', 'stories.list').then(function(resp) {
              $scope.results = resp.items;
          }, function() {
            $scope.fakeError("You must be authenticated");
          });
    };

    $scope.submit = function(isValid) {
        // validate the form
        if (!isValid) {
            $scope.fakeError('Sorry! You must fill in all fields!');
            return;
        }

        // execute action
        if ($scope.updateFlag) {
            update();
        } else {
            save();
        }
    }

    $scope.remove = function(id) {
        var answer = $window.confirm("Do you have sure?");
        if (answer) {
            doRemove(id);
        }
    }

    $scope.prepareUpdate = function(id) {
        GApi.executeAuth('storyApi', 'story.by.id', {id : id}).then(function(resp) {
            $scope.updateFlag = true;
            fillStory(resp);
        }, function() {
            $scope.fakeError("You must be authenticated");
        });
    }

    /** Javascript functions **/

    function doRemove(id) {
        GApi.executeAuth('storyApi', 'story.remove', {id : id}).then(function(resp) {
            $scope.stories();
        }, function() {
            $scope.fakeError("You must be authenticated");
        });
    }

     function save() {
        GApi.executeAuth('storyApi', 'story.save', $scope.story).then(function(resp) {
            $scope.stories();
            clear();
        }, function() {
            $scope.fakeError("You must be authenticated");
        });
     }

     function update() {
        GApi.executeAuth('storyApi', 'story.update', $scope.story).then(function(resp) {
            $scope.stories();
            clear();
        }, function() {
            $scope.fakeError("You must be authenticated");
        });
     }

    function fillStory(storyLoaded) {
        $scope.story.id = storyLoaded.id;
        $scope.story.component = storyLoaded.component;
        $scope.story.storyName = storyLoaded.storyName;
        $scope.story.sprint = storyLoaded.sprint;
        $scope.story.participants = storyLoaded.participants;
        $scope.story.reg_date = storyLoaded.reg_date;
    }

    function clear() {
        $scope.story.id = null;
        $scope.story.component = $scope.components[0];
        $scope.story.participants = $scope.participants[0];
        $scope.story.storyName = null;
        $scope.story.sprint = null;
    }

    // load stories when open the page
    $scope.stories();
});
