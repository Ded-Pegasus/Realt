<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    ${listing!" "}
    <@l.login "/registration" true/>
</@c.page>