<#import "parts/common.ftl" as com>
<#import "parts/grouptaskdetails.ftl" as gtd>

<@com.page>
    <div class="row">
        <div class="mx-auto col-lg-6 col-xl-6 col-md-6">
        <@gtd.groupTask task/>
    </div></div>
    <div class="row">
        <div class="mx-auto col-lg-6 col-xl-6 col-md-6 col-xs-6">
            <form method="post" class="form-group">
                <input type="hidden" name="task" value="${task.id}"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="submit"  class="btn btn-primary" name = "submitAction" value="Accept"/>
                <button id="2" class="btn btn-primary" type="button" onclick="showElem()" value="Refuse">Refuse</button>
                <div style="display: none" id="1">
                    <input type="text" name="comments" placeholder="Why refuse?=("/><br/>
                    <input type="submit" class="btn btn-primary" name = "submitAction" value="Refuse"/>
                </div>
            </form>
        </div>
    </div>
    <script>
        function showElem() {
            document.getElementById("1").style.display = "block";
            document.getElementById("2").style.display = "none";
        }
    </script>
</@com.page>