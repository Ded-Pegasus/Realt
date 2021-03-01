<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>

    <div class="container">
        <!-- Слайдер -->
        <div id="carousel" class="carousel slide">
            <!-- Индикаторы слайдов -->
            <ol class="carousel-indicators">
                <!-- Активный индикатор-->
                <li class="active" data-target="#carousel" data-slide-to="0"></li>
                <!-- Неактивные индикаторы -->
                <li data-target="#carousel" data-slide-to="1"></li>
                <li data-target="#carousel" data-slide-to="2"></li>
            </ol>

            <!-- Слайды -->
            <div class="carousel-inner">

                <#if listing.filename??>
                    <div class="item active">
                        <img src="/img/${listing.filename}" alt="">

                        <div class="carousel-caption">
                        </div>
                    </div>
                </#if>

                <#if listing.filename1??>
                    <div class="item">
                        <img src="/img/${listing.filename1}" alt="">

                        <div class="carousel-caption">
                        </div>
                    </div>
                </#if>

                <#if listing.filename2??>
                    <div class="item">
                        <img src="/img/${listing.filename2}" alt="">

                        <div class="carousel-caption">
                        </div>
                    </div>
                </#if>

            </div>

            <!-- Стрелки переключения слайдов -->
            <a class="left carousel-control" href="#carousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>

            <a class="right carousel-control" href="#carousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

    </div>

    <h5 class="card-header">${listing.title}</h5>
    <p class="fs-2 mt-4">Price ${listing.price}$</p>
    <div>
        <dl class="row">
            <dt class="col-sm-3 mt-2">Description lists</dt>
        </dl>
        <p class="text-justify">${listing.text}</p>
    </div>

    <div class="card"  style="width: 20rem;">
        <ul class="list-group list-group-flush">
            <li class="list-group-item">
                <h3>Contact details</h3>
            </li>
            <li class="list-group-item">
                <h4>Name</h4>
                <h5>
                    <#if listing.authorName??>
                        ${listing.authorName}
                    <#else>
                        none
                    </#if>
                </h5>
            </li>
            <li class="list-group-item">
                <h4>Phone number</h4>
                <h5>
                    <#if listing.authorPhoneNumber??>
                        ${listing.authorPhoneNumber}
                    <#else>
                        none
                    </#if>
                </h5>
            </li>
            <li class="list-group-item">
                <h4>Mail</h4>
                <h5>
                    <#if listing.authorMail??>
                        ${listing.authorMail}
                    <#else>
                        none
                    </#if>
                </h5>
            </li>
        </ul>
    </div>

    <#if isAdmin || user?? && user.username==listing.authorName>
        <p>
            <button class="btn btn-primary mt-4" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                Refactoring post
            </button>
        <p>
        <div class="collapse" id="collapseExample">
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
                <div class="form-group mt-1">
                    <label for="exampleFormControlTextarea1">Description</label>
                    <textarea class="form-control ${(textError??)?string('is-invalid', '')}" name="text"
                              value="<#if listing??>${listing.text}</#if>" id="exampleFormControlTextarea1" rows="3"></textarea>
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <div class="custom-file-label mt-2">
                    <input type="file" name="title_file" class="custom-file-input" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose title file</label>
                </div>
                <div class="custom-file-label mt-2">
                    <input type="file" name="file1" class="custom-file-input" id="customFile1">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
                <div class="custom-file-label mt-2">
                    <input type="file" name="file2" class="custom-file-input" id="customFile2">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
                <button type="submit" class="btn btn-primary mt-2 mb-2">confirm and publish</button>
            </form>
            <form action="/deleteListing/${listing.id}" method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="listing" value="${listing.id}">
                <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </div>
            </form>
        </div>
    </#if>
</@c.page>