<#import "parts/common.ftl" as com>
<#import "parts/grouptaskdetails.ftl" as gtd>

<@com.page>
    <div class="mx-auto col-lg-4 col-md-4 col-sm-4">
    <@gtd.groupTask task />

        <form method="post" action="/grouptaskdetails" enctype="multipart/form-data">
            <#if mes??>
                <p style="color: red;"><h4>Поле "ответ" или "файл" должно быть заполнено!</h4></p>
            </#if>
            <label for="res"> Ответ*: </label>
            <input id="res" type="text" name="result" placeholder="paste link for result"/><br/>
            <input type="file" name="file"/><br/>
            <input type="submit" name = "submitAction" class="btn btn-primary"value="Done" /><br/>
            <button id="2" type="button" onclick="showElem()" value="Refuse">Refuse</button>
            <div style="display: none" id="hi">
                <input type="text" name="comments" placeholder="Why refuse?=("/><br/>
                <input type="submit" name = "submitAction" class="btn btn-primary" value="Refuse" />
            </div>
            <input type="hidden" name="task" value="${task.id}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>

    <script>
        function showElem() {
            document.getElementById("hi").style.display = "block";
            document.getElementById("2").style.display = "none";
        }
    </script>
    </div>
</@com.page>