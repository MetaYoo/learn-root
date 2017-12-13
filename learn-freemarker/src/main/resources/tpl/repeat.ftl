<#assign x = 1>

一个参数：
<@repeat count=4>
Test ${x}
    <#assign x = x + 1>
</@repeat>

二个参数：
<@repeat count=3 hr=true>
Test
</@repeat>

循环变量：
<@repeat count=3; cnt>
${cnt}. Test
</@repeat>