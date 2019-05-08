<#macro commodity commodity additionalClass="">
    <div class="${additionalClass} product">
        <div class="product__look">
            <img class="product__image" src="/commodities/getMainImage?commodityId=${commodity.id}"/>
            <p class="product__price">$ ${commodity.price}</p>
            <a href="commodities/${commodity.id}" class="product__buy-link">Shop now</a>
        </div>
        <a href="/commodities/${commodity.id}" class="product__name font-MerriweatherRegular">${commodity.name}</a>
    </div>
</#macro>