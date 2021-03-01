<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Realt</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/main"><h4>Home</h4></a>
                </li>
                <#if user??>
                    <li class="nav-item">
                        <a class="nav-link active" style="margin-left: 5px" aria-current="page" href="/user-listings/${currentUserId}"><h4>My listings</h4></a>
                    </li>
                </#if>
                <li class="nav-item">
                    <a class="nav-link active" style="margin-left: 5px" aria-current="page" href="/newListing"><h4>Add new listing</h4></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" style="margin-left: 5px" aria-current="page" href="/profile"><h4>${name}</h4></a>
                </li>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link active" style="margin-left: 5px" aria-current="page" href="/user"><h4>UserList</h4></a>
                    </li>
                </#if>
            </ul>

            <div style="overflow: hidden; text-align: right;">
                <h4><@l.logout/></h4>
            </div>

        </div>
    </div>
</nav>