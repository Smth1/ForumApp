<#import "parts/common.ftl" as c>


<@c.page>
    <h3>
        Topics
    </h3>

    <div>
        <#list topics as topic>
            <div class="jumbotron">
                <h1 class="display-4">${topic.name}</h1>
                <p class="lead">${topic.text}</p>
                <hr class="my-4">

                <a class="btn btn-primary btn-lg" href="/topics/${topic.id}" role="button">Learn more</a>
            </div>
        <#else>
            No Topics
        </#list>
    </div>
</@c.page>
