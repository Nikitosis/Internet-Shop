<#macro filtersItem filterTitle filterCategories onClickMethod="" additionalClass="">
    <div class="filters-item filters-section__filters-item">
        <p class="filters-item__title font-LatoRegular" onclick=${onClickMethod}>${filterTitle}</p>
        <div class="filters-item__checkbox-box">
            <#if filterCategories??>
                <#list filterCategories as category>
                    <div class="filters-item__checkbox-wrapper">
                        <label class="filters-item__checkbox-label">
                            <input type="checkbox" name="tags" value="${category.name}__${category.value}" class="filters-item__checkbox">
                            ${category.value}
                        </label>
                    </div>
                </#list>
            </#if>
        </div>
    </div><!-- filters-item -->
</#macro>