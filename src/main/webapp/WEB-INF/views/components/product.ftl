<#macro product additionalClass commodityId>
    <div class="${additionalClass} product">
        <div class="product__look">
            <p class="product__price">$100</p>
            <a href="commodities/${commodityId}" class="product__buy-link">Shop now</a>
        </div>
        <p class="product__name font-MerriweatherRegular">Some product</p>
    </div>
</#macro>