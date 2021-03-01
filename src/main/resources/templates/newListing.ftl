<#import "parts/common.ftl" as c>

<@c.page>
    <strong>
        <h3>Fill out the announcement form</h3>
    </strong>

    <form  method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="form-group">
            <label for="exampleFormControlInput1"></label>
            <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}" name="title"
                   value="<#if listing??>${listing.title}</#if>" id="exampleFormControlInput1" placeholder="Title">
            <#if titleError??>
                <div class="invalid-feedback">
                    ${titleError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <label for="exampleFormControlSelect1"></label>
            <select class="form-control" name="tag" id="exampleFormControlSelect1">
                <option value="Flat">Flat</option>
                <option value="Cottage">Cottage</option>
                <option value="Houses">Houses</option>
            </select>
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput1"></label>
            <input type="text" class="form-control ${(priceError??)?string('is-invalid', '')}" name="price"
                   value="<#if listing??>${listing.price}</#if>" id="exampleFormControlInput1" placeholder="USD">
            <#if priceError??>
                <div class="invalid-feedback">
                    ${priceError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Description</label>
            <textarea class="form-control" name="text" id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>
        <div class="custom-file-label mt-2">
            <input type="file" name="file" class="custom-file-input" id="customFile">
            <label class="custom-file-label" for="customFile">Choose file</label>
        </div>
        <button type="submit" class="btn btn-primary mt-2 mb-2">confirm and publish</button>
    </form>
</@c.page>