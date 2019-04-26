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
                        <h3 class="container__title">Winter party collection</h3>
                        <p class="container__info">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid quibusdam vitae, animi eveniet li.</p>
                        <a href="" class="container__link">Shop now</a>
                    </div>
                </div>
                <div class="image-box image-box_column-container image-box_fill-vertical">
                    <div class="image-box image-box_column-container image-box_fill-horizontal">
                        <div class="container image-box__container container_content-position_left container_background_2">
                            <h3 class="container__title">Wparty collection</h3>
                            <p class="container__info">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid quibusdam vitaeelectus voluptatum hic accusantium.</p>
                            <a href="" class="container__link">Shop now</a>
                        </div>
                    </div>
                    <div class="image-box image-box_row-container image-box_fill-horizontal">
                        <div class="image-box image-box_fill-vertical">
                            <div class="container image-box__container container_content-position_left container_background_3">
                                <h3 class="container__title">Something other</h3>
                                <a href="" class="container__link">Shop now</a>
                            </div>
                        </div>
                        <div class="image-box image-box_fill-vertical">
                            <div class="container image-box__container container_content-position_left container_background_4">
                                <h3 class="container__title">Winter party collection</h3>
                                <a href="" class="container__link">Shop now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section><!-- main-banner -->
    </div><!-- wrapper-main-banner -->
    <div class="wrapper-bestsellers">
        <div class="bestsellers wrapper-bestsellers__bestsellers">
            <h3 class="bestsellers__title font-MerriweatherRegular">Women best selling products</h3>
            <p class="bestsellers__subtitle font-LatoRegular">Lorem ipsum dolor sit amet.</p>
            <div class="bestsellers__product-list product-list">

                <#list 1..4 as i>
                    <@product additionalClass="product-list__product" commodityId=1/>
                </#list>

            </div>
            <a href="" class="bestsellers__more-link font-LatoBold">View more</a>
        </div><!-- bestsellers -->
    </div>
    <div class="wrapper-bestsellers">
        <div class="bestsellers wrapper-bestsellers__bestsellers">
            <h3 class="bestsellers__title font-MerriweatherRegular">Men best selling products</h3>
            <p class="bestsellers__subtitle font-LatoRegular">Lorem ipsum dolor sit amet.</p>
            <div class="bestsellers__product-list product-list">

                <#list 1..4 as i>
                    <@product additionalClass="product-list__product" commodityId=1/>
                </#list>

            </div><!--product-list-->
            <a href="" class="bestsellers__more-link font-LatoBold">View more</a>
        </div><!-- bestsellers -->
    </div>

    <@main_footer/>
</body>

</html>