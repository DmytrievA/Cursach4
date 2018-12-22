<#import "parts/common.ftl" as com>
<#import "parts/grouptaskdetails.ftl" as gtd>

<@com.page>
<@gtd.groupTask task />
    <form method="post" action="/grouptaskdetails">
        <if mes??>
            <p style="color: red;"><h4>Поле "ответ" должно быть заполнено!</h4></p>
        </if>
        <label for="res"> Ответ*: </label>
        <input id="res" type="text" name="result" placeholder="paste link for result"/><br/>
        <input type="submit" name = "submitAction" value="Done"/><br/>
        <button id="2" type="button" onclick="showElem()" value="Refuse">Refuse</button>
        <div style="display: none" id="hi">
            <input type="text" name="comments" placeholder="Why refuse?=("/><br/>
            <input type="submit" name = "submitAction" value="Refuse"/>
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
</@com.page>