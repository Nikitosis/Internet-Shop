<#macro commodity commodity additionalClass="">
    <div class="${additionalClass} product">
        <div class="product__look">
            <img class="product__image" src="/commodities/getMainImage?commodityId=${commodity.id}"/>
            <p class="product__price">$ ${commodity.price}</p>
            <form action="/basket/removeCommodity" method="post">
                <input type="hidden" name="id" value="${commodity.id}"/>
                <button type="submit" class="product__remove-button">Remove</button>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>

        </div>
        <a href="/commodities/${commodity.id}" class="product__name font-MerriweatherRegular">${commodity.name}</a>
    </div>
</#macro>