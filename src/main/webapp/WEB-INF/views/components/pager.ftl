<#macro pager paginator additionalClass="" requestParameters="" filterParams="">

<#if paginator.getTotalPages() gt 7>
    <#assign
        totalPages=paginator.getTotalPages()
        pageNumber=paginator.getPageIndex()

        head=(pageNumber>4)?then([1,-1], [1,2,3])
        tail=(pageNumber<totalPages-3)?then([-1,totalPages],[totalPages-2,totalPages-1,totalPages])

        bodyBefore=(pageNumber>4 && pageNumber<totalPages-1)?then([pageNumber-2,pageNumber-1],[])
        bodyAfter=(pageNumber>2 && pageNumber<totalPages-3)?then([pageNumber+1,pageNumber+2],[])

        body=head+bodyBefore+(pageNumber>3 && pageNumber<totalPages-2)?then([pageNumber],[])+bodyAfter+tail
    >
<#elseIf paginator.getTotalPages() gt 0>
    <#assign
        body= 1..paginator.getTotalPages()
    >
<#else>
    <#assign
        body=[1]
    >
</#if>

<ul class="products-pages ${additionalClass}">
    <#list body as page>
        <#if page==-1>
            <li class="products-pages__item"><a href="#" class="products-pages__link">...</a></li>
        <#elseIf page==paginator.getPageIndex()>
            <li class="products-pages__item"><a href="#" class="products-pages__link products-pages__link_selected">${page}</a></li>
        <#else>
            <li class="products-pages__item"><a href="/commodities?page=${page}${requestParameters}" class="products-pages__link">${page}</a></li>
        </#if>
    </#list>
    <#--<li class="products-pages__item"><a href="#" class="products-pages__link">1</a></li>-->
    <#--<li class="products-pages__item"><a href="#" class="products-pages__link products-pages__link_selected">2</a></li>-->
    <#--<li class="products-pages__item"><a href="#" class="products-pages__link">3</a></li>-->
    <#--<li class="products-pages__item"><a href="#" class="products-pages__link">4</a></li>-->
</ul>

</#macro>