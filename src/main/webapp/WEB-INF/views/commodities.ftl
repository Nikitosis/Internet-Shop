<#include "components/main_header.ftl"/>
<#include "components/main_nav.ftl"/>
<#include "components/main_footer.ftl"/>
<#include "components/commodity.ftl"/>
<#include "components/pager.ftl"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/resources/css/commodities.css">
    <link rel="stylesheet" href="/resources/css/components/main_header.css"/>
    <link rel="stylesheet" href="/resources/css/components/main_nav.css"/>
    <link rel="stylesheet" href="/resources/css/components/main_footer.css"/>
    <link rel="stylesheet" href="/resources/css/components/commodity.css"/>
    <link rel="stylesheet" href="/resources/css/components/pager.css"/>

    <script>
		// window.onload=function(){
		//     console.log("123");
		//     document.getElementById("selectSorting").onchange=function(){
		//         var thisElem=
		//         window.location.href=window.location.href+'&sortBy'+(this).value();
		// 	}
		// };
    // window.onload = function() {
    //     var filtersItems = document.getElementsByClassName("filters-item");
    //     for (var i = 0; i < filtersItems.length; i++) {
    //         var filterTitle = filtersItems[i].getElementsByClassName("filters-item__title")[0];
    //         // filterTitle.onclick=expandCheckBox(filterCheckboxBox);
    //         filterTitle.onclick=function(){
    //         	var filtersItem=filterTitle.closest(".filters-item");
    //         	var filterCheckboxBox=filtersItem.getElementsByClassName("filters-item__checkbox-box")[0];
    //         	filterCheckboxBox.classList.toggle("filters-item__checkbox-box_expanded");
    //         	console.log("filterTitle clicked");
    //         	console.log(filterTitle);
    //         	console.log(filterCheckboxBox);
    //         }
    //     }
    //
		function onSortingChanged(selectSort){
		    var selectedIndex=selectSort.selectedIndex;
            //window.location.href=window.location.href+'&sortBy='+selectSort.options[selectedIndex].value;
		}

		function expandBlock(filterTitle){
			var filtersItem=filterTitle.closest(".filters-item");
			var filterCheckboxBox=filtersItem.getElementsByClassName("filters-item__checkbox-box")[0];
			filterTitle.classList.toggle("filters-item__title_expanded");
			filterCheckboxBox.classList.toggle("filters-item__checkbox-box_expanded");
			console.log("filterTitle clicked");
			// console.log(filterTitle);
			// console.log(filterCheckboxBox);
		}
    </script>
</head>

<body>
    <@main_header/>

    <@main_nav/>

    <div class="wrapper-main">
        <main class="main wrapper-main__main"></form>
            <div class="filters-section main__filters-section">
            	<p class="filters-section__header-title font-MerriweatherRegular">Filter by</p>
                <form clas="filters-section__filters-form" action="/commodities" method="get">
                    <div class="sort-selector filters-section__sort-selector">
                        <p class="sort-selector__sort-title">Sort by</p>
                        <select class="sort-selector__sort-select" onchange="onSortingChanged(this)">
                            <option value="First">Name</option>
                            <option value="First">Price</option>
                            <option value="First">Date</option>
                        </select>
                    </div>
					<div class="filters-item filters-section__filters-item">
						<p class="filters-item__title font-LatoRegular" onclick="expandBlock(this)">Categories</p>
						<div class="filters-item__checkbox-box">
							<div class="filters-item__checkbox-wrapper">
								<label class="filters-item__checkbox-label">
									<input type="checkbox" class="filters-item__checkbox">
									Bottom
								</label>
							</div>
							<div class="filters-item__checkbox-wrapper">
								<label class="filters-item__checkbox-label">
									<input type="checkbox" class="filters-item__checkbox">
									Bottom
								</label>
							</div>
							<div class="filters-item__checkbox-wrapper">
								<label class="filters-item__checkbox-label">
									<input type="checkbox" class="filters-item__checkbox">
									Bottom
								</label>
							</div>
						</div>
					</div><!-- filters-item -->
					<div class="filters-item filters-section__filters-item">
						<p class="filters-item__title font-LatoRegular" onclick="expandBlock(this)" >Categories</p>
						<div class="filters-item__checkbox-box">
							<div class="filters-item__checkbox-wrapper">
								<label class="filters-item__checkbox-label">
									<input type="checkbox" class="filters-item__checkbox">
									Bottom
								</label>
							</div>
							<div class="filters-item__checkbox-wrapper">
								<label class="filters-item__checkbox-label">
									<input type="checkbox" class="filters-item__checkbox">
									Bottom
								</label>
							</div>
							<div class="filters-item__checkbox-wrapper">
								<label class="filters-item__checkbox-label">
									<input type="checkbox" class="filters-item__checkbox">
									Bottom
								</label>
							</div>
						</div>
					</div><!-- filters-item -->
					<button class="filters-section__search-button" type="submit">Filter</button>
                </form>
            </div><!-- filters-section -->
            <div class="products-section main__products-section">
                <div class="products-nav products-section__products-nav">
					<p class="products-nav__found-products font-MerriweatherRegular">102 Products Found</p>
                    <@pager paginator=paginator additionalClass="products-nav__products-pages"/>
                </div><!-- products-nav -->
                <div class="products-list main__products-list">

					<#if paginator.getPage()??>
						<#list paginator.getPage() as commodity>
							<@product additionalClass="products-list__product" commodityId="${commodity.id}" commodityPrice="${commodity.price}" commodityName="${commodity.name}"/>
						</#list>
					</#if>

                </div><!-- products-list -->
                <div class="pages-controller products-section__pages-controller">
                    <#if paginator.getPageIndex() gt 1>
                	    <a href="/commodities?page=${paginator.getPageIndex()-1}" class="pages-controller__previous-page font-LatoRegular">Previous</a>
                    </#if>
                    <@pager paginator=paginator additionalClass="pages-controller__products-pages"/>
                	<#if paginator.getPageIndex() lt paginator.getTotalPages()>
                        <a href="/commodities?page=${paginator.getPageIndex()+1}" class="pages-controller__next-page font-LatoRegular">Next</a>
                    </#if>
                </div>
            </div><!-- products-section -->
        </main>

    </div><!-- wrapper-main -->

    <@main_footer/>
</body>

</html>