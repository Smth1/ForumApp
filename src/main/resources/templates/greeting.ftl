<#import "parts/common.ftl" as c>


<@c.page>
    <h5>
        Hello, user
    </h5>
    <div>
        This is a simple clone of Twitter
    </div>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list forums as forum>
            <div class="col">
                <div class="card my-3" >
                    <div class="card-body">
                    <h3 class="card-title">
                        <i>${forum.name}</i>
                    </h3>

                    <div class="card-text">
                        <span>${forum.longDescription}</span>
                    </div>
                    <a style="margin-top: 30px" href="/forums/${forum.id}" class="btn btn-primary">Details</a>
                    </div>
                </div>
            </div>

        <#else>
            No Forum
        </#list>
    </div>
</@c.page>
