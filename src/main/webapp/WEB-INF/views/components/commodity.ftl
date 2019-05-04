<#macro commodity commodity additionalClass="">
    <div class="${additionalClass} product">
        <div class="product__look">
            <p class="product__price">$ ${commodity.price}</p>
            <a href="commodities/${commodity.id}" class="product__buy-link">Shop now</a>
        </div>
        <p class="product__name font-MerriweatherRegular">${commodity.name}</p>
    </div>
</#macro>