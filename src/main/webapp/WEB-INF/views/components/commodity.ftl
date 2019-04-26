<#macro product additionalClass commodityId commodityPrice=100>
    <div class="${additionalClass} product">
        <div class="product__look">
            <p class="product__price">${commodityPrice}</p>
            <a href="commodities/${commodityId}" class="product__buy-link">Shop now</a>
        </div>
        <p class="product__name font-MerriweatherRegular">Some product</p>
    </div>
</#macro>