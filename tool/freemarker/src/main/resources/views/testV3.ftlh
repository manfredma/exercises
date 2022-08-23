<#assign name = "Robert">

    His name is ${name}.

<#assign value = 4>

<#if value < 0>
    The number is negative
<#elseif value == 0>
    The number is zero
<#else>
    The number is positive
</#if>

<#assign colours = ["red", "green", "blue", "yellow"]>

<#list colours as col>
    ${col}
</#list>

<#assign items = {"pens": 3, "cups": 2, "tables": 1}>

<#list items?values as v>
    ${v}
</#list>

<#list items?keys as k>
    ${k}
</#list>

