<#import "pager.ftl" as p>

<@p.pager url page filter/>

<div class="block; mt-2">
    <#list page.content as listing>
        <div class="mt-2; d-inline" style="margin-left: 10px">
            <div class="card mb-2" style="width: 18rem; display: inline-block">
                <#if listing.filename??>
                    <img class="card-img-top" src="/img/${listing.filename}" alt="Card image cap">
                </#if>
                <div class="card-body">
                    <p class="card-text">${listing.title}</p>
                    <p class="card-text">Price ${listing.price}$</p>
                    <strong>Owner - ${listing.authorName}</strong>
                    <p><a href="/postDetail/${listing.id}" class="card-link">more info</a></p>
                </div>
            </div>
        </div>

    <#else>
        No listing
    </#list>
</div>

<@p.pager url page filter/>