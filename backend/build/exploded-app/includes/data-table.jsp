<div class="table-responsive"
    style="margin-left:20px; margin-right: 40px; margin-top: 20px; width:85%">
  <table class="table table-striped">
  <tr>
      <th style="width: 10%">Component</th>
      <th style="width: 40%">StoryName</th>
      <th style="width: 40%">Sprint</th>
      <th style="width: 5%"></th>
      <th style="width: 5%"></th>
  </tr>
  <tr ng-repeat="result in results" >
      <input type="hidden">{{result.id}}</input>
      <td style="width: 10%">{{result.component}}</td>
      <td style="width: 40%">{{result.storyName}}</td>
      <td style="width: 40%">{{result.sprint}}</td>
      <td><a href="#" ng-click="prepareUpdate(result.id)" style="width: 5%; color: #CDDC39; font-size:11px">edit</a></td>
      <td><a href="#" ng-click="remove(result.id)" style="width: 5%; color: #2196F3; font-size:11px">remove</a></td>
  </tr>
  </table>
</div>