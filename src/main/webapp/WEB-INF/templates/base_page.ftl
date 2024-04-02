<#include "base.ftl">

<#macro content>
    <header class="p-3 mb-3 border-bottom">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-between justify-content-lg-start">

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/about" class="nav-link px-2 link-body-emphasis">About Us</a></li>
                    <li><a href="/posts" class="nav-link px-2 link-body-emphasis">Posts</a></li>
                    <li><a href="/shares" class="nav-link px-2 link-body-emphasis">Find share</a></li>
                    <#if user?has_content>
                        <li><a href="/posts_submit" class="nav-link px-2 link-body-emphasis">Submit a post</a></li>
                    </#if>
                </ul>
                <#if user?has_content>
                    <div class="dropdown text-end">
                        <a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="${user.getPfpPicture()}" alt="mdo" width="48" height="48" class="rounded-circle">
                        </a>
                        <ul class="dropdown-menu text-small">
                            <li><a class="dropdown-item" href="/profile">Profile</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item text-danger" href="/logout">Logout</a></li>
                        </ul>
                    </div>
                </#if>
                <#if !user?has_content>
                    <form class="form-inline mt-2">
                        <button onclick="document.location='/login'" class="btn btn-secondary" type="button">Login</button>
                        <button onclick="document.location='/register'" class="btn btn-secondary" type="button">Register</button>
                    </form>
                </#if>

            </div>
        </div>
    </header>


    <div><@page_content/></div>
    <div id="text"></div>

    <script>
        $.extend({
            getUrlParam: function (param) {
                let sPageURL = decodeURIComponent(window.location.search.substring(1)),
                    sURLVariables = sPageURL.split('&'),
                    sParameterName,
                    i;
                for (i = 0; i < sURLVariables.length; i++) {
                    sParameterName = sURLVariables[i].split('=');
                    if (sParameterName[0] === param) {
                        return sParameterName[1] === undefined ? true : sParameterName[1];
                    }
                }
            },
        });
    </script>
</#macro>
