<#import "parts/common.ftl" as c>

<@c.page>

    <form action="/profile" method="get">
        <div class="card">
            <h3 class="card-header">Profile data</h3>
            <div class="card-body" style="display:inline-block">
                <h4 class="card-title">Name</h4>
                <h5 class="card-text">
                    <#if user.username??>
                        ${user.username}
                    <#else>
                        none
                    </#if>
                </h5>
                <h4 class="card-title">Mail</h4>
                <h5 class="card-text">
                    <#if user.mail??>
                        ${user.mail}
                    <#else>
                        none
                    </#if>
                </h5>
                <h4 class="card-title">Phone number</h4>
                <h5 class="card-text">
                    <#if user.phoneNumber??>
                        ${user.phoneNumber}
                    <#else>
                        none
                    </#if>
                </h5>
            </div>
        </div>
    </form>

    <form action="/profile" method="post">
        <p>
            <button class="btn btn-primary mt-2 md-1" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                Refactoring profile
            </button>
        <p>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="collapse" id="collapseExample">
            <div class="form-group">
                <label for="exampleInputPassword1">Name</label>
                <input type="text" class="form-control ${(userError??)?string('is-invalid', '')}" name="name"
                       value="<#if user.name??>${user.name}</#if>" id="exampleInputName1" placeholder="Enter name">
                <#if userError??>
                    <div class="invalid-feedback">
                        ${userError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <input type="email" class="form-control" name="mail" id="exampleInputMail1" aria-describedby="email" placeholder="Enter email">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Phone number</label>
                <input type="text" class="form-control ${(phoneError??)?string('is-invalid', '')}" name="phone"
                       value="<#if user.phoneNumber??>${user.phoneNumber}</#if>" id="exampleInputPhone1" placeholder="Enter phone">
                <#if phoneError??>
                    <div class="invalid-feedback">
                        ${phoneError}
                    </div>
                </#if>
            </div>
            <div>
                <button type="submit" class="btn btn-primary mt-1">Save</button>
            </div>
        </div>
    </form>

</@c.page>