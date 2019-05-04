<#macro filtersItem filterTitle="Title" allCategories=[] curCommodityFilter=null onClickMethod="" additionalClass="">
    <div class="filters-item filters-section__filters-item">
        <p class="filters-item__title font-LatoRegular" onclick=${onClickMethod}>${filterTitle}</p>
        <div class="filters-item__checkbox-box">
            <#if allCategories??>
                <#list allCategories as category>
                    <#assign
                    isSelected=false
                    >
                    <#if curCommodityFilter.categories??>
                        <#list curCommodityFilter.categories as selectedCategory>
                            <#if category.name==selectedCategory.name && category.value==selectedCategory.value>
                                <#assign
                                    isSelected=true
                                >
                            </#if>
                        </#list>
                    </#if>


                    <div class="filters-item__checkbox-wrapper">
                        <label class="filters-item__checkbox-label">
                            <input type="checkbox" name="tags" value="${category.name}__${category.value}" class="filters-item__checkbox"
                                <#if isSelected?? && isSelected==true>checked</#if>
                            >
                            ${category.value}
                        </label>
                    </div>
                </#list>
            </#if>
        </div>
    </div><!-- filters-item -->
</#macro>