<#import "parts/common.ftl" as c>

<@c.page>
    <form method="get" action="/main">
        <select class="custom-select" name="filter">
            <option value="All" selected>All</option>
            <option value="Flat">Flat</option>
            <option value="Cottage">Cottage</option>
            <option value="Houses">Houses</option>
        </select>
        <button type="submit" class="btn btn-primary bl-1">Search</button>
    </form>

    <#include "parts/listingList.ftl" />

</@c.page>