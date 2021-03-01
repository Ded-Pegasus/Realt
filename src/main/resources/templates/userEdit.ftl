<#import "parts/common.ftl" as c>

<@c.page>
    User editor
    <form action="/user" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="text" name="username" value="${user.username}">
        <input type="hidden" value="${user.id}" name="userId">
        <button type="submit">Save</button>
    </form>
</@c.page>