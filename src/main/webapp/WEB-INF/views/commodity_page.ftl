<#include "components/main_header.ftl"/>
<#include "components/main_nav.ftl"/>
<#include "components/main_footer.ftl"/>
<#include "components/comment.ftl"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/resources/css/commodity_page.css">
    <link rel="stylesheet" href="/resources/css/components/main_header.css"/>
    <link rel="stylesheet" href="/resources/css/components/main_nav.css"/>
    <link rel="stylesheet" href="/resources/css/components/main_footer.css"/>
    <link rel="stylesheet" href="/resources/css/components/commodity.css"/>
    <link rel="stylesheet" href="/resources/css/components/comment.css"/>
</head>

<body>
    <@main_header/>

    <@main_nav/>

    <div class="wrapper-main">
        <main class="main wrapper-main__main">
            <div class="product-header main__product-header">
            	<p class="product-header__product-name font-MerriweatherRegular">${commodity.name}</p>
            	<p class="product-header__product-category font-LatoRegular">Some category</p>
            </div>

            <#if commodity.getImages()?size gt 0>
                <section class="image-section main__image-section">
                    <img class="image-section__picture image-section__picture_big" src="/commodities/getMainImage?commodityId=${commodity.id}"/>
                    <#list 0..commodity.getImages()?size-1 as imageIndex>
                        <img class="image-section__picture" src="/commodities/getImage?commodityId=${commodity.id}&imageIndex=${imageIndex}"/>
                    </#list>
                </section><!-- image-section  -->
            </#if>

            <div class="about main__about">
                <section class="about__section about__section_description">
                    <p class="section__title_big font-LatoBold">$ ${commodity.price}</p>
                </section>
                <section class="about__section about__section_product-detail">
                    <p class="section__title font-MerriweatherRegular">Product detail</p>
                    <#if commodity.description??>
                        <p class="section__text font-LatoRegular">${commodity.description}</p>
                    </#if>
                    </section>
                <form action="/addToBasket" method="post">
                    <section class="action-buttons about__action-buttons">
                        <input type="hidden" name="commodity_id" value="${commodity.id}"/>

                        <button class="action-buttons__button action-buttons__button_buy font-LatoBold" type="submit">Add to cart</button>
                        <button class="action-buttons__button action-buttons__button_add-cart font-LatoBold" type="submit">Add to cart</button>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </section>
                </form>
            </div> <!--about-->

			<section class="comment-section main__comment-section">
				<p class="comment-section__comments-amount font-MerriweatherRegular">${commodity.getComments()?size} Comments</p>

				<@security.authorize access="isAuthenticated()">
					<form class="comment-form comment-section__comment-form" action="/commodities/addComment" method="post">
						<label for="comment-form__textarea" class="comment-form__title font-LatoRegular">Comment</label>
						<textarea name="content" id="comment-form__textarea" cols="30" rows="10" class="comment-form__textarea"></textarea>
						<input type="submit" class="comment-form__post-comment font-LatoRegular"/>

						<input type="hidden" name="commodity_id" value="${commodity.id}"/>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
				</@security.authorize>
				<#if (commodity.comments)??>
					<#list commodity.comments as commentData>
						<@comment userName="${commentData.user.username}" message="${commentData.content}" date="${commentData.date}"/>
					</#list>
				</#if>
			</section><!-- comment-section -->

			<!-- <section class="column__section column__section_recommended-products">
				<p class="section__title font-MerriweatherRegular">You may like</p>
				<div class="recommended-products__wrapper">

				</div>
			</section> -->
        </main>
    </div>

    <form action="/commodities/deleteCommodity?id=${commodity.id}" method="post">
        <input type="submit" value="Delete commodity"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <@main_footer/>
</body>

</html>