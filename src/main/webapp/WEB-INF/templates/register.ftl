<#include "base.ftl">
<#macro title>Register</#macro>
<style>
    html,
    body {
        height: 100%;
    }

    .form-signin {
        max-width: 330px;
        padding: 1rem;
        position: fixed; top: 50%; left: 50%;
        -webkit-transform: translate(-50%, -50%);
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }

    .form-signin .form-floating:focus-within {
        z-index: 2;
    }

    .form-signin input[type="text"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>
<#macro content>
    <main class="form-signin w-100 m-auto">
        <form method="post">
            <h1 class="h3 mb-3 fw-normal">Please register</h1>

            <div class="form-floating">
                <input type="text" name="login" class="form-control" id="floatingInput" placeholder="login">
                <label for="floatingInput">Login</label>
            </div>

            <div class="form-floating">
                <input type="email" name="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                <label for="floatingInput">Email</label>
            </div>

            <div class="form-floating">
                <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="password">
                <label for="floatingPassword">Password</label>
            </div>

            <div class="form-floating">
                <input type="password" name="confirmpassword" class="form-control" id="floatingPassword" placeholder="password">
                <label for="floatingPassword">Confirm password</label>
            </div>

            <div class="form-check text-start my-3">
                <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                    Remember me
                </label>
            </div>
            <button class="btn btn-primary w-100 py-2" type="submit">Register</button>
            <div class="container">
                <div class="row lg-2 mt-2">
                    <div class="col-lg-6">
                        <div class="fw-light text-center text-wrap mt-2">Have account?</div>
                    </div>
                    <div class="col-lg-6">
                        <button class="btn btn-primary py-2" onclick="document.location='/login'" type="button">Login</button>
                    </div>
                </div>
            </div>
        </form>
    </main>
</#macro>