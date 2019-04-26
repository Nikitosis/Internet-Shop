<#macro comment additionalClass="comment-section__single-comment" userName="Username" message="Message" date="Date">
    <div class="single-comment ${additionalClass}">
        <p class="single-comment__name font-MerriweatherBold">${userName}</p>
        <p class="single-comment__text font-LatoRegular">${message}</p>
        <p class="single-comment__date">${date}</p>
    </div>
</#macro>