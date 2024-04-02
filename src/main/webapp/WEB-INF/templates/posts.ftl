<#include "base_page.ftl">

<#macro title>Posts</#macro>

<#macro page_content>
    <div class="container">
        <div class="row">
            <#list posts as post>
                <divc class="col-3"></divc>
                <div class="col-lg-6 justify-content-center">
                    <div class="card w-75">
                        <img class="card-img-top justify-content-center" src="${post.getImageUrl()}" alt="post_image">
                        <div class="card-body">
                            <small class="text-muted">
                                ${post.getDatePosted()?string("d MMMM, HH:mm")}
                            </small>
                            <a href="post?id=${post.getId()}" class="text-decoration-none">
                                <h2 class="card-title h4">${post.getTitle()}</h2>
                            </a>
                            <div class="text-decoration-none">
                                <a href="profile?id=${post.getPosterId()}">
                                    <p class="card-text">By ${post.getPosterLogin()}</p>
                                </a>
                            </div>
                            <div class="text-decoration-none">
                                <p class="card-text">${post.getShortDesc()}</p>
                            </div>
                            <a class="btn btn-primary" href="post?id=${post.getPosterId()}">Read more</a>
                        </div>
                    </div>
                </div>
                <divc class="col-3"></divc>
            </#list>
        </div>
    </div>
</#macro>



