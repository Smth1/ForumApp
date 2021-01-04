<#import "parts/common.ftl" as c>


<@c.page>
    <div>
        <h1>
            Theme name
        </h1>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input class="form-control" type="text" name="filter" value="${filter?ifExists}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-2">
                    Search
                </button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new message
    </a>

    <div class="collapse <#if message??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group col-md-4">
                    <input type="text"
                           name="text"
                           class="form-control ${(textError??)?string('is-invalid','')}"
                           value="<#if message??>${message.text}</#if>"
                           placeholder="input message"/>
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <div class="form-group col-md-4">
                    <input type="text"
                           name="tag"
                           value="<#if message??>${message.tag}</#if>"
                           class="form-control"
                           placeholder="input tag" />
                    <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                    </#if>
                </div>
                <div class="form-group col-md-4">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile"/>
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group col-md-4">
                    <button type="submit" class="btn btn-primary">add</button>
                </div>
            </form>
        </div>
    </div>

    <div>
        <h4>
        Messages
        </h4>
    </div>

    <div class="card-columns">
        <#list messages as message>
            <div class="card my-3" >
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if >
                <div>
                    <b>${message.id}</b>
                </div>
                <div class="card-title">
                    <i>${message.tag}</i>
                </div>
                <div class="m2 card-text">
                    <span>${message.text}</span>
                </div>
                <div class="card-footer text-muted">
                    ${message.authorName}
                </div>
            </div>


        <#else>
            No message
        </#list>
    </div>
</@c.page>
