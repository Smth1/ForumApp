<#import "parts/common.ftl" as c>


<@c.page>
    <h5>
        Hello, user
    </h5>
    <div>
        This is a simple clone of Twitter
    </div>
    <div class="card-columns">
        <#list forums as forum>
            <div class="card my-3" >

                <div>
                    <b>${forum.id}</b>
                </div>
                <div class="card-title">
                    <i>${forum.name}</i>
                </div>
                <div class="m2 card-text">
                    <span>${forum.longDescription}</span>
                </div>
                <a href="/forums/${forum.id}" class="btn btn-primary">Go somewhere</a>
            </div>


        <#else>
            No Forum
        </#list>
    </div>
</@c.page>