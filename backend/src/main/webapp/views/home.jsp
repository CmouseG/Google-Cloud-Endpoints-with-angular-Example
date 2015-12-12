<%@ include file="/includes/header.jsp"%>
<div ng-controller="NewStoryCtrl" style="margin: 0 auto; width: 50%">
    <body>
        <div style="margin: 0 auto; width: 50%; margin-top: 10px; margin-bottom: 20px">
            <h4>Google cloud endpoints with AngularJS</h4>
        </div>
        <hr />
        <div ng-show="showError" ng-class="{fade:doFade}"
            class="alert alert-danger"><strong>Error:</strong> {{errorMessage}}
        </div>
        <form method="post" role="form" name="form" ng-submit="submit(form.$valid)" novalidate>
            <div>
                <div class="form-group" style="margin-left: 35px;">
                    <label>StoryName</label>
                    <div class="input-group">
                        <input type="text" class="form-control"
                            ng-model="story.storyName"
                            style="width: 550px;"
                            required />
                    </div>
                </div>
                <div class="form-group" style="margin-left: 35px;">
                    <label>Sprint</label>
                    <div class="input-group">
                        <input type="text" class="form-control"
                            style="width: 300px;"
                            ng-model="story.sprint"
                            required />
                    </div>
                </div>
                <div class="form-group" style="margin-left: 35px;">
                    <label>Component</label>
                    <div class="input-group">
                        <div style="width: 300px;">
                             <select  class="form-control"
                                ng-model="story.component"
                                ng-init="story.component=components[0]"
                                ng-options="cp for cp in components"
                                required>
                             </select>
                        </div>
                    </div>
                </div>
                <div class="form-group" style="margin-left: 35px;">
                    <label>Participants</label>
                    <div class="input-group">
                        <div style="width: 300px;">
                             <select class="form-control"
                                ng-model="story.participants"
                                ng-init="story.participants=participants[0]"
                                ng-options="part for part in participants"
                                required>
                             </select>
                        </div>
                    </div>
                </div>
            </div>
            <div style="margin-left: 35px; width:  50%">
                <button type="submit" class="btn btn-primary" ng-hide="updateFlag">Save</button>
                <button type="submit" class="btn btn-primary" ng-show="updateFlag">Edit</button>
            </div>
        </form>
        <%@ include file="/includes/data-table.jsp"%>
    </body>
</diV>