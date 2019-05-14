<#include "components/main_header.ftl"/>
<#include "components/main_nav.ftl"/>
<#include "components/main_footer.ftl"/>
<#include "components/commodity_basket.ftl"/>
<#include "components/head_tags.ftl"/>


<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/resources/css/basket.css">
    <link rel="stylesheet" href="/resources/css/components/main_header.css"/>
    <link rel="stylesheet" href="/resources/css/components/main_nav.css"/>
    <link rel="stylesheet" href="/resources/css/components/main_footer.css"/>
    <link rel="stylesheet" href="/resources/css/components/commodity_basket.css"/>

    <@head_tags/>
</head>
<body>
<div class="main">
    <@main_header/>
    <@main_nav/>

    <div class="products-section main__products-section">
        <h2 class="products-section__header font-LatoBold">Your basket</h2>
        <div class="products-list products-section__products-list">
            <#list commodities as curCommodity>
                <@commodity additionalClass="products-list__product" commodity=curCommodity/>
            </#list>
        </div>
        <div class="products-result products-section__products-result">
            <h3 class="products-result__price font-MerriweatherRegular">Total price: $${totalPrice}</h3>
            <#if commodities?? && commodities?size gt 0>
                <form class="products-result__confirm-form" action="/basket/confirmBuy" method="post">
                    <button class="products-result__confirm-button" type="submit">Confirm buy</button>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </#if>
        </div>

    </div>

    <@main_footer/>

</div>


</body>
</html>