<#import "parts/common.ftl" as com>
<#import "parts/shortgrouptaskdetails.ftl" as sgtd>

<@com.page>
    <div class="row">
    <div class="mx-auto col-lg-6 col-xl-6 col-md-6">
        <#list tasks as task>
            <@sgtd.groupTask task "/grouptaskdetails"/>
        </#list>
    </div>
    </div>
</@com.page>