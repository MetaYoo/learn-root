

<#macro repeat count>
    <#list 1..count as x>
        <#nested x, x/2, x==count>
    </#list>
</#macro>

<@repeat count=4; a,b,c>
    ${a}.${b}, <#if c>last</#if>
</@repeat>
