function expandBlock(filterTitle){
    var filtersItem=filterTitle.closest(".filters-item");
    var filterCheckboxBox=filtersItem.getElementsByClassName("filters-item__checkbox-box")[0];
    filterTitle.classList.toggle("filters-item__title_expanded");
    filterCheckboxBox.classList.toggle("filters-item__checkbox-box_expanded");
    console.log("filterTitle clicked");
    // console.log(filterTitle);
    // console.log(filterCheckboxBox);
}
