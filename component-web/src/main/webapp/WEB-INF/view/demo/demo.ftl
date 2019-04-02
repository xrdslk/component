<#import "/master/master-html.ftl" as html/>
<@html.html>
	<@html.head title="" description="" keywords=" ">
    </@html.head>
<body>    
<div id='header'>
    <form id="form" role="form" method="post" action="${basePath}/demo/view">
      <div class="form-group">
        <label for="businessId">businessId</label>
        <input type="text" class="form-control" id="businessId" name="businessId" value="${(response.businessId)!''}"></input>
      </div>
      <div class="form-group">
        <label for="businessType">businessType</label>
        <input type="text" class="form-control" id="businessType" name="businessType" value="${(response.businessType)!''}"></input>
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>
</body>
</@html.html>
