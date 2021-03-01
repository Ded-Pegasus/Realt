<#macro login path isRegisterForm>
    <form action="${path}" method="post" xmlns="http://www.w3.org/1999/html">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div>
            <div class="form-group" style="display: flex">
                <div class="col-sm-6">
                    <label> User Name : </label>
                    <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid', '')}"
                           value="<#if user??>${user.username}</#if>" placeholder="User name"/>
                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group" style="display: flex">
                <div class="col-sm-6">
                    <label> Password: </label>
                    <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"placeholder="password"/>
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <div class="container">
            <div>
                <#if !isRegisterForm>
                    <a href="/registration" style="margin-top: 4px">Add new user</a>
                </#if>
            </div>
            <div>
                <button class="btn btn-primary" type="submit" style="margin-top: 4px">
                    <#if isRegisterForm>Create<#else>Sign In</#if>
                </button>
            </div>
        </div>
    </form>
</#macro>


<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>