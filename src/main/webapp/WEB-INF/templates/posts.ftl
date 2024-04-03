<#include "base_page.ftl">

<#macro title>Posts</#macro>

<#macro page_content>
    <div class="container">
        <div class="row">
            <#list posts as post>
                <divc class="col-3"></divc>
                <div class="col-lg-6 justify-content-center">
                    <div class="card w-75">
                        <img class="card-img-top justify-content-center" src="${post.imageUrl}" alt="post_image">
                        <div class="card-body">
                            <small class="text-muted">
                                ${post.datePosted?string("d MMMM, HH:mm")}
                            </small>
                            <a href="post?id=${post.id}" class="text-decoration-none">
                                <h2 class="card-title h4">${post.title}</h2>
                            </a>
                            <div class="text-decoration-none">
                                <a href="profile?id=${post.poster.id}">
                                    <p class="card-text">By ${post.poster.login}</p>
                                </a>
                            </div>
                            <div class="text-decoration-none">
                                <p class="card-text">${post.description}</p>
                            </div>
                            <a class="btn btn-primary" href="post?id=${post.poster.id}">Read more</a>
                        </div>
                    </div>
                </div>
                <divc class="col-3"></divc>
            </#list>
        </div>
    </div>
</#macro>



