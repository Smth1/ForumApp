<#import "parts/common.ftl" as c>

<@c.page>
    List of users
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Role</th>
            <th scope="col">Password</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td scope="row">
                    ${user.username}
                </td>
                <td scope="row">
                    <#list user.roles as role>${role}<#sep>, </#list>
                </td>
                <td scope="row">
                    ${user.password}
                </td>
                <td scope="row">
                    <a href="/user/${user.id}">
                        edit
                    </a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>