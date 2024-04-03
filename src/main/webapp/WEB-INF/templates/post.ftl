<#include "base_page.ftl">

    <style>
        .container[id="post_container"] {
            background-color: #eee;
        }

        .post_details {
            justify-content: space-between;
        }

        .comment {
            border: 1px solid #000;
            padding: 10px;
            margin: 10px 0;
        }
    </style>

<#macro title>
    <#if post?has_content>
        ${post.title}
    </#if>
    <#if !post?has_content>
        No post found
    </#if>
</#macro>

<#macro page_content>
    <div class="container" id="post_container">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="d-flex row align-content-center">
                    <div class="col-3"></div>
                    <div class="col-6">
                        <img src="${post.imageUrl}" alt="${post.title}" class="img-fluid mt-4 mx-auto align-self-center" width="300" height="200">
                        <h2 class="display-5 align-self-center">${post.title}</h2>
                    </div>
                    <div class="col-3"></div>
                </div>
                <div class="post_details">
                    <div>
                        <span class="font-weight-bold">By </span>
                        <div class="author-name">
                            <a href="/profile?id=${author.id}" rel="author" class="author-link">
                                <span class="author-name font-weight-bold">${author.login}</span>
                            </a>
                        </div>
                    </div>
                    <div>
                        <time>${post.datePosted?string("dd MMMM yyyy, HH:mm")}</time>
                    </div>
                </div>
                <div class="justify-content-between text-wrap pt-lg-3">
                    <p class="article-content text-break text-justify" style="color: #000; margin-top: 10px;">${post.content}</p>
                </div>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-9">
                <div id="commentsSection">
                    <#if comments?has_content>
                        <#list comments as comment>
                            <div class="comment">
                                <div class="justify-content-between d-flex">
                                    <div class="user-avatar">
                                        <a href="/profile?id=${comment.commenter.id}">
                                            <span class="user-id font-weight-bold" style="color: #000;">${comment.commenter.login}</span>
                                        </a>
                                    </div>
                                    <p class="timestamp font-weight-light m-1 text-right"
                                       style="font-size: 12px;">${comment.datePublished?string("dd MMMM yyyy, HH:mm")}</p>
                                </div>
                                <div class="comment-details">
                                    <div class="d-flex justify-content-between text-wrap">
                                        <p class="comment-text text-break text-justify m-1">${comment.content}</p>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>
        </div>
        <#if user?has_content>
            <div class="row justify-content-center">
                <div class="col-md-9">
                    <div class="d-flex comment">
                        <div class="col-12 comment-details">
                            <div class="d-flex justify-content-between text-wrap">
                                <textarea class="form-control" rows="2" placeholder="Your comment" aria-label="input_comment" name="input_comment" id="input_comment"></textarea>
                            </div>
                            <div class="d-flex row justify-content-center mt-md-2">
                                <button type="button" id="btn_comment" class="btn btn-outline-secondary w-50" disabled>Comment</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#if>
    </div>
</#macro>

<script>
    $(document).ready(function () {
        let user
        $.get("/getutil?task=get_user_data", function (response) {
            if (response === "null") {
                user = undefined;
            } else {
                user = JSON.parse(response)
            }
        } )

        $("#input_comment").on("input", function () {
            let comment = $(this).val()

            let btnComment = document.getElementById("btn_comment")
            if ((typeof comment !== "undefined")) {
                comment = comment.trim()
                if (!comment) btnComment.setAttribute("disabled", "")
                else btnComment.removeAttribute("disabled")
            }
            else {
                btnComment.setAttribute("disabled", "")
            }
        })

        $("#btn_comment").on("click", function () {
            let comment = $("#input_comment").val()

            $.get("/getutil?task=update_comments&post_id=" + $.getUrlParam("id") + "&commenter_id=" + user.id + "&content=" + comment
                + "&commenter_login=" + user.login, function (response) {
                switch (response) {
                    case "not_ok":
                        break
                    default:
                        $("#commentsSection").append(
                            $("<div>").addClass("comment").append(
                                $("<div>").addClass("justify-content-between").css("display", "flex").append(
                                    $("<div>").addClass("user-avatar").append(
                                        $("<a>").attr("href", "/profile?id=" + user.id).append(
                                            $("<span>").addClass("user-id font-weight-bold").css("color", "#000").text(user.login)
                                        )
                                    ),
                                    $("<p>").addClass("timestamp font-weight-light m-1 text-right").css("font-size", "12px").text(response)
                                ),
                                $("<div>").addClass("comment-details").append(
                                    $("<div>").addClass("justify-content-between text-wrap").css("display", "flex").append(
                                        $("<p>").addClass("comment-text text-break text-justify m-1").text(comment)
                                    )
                                )
                            )
                        )
                        break
                }
            })
        })
    })
</script>