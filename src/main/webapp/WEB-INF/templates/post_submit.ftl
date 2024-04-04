<#include "base_page.ftl">

<#macro title>
    Submit post
</#macro>

<style>
    .container[id="post_container"] {
        background-color: #eee;
    }
</style>

<#macro page_content>
    <div class="container" id="post_container">
        <form action="/upload?entity=post&id=${user.id}" method="post" enctype="multipart/form-data">
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex row align-content-center">
                        <div class="col-3"></div>
                        <div class="col-6 align-content-center">
                            <div class="row align-self-center">
                                <img id="current_post_img" src="https://res.cloudinary.com/dwsn91q46/image/upload/v1698496954/127523-n42lmrytsk5f8acijhwp_whbcgk.jpg" alt="post_image" class="img-fluid mt-4" width="300" height="200">
                            </div>
                            <div class="card mb-3 mt-3">
                                <div class="input-group mt-mb-1">
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="img" name="img" required>
                                    </div>
                                </div>
                            </div>
                            <div class="input-group mb-3 mt-md-3">
                                <input type="text" id="post_title" name="post_title" class="form-control" placeholder="Post title" aria-label="Post title" aria-describedby="basic-addon1" required>
                            </div>
                        </div>
                        <div class="col-3"></div>
                    </div>
                    <div class="input-group mt-3 mb-4">
                        <textarea class="form-control" aria-label="With textarea" rows="2" placeholder="Post short description" id="short_desc" name="short_desc" required></textarea>
                    </div>

                    <div class="input-group mt-3 mb-4">
                        <textarea class="form-control" aria-label="With textarea" rows="8" placeholder="Post content" id="content" name="content" required></textarea>
                    </div>
                    <div id="warning_msg"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4 justify-content-center">
                    <button type="submit" id="submit_btn" class="btn btn-primary">Submit</button>
                </div>
                <div class="col-4"></div>
            </div>
        </form>
    </div>
</#macro>

<script>
    $(document).ready(function () {
        function readURL(input) {
            if (input.files && input.files[0]) {
                let reader = new FileReader();
                reader.onload = function (e) {
                    $('#current_post_img').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }

        $("#img").change(function(){
            readURL(this);
        });

        $("#post_title").on("input", function () {
            let text = $(this).val()
            if (typeof text !== "undefined") {
                $.get("/getutil?task=check_title&post_title=" + text, function (response) {
                    switch (response) {
                        case "not_ok":
                            document.getElementById("submit_btn").setAttribute("disabled", "")
                            break
                        case "ok":
                            document.getElementById("submit_btn").removeAttribute("disabled")
                            break
                    }
                })
            }
        })
        $("#content").on("input", function () {
            let text = $(this).val()
            if (text.length >= 500) {
                $("#warning_msg").text("Post is too long, should be less than 500 charactes")
                document.getElementById("submit_btn").setAttribute("disabled", "")
            }
            else {
                $("#warning_msg").text("")
                document.getElementById("submit_btn").removeAttribute("disabled")
            }
        })


    })
</script>