<#include "base_page.ftl">

<#macro title>Profile</#macro>

    <style>
        .container[id="profile"] {
            background-color: #eee;
        }
        .img-fluid[id="user_pfp"] {
            width: 150px;
            height: 150px;
        }
        .img-fluid[id="like_img"] {
            width: 32px;
            height: 24px;
        }
        .img-fluid[id="like_img_2"] {
            width: 32px;
            height: 24px;
        }
    </style>

<#macro page_content>
    <section>
        <div class="container py-5" id="profile">
            <div class="row">
                <div class="col-lg-3">
                    <div class="card mb-4">
                        <div class="card-body text-center">
                            <#if isSameUser == false>
                                <img id="user_pfp" src="${profile_user.profilePicture}" alt="avatar" class="img-fluid">
                            <#else>
                                <form action="/upload?entity=pfp&id=${user.id}"  method="post" enctype="multipart/form-data">
                                    <img id="user_pfp" src="${profile_user.profilePicture}" alt="avatar" class="img-fluid">
                                    <input type="file" class="custom-file-input" id="img" name="img">
                                    <button type="submit" class="btn-outline-secondary" id="img_btn">Save</button>
                                </form>
                            </#if>
                            <h5 id="login" class="my-3">${profile_user.login}</h5>
                            <#if isSameUser == false && user?has_content>
                                <div class="container">
                                    <div class="col-lg-12 justify-content-center">
                                        <div class="row justify-content-between">
                                            <div class="col lg-3"></div>
                                            <div class="col align-self-center lg-3">
                                                <img src="https://logowik.com/content/uploads/images/940_like_icon.jpg" alt="like" class="img-fluid" id="like_img">
                                                <div id="like_count">${(profile_user.likes)!0}</div>
                                            </div>
                                            <div class="col align-self-center lg-3">
                                                <button type="button" id="like" class="btn btn-primary">Like</button>
                                            </div>
                                            <div class="col lg-3"></div>
                                        </div>
                                    </div>
                                </div>
                            </#if>
                            <#if isSameUser == true || !user?has_content>
                                <div class="container">
                                    <div class="col-lg-12 justify-content-center">
                                        <div class="row justify-content-between">
                                            <div class="col lg-3"></div>
                                            <div class="col align-self-center lg-3">
                                                <img src="https://logowik.com/content/uploads/images/940_like_icon.jpg" alt="like" class="img-fluid" id="like_img">
                                                <div id="like_count">${(profile_user.likes)!0}</div>
                                            </div>
                                            <div class="col lg-3"></div>
                                        </div>
                                    </div>
                                </div>
                            </#if>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5">
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-5">
                                    <p class="mb-0">Name</p>
                                </div>
                                <div class="col-sm-7">
                                    <#if isSameUser == false>
                                        <p id="name" class="text-muted mb-0">${(profile_user.name)!"Not specified"}</p>
                                    <#else>
                                        <input id="input_name" name="input_name" type="text" class="d-flex form-control" aria-label="input_name" placeholder="${(profile_user.name)!"Not specified"}">
                                    </#if>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-5">
                                    <p class="mb-0">Last name</p>
                                </div>
                                <div class="col-sm-7">
                                    <#if isSameUser == false>
                                        <p id="lastname" class="text-muted mb-0">${(profile_user.lastname)!"Not specified"}</p>
                                    <#else>
                                        <input id="input_lastname" name="input_lastname" type="text" class="d-flex form-control" aria-label="input_lastname" placeholder="${(profile_user.lastname)!"Not specified"}">
                                    </#if>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-5">
                                    <p class="mb-0">Email</p>
                                </div>
                                <div class="col-sm-7">
                                    <#if isSameUser == false>
                                        <p id="email" class="text-muted mb-0">${profile_user.email}</p>
                                    <#else>
                                        <input id="input_email" name="input_email" type="email" class="d-flex form-control" aria-label="input_email" placeholder="${profile_user.email}">
                                    </#if>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-5">
                                    <p class="mb-0">Registration date</p>
                                </div>
                                <div class="col-sm-7">
                                    <p id="lastname" class="text-muted mb-0">${profile_user.dateRegistered}</p>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-5">
                                    <p class="mb-0">Country</p>
                                </div>
                                <div class="col-sm-7">
                                    <#if isSameUser == false>
                                        <p id="country" class="text-muted mb-0">${(profile_user.country)!"Not specified"}</p>
                                    <#else>
                                        <input id="input_country" type="text" name="input_country" class="d-flex form-control" aria-label="input_country" placeholder="${(profile_user.country)!"Not specified"}">
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="col-sm-9">
                                <#if isSameUser == false>
                                    <p id="bio" class="mb-0 text-wrap">${(profile_user.bio)!"Not specified"}</p>
                                <#else>
                                    <textarea id="input_bio" class="mb-0 text-wrap" aria-label="input_bio" rows="8" cols="40" placeholder="${(profile_user.bio)!"Not specified"}"></textarea>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <#if isSameUser == true>
                    <button class="btn btn-primary" type="button" id="save_changes">Save changes</button>
                </#if>
            </div>
        </div>
    </section>
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
        if (typeof user !== undefined) {
            let profileUserId = $.getUrlParam("id")
            if (typeof profileUserId !== "undefined") {
                let likeSenderId = user.id
                if (profileUserId !== likeSenderId) {
                    $.get("/getutil?task=check_likes&receiver_id=" + $.getUrlParam("id") + "&sender_id=" + user.id,
                        function (response) {
                            switch (response) {
                                case "not_ok":
                                    document.getElementById("like").setAttribute("disabled", "")
                                    break
                                case "ok":
                                    $(document).on("click", $("#like"), function () {
                                        $.get("/getutil?task=update_likes&receiver_id=" + $.getUrlParam("id") + "&sender_id=" + user.id,
                                            function (response) {
                                                $("#like_count").text(response)
                                                document.getElementById("like").setAttribute("disabled", "")
                                            }
                                        )
                                    })
                                    break
                            }
                        })
                }
            }
            function readURL(input) {

                if (input.files && input.files[0]) {
                    let reader = new FileReader();

                    reader.onload = function (e) {
                        $('#user_pfp').attr('src', e.target.result);
                    }

                    reader.readAsDataURL(input.files[0]);
                }
            }

            $("#img").change(function(){
                readURL(this);
            });

            $("#save_changes").on("click", function () {
                let requestUrl = "/getutil?task=update_user_data&" + "id=" + user.id

                let name = $("#input_name").val()
                if (typeof name !== "undefined") {
                    $("#input_name").val("")
                    name = name.trim()
                    if (name) {
                        $("#input_name").attr("placeholder", name)
                        requestUrl = requestUrl + "&name=" + name
                    }
                }

                let lastname = $("#input_lastname").val()
                if (typeof lastname !== "undefined") {
                    $("#input_lastname").val("")
                    lastname = lastname.trim()
                    if (lastname) {
                        $("#input_lastname").attr("placeholder", lastname)
                        requestUrl = requestUrl + "&lastname=" + lastname
                    }
                }

                let email = $("#input_email").val()
                if (typeof email !== "undefined") {
                    $("#input_email").val("")
                    email = email.trim()
                    if (email) {
                        $("#input_email").attr("placeholder", email)
                        requestUrl = requestUrl + "&email=" + email
                    }
                }

                let bio = $("#input_bio").val()
                if (typeof bio !== "undefined") {
                    $("#input_bio").val("")
                    bio = bio.trim()
                    if (bio) {
                        $("#input_bio").attr("placeholder", bio)
                        requestUrl = requestUrl + "&bio=" + bio
                    }
                }

                let country = $("#input_country").val()
                if (typeof country !== "undefined") {
                    country = country.trim()
                    $("#input_country").val("")
                    if (country) {
                        $("#input_country").attr("placeholder", country)
                        requestUrl = requestUrl + "&country=" + country
                    }
                }

                if (requestUrl !== "/getutil?task=update_user_data") {
                    $.get(requestUrl, function (response) {
                        if (response === "ok") {
                            $.get("/getutil?task=get_user_data", function (response) {
                            } )
                        }
                    })
                }
            })
        }
    })
</script>