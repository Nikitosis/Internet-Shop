<#include "components/main_header.ftl"/>
<#include "components/main_nav.ftl"/>
<#include "components/main_footer.ftl"/>
<#include "components/commodity.ftl"/>

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

    <script>
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
    // }

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
        <main class="main wrapper-main__main">
            <div class="filters-section main__filters-section">
            	<p class="filters-section__header-title font-MerriweatherRegular">Filter by</p>
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
            </div><!-- filters-section -->
            <div class="products-section main__products-section">
                <div class="products-nav products-section__products-nav">
                    <p class="products-nav__found-products font-MerriweatherRegular">102 Products Found</p>
                    <p class="products-nav__sort-title">Sort by</p>
                    <select class="products-nav__sort-select">
                        <option value="First">First</option>
                        <option value="First">First</option>
                        <option value="First">First</option>
                    </select>
                    <ul class="products-pages products-nav__products-pages">
                        <li class="products-pages__item"><a href="#" class="products-pages__link">1</a></li>
                        <li class="products-pages__item"><a href="#" class="products-pages__link products-pages__link_selected">2</a></li>
                        <li class="products-pages__item"><a href="#" class="products-pages__link">3</a></li>
                        <li class="products-pages__item"><a href="#" class="products-pages__link">4</a></li>
                    </ul>
                </div><!-- products-nav -->
                <div class="products-list main__products-list">

					<#if paginator.getPage()??>
						<#list paginator.getPage() as commodity>
							<@product additionalClass="products-list__product" commodityId="${commodity.id}" commodityPrice="${commodity.price}" commodityName="${commodity.name}"/>
						</#list>
					</#if>

                </div><!-- products-list -->
                <div class="pages-controller products-section__pages-controller">
                	<a href="" class="pages-controller__previous-page font-LatoRegular">Previous</a>
                	<ul class="products-pages pages-controller__products-pages">
                        <li class="products-pages__item"><a href="#" class="products-pages__link">1</a></li>
                        <li class="products-pages__item"><a href="#" class="products-pages__link products-pages__link_selected">2</a></li>
                        <li class="products-pages__item"><a href="#" class="products-pages__link">3</a></li>
                        <li class="products-pages__item"><a href="#" class="products-pages__link">4</a></li>
                    </ul>
                	<a href="" class="pages-controller__next-page font-LatoRegular">Next</a>
                </div>
            </div><!-- products-section -->
        </main>

    </div><!-- wrapper-main -->

    <@main_footer/>
</body>

</html>