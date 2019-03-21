<html>
<head>
    <title>Users</title>
</head>
    <body>
 <#if users?has_content>
           <ul>
         <#list users as user>
             <li>${user.name} : ${user.surName} : ${user.email}</li>
         </#list>
           </ul>
 <#else> <h1>No users yet.</h1>
 </#if>
    <form action="/users/new" method="get">
        <button>Add new user</button>
    </body>
</html>