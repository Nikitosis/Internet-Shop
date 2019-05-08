<#include "components/main_header.ftl"/>
<#include "components/main_nav.ftl"/>
<#include "components/main_footer.ftl"/>
<#include "components/commodity.ftl"/>

<#--<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />-->

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/resources/css/home.css">
    <link rel="stylesheet" href="/resources/css/components/main_header.css"/>
    <link rel="stylesheet" href="/resources/css/components/main_nav.css"/>
    <link rel="stylesheet" href="/resources/css/components/main_footer.css"/>
    <link rel="stylesheet" href="/resources/css/components/commodity.css"/>
</head>

<body>
    <@main_header/>

    <@main_nav/>


    <div class="wrapper-main-banner">
        <section class="main-banner wrapper-main-banner__main-banner font-LatoRegular">
            <div class="main-banner__image-box image-box image-box_row-container image-box_fill-horizontal image-box_fill-vertical">
                <div class="image-box image-box_fill-vertical">
                    <div class="container image-box__container container_content-position_center container_background_1">
                        <h3 class="container__title">Fast gaming notebooks</h3>
                        <p class="container__info"> Now you can enjoy modern games like GTA 3 or Mafia 1. Fast and furious notebooks here!</p>
                        <a href="/commodities?mainTag=type__notebook&tags=type__notebook" class="container__link">Shop now</a>
                    </div>
                </div>
                <div class="image-box image-box_column-container image-box_fill-vertical">
                    <div class="image-box image-box_column-container image-box_fill-horizontal">
                        <div class="container image-box__container container_content-position_left container_background_2">
                            <h3 class="container__title">Brand new Iphone Collection</h3>
                            <p class="container__info"> New Iphone 2 Remake collection. Relicts for real fans.</p>
                            <a href="/commodities?mainTag=type__phone&tags=type__phone" class="container__link">Shop now</a>
                        </div>
                    </div>
                    <div class="image-box image-box_row-container image-box_fill-horizontal">
                        <div class="image-box image-box_fill-vertical">
                            <div class="container image-box__container container_content-position_left container_background_3">
                                <h3 class="container__title">New Accessory Collection</h3>
                                <a href="/commodities?mainTag=type__accessory&tags=type__accessory" class="container__link">Shop now</a>
                            </div>
                        </div>
                        <div class="image-box image-box_fill-vertical">
                            <div class="container image-box__container container_content-position_left container_background_4">
                                <h3 class="container__title">Spares</h3>
                                <a href="/commodities?mainTag=type__spares&tags=type__spares" class="container__link">Shop now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section><!-- main-banner -->
    </div><!-- wrapper-main-banner -->
    <#assign
    commoditiesAmount=(commodities?size>4)?then(4,commodities?size)
    >
    <#if commoditiesAmount gt 0>
        <div class="wrapper-bestsellers">
            <div class="bestsellers wrapper-bestsellers__bestsellers">
                <h3 class="bestsellers__title font-MerriweatherRegular">Best selling notebooks</h3>
                <p class="bestsellers__subtitle font-LatoRegular">Best notebooks for your grandparents.</p>
                <div class="bestsellers__product-list product-list">
                    <#list 0..commoditiesAmount-1 as i>
                        <@commodity additionalClass="product-list__product" commodity=commodities[i]/>
                    </#list>

                </div>
                <a href="/commodities?mainTag=type__notebook&tags=type__notebook" class="bestsellers__more-link font-LatoBold">View more</a>
            </div><!-- bestsellers -->
        </div> <!-- wrapper-bestsellers -->
    </#if>
    <#if commoditiesAmount gt 0>
        <div class="wrapper-bestsellers">
            <div class="bestsellers wrapper-bestsellers__bestsellers">
                <h3 class="bestsellers__title font-MerriweatherRegular">Best selling phones</h3>
                <p class="bestsellers__subtitle font-LatoRegular">A phone for your child.</p>
                <div class="bestsellers__product-list product-list">

                    <#list 0..commoditiesAmount-1 as i>
                            <@commodity additionalClass="product-list__product" commodity=commodities[i]/>
                    </#list>

                </div><!--product-list-->
                <a href="/commodities?mainTag=type__phone&tags=type__phone" class="bestsellers__more-link font-LatoBold">View more</a>
            </div><!-- bestsellers -->
        </div>
    </#if>

    <@main_footer/>
</body>

</html>