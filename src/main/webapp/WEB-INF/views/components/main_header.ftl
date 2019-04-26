<#macro main_header basketAmount=0 basketPrice=0>
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

    <div class="wrapper-header">
        <header class="header wrapper-header__header">
            <div class="user header__user font-LatoBold">
                <@security.authorize access="isAuthenticated()">
                    <a href="#" class="user__avatar"></a>
                     <a href="#" class="user__username">
                            <@security.authentication property="principal.username"/>
                     </a>
                    <a href="/logout" class="user__logout">Logout</a>
                </@security.authorize>

                <@security.authorize access="!isAuthenticated()">
                    <a href="/login" class="user__login">Login</a>
                    <a href="/registration" class="user__sign-up">Sign Up</a>
                </@security.authorize>

            </div>
            <a href="/" class="logo header__logo font-Awesome">
                <h1 class="logo__title">ELECTRON</h1>
                <h2 class="logo__subtitle">SHOP</h2>
            </a>
            <a href="/basket" class="cart header__cart font-LatoRegular">
                <div class="cart__bag">
                    <div class="cart__amount-items">${basketAmount}</div>
                </div>
                <div class="cart__total-price">$ ${basketPrice}</div>
            </a>
        </header>
    </div><!-- wrapper-header -->
</#macro>