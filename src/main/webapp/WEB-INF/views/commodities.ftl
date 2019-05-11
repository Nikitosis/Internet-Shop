<#include "components/main_header.ftl"/>
<#include "components/main_nav.ftl"/>
<#include "components/main_footer.ftl"/>
<#include "components/commodity.ftl"/>
<#include "components/pager.ftl"/>
<#include "components/filters-item.ftl"/>

<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/resources/css/commodities.css">
    <link rel="stylesheet" href="/resources/css/components/main_header.css"/>
    <link rel="stylesheet" href="/resources/css/components/main_nav.css"/>
    <link rel="stylesheet" href="/resources/css/components/main_footer.css"/>
    <link rel="stylesheet" href="/resources/css/components/commodity.css"/>
    <link rel="stylesheet" href="/resources/css/components/pager.css"/>
    <link rel="stylesheet" href="/resources/css/components/filters-item.css"/>
    <script src="/resources/js/filters-item.js"></script>
	<#assign
		requestParameters=""
	>
	<#if RequestParameters.tags??>
		<#assign
			requestParameters=requestParameters+'&'+'tags='+RequestParameters.tags
		>
	</#if>
    <#if RequestParameters.minPrice??>
		<#assign
		requestParameters=requestParameters+'&'+'minPrice='+RequestParameters.minPrice
		>
	</#if>
    <#if RequestParameters.maxPrice??>
		<#assign
		requestParameters=requestParameters+'&'+'maxPrice='+RequestParameters.maxPrice
		>
	</#if>
    <#if RequestParameters.namePattern??>
		<#assign
		requestParameters=requestParameters+'&'+'namePattern='+RequestParameters.namePattern
		>
	</#if>
    <#if RequestParameters.sortBy??>
		<#assign
		requestParameters=requestParameters+'&'+'sortBy='+RequestParameters.sortBy
		>
	</#if>
</head>

<body>
    <@main_header/>

    <@main_nav/>

<#--requestParams:${requestParameters}-->

    <div class="wrapper-main">
        <main class="main wrapper-main__main"></form>
            <div class="filters-section main__filters-section">
            	<p class="filters-section__header-title font-MerriweatherRegular">Filter by</p>
                <form clas="filters-section__filters-form" action="/commodities" method="get">
                    <div class="sort-selector filters-section__sort-selector">
                        <p class="sort-selector__sort-title">Sort by</p>
                        <select class="sort-selector__sort-select" name="sortBy">
                            <option value="NAME" <#if commodityFilter.sortBy=="NAME">selected</#if> >Name</option>
                            <option value="PRICE"<#if commodityFilter.sortBy=="PRICE">selected</#if>>Price</option>
                            <option value="CREATION_DATE"<#if commodityFilter.sortBy=="CREATION_DATE">selected</#if>>Date</option>
                        </select>
                    </div>

					<#list groupedCategories?keys as categoryKey>
						<@filtersItem
                            filterTitle=categoryKey
                            allCategories=groupedCategories[categoryKey]
                            curCommodityFilter=commodityFilter
							additionalClass="filters-section__filters-item"/>
					</#list>
					<#--<@filtersItem onClickMethod="expandBlock(this)" additionalClass="filters-section__filters-item"/>-->
					<#--<@filtersItem onClickMethod="expandBlock(this)" additionalClass="filters-section__filters-item"/>-->

					<button class="filters-section__search-button" type="submit">Filter</button>
                </form>
            </div><!-- filters-section -->
            <div class="products-section main__products-section">
                <div class="products-nav products-section__products-nav">
					<p class="products-nav__found-products font-MerriweatherRegular">${paginator.getItemsAmount()} Products Found</p>
                    <@pager paginator=paginator additionalClass="products-nav__products-pages" requestParameters=requestParameters/>
                </div><!-- products-nav -->
                <div class="products-list main__products-list">

					<#if paginator.getPage()??>
						<#list paginator.getPage() as curCommodity>
							<@commodity additionalClass="products-list__product" commodity=curCommodity/>
						</#list>
					</#if>

                    <@security.authorize access="hasRole('ROLE_ADMIN')">
                        <#if paginator.pageIndex==paginator.getTotalPages()>
                            <a href="/commodities/addCommodity" class="products-list__product product-list__product_new"></a>
                        </#if>
                    </@security.authorize>

                </div><!-- products-list -->
                <div class="pages-controller products-section__pages-controller">
                    <#if paginator.getPageIndex() gt 1>
                	    <a href="/commodities?page=${paginator.getPageIndex()-1}" class="pages-controller__previous-page font-LatoRegular">Previous</a>
                    </#if>
                    <@pager paginator=paginator additionalClass="pages-controller__products-pages" requestParameters=requestParameters/>
                	<#if paginator.getPageIndex() lt paginator.getTotalPages()>
                        <a href="/commodities?page=${paginator.getPageIndex()+1}" class="pages-controller__next-page font-LatoRegular">Next</a>
                    </#if>
                </div>
            </div><!-- products-section -->
        </main>

    </div><!-- wrapper-main -->

    <@main_footer/>
</body>

</html>