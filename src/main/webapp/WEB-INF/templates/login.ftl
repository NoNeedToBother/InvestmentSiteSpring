<#include "base.ftl">

<#macro title>Login</#macro>
<html xmlns:th="http://www.thymeleaf.org">
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

    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>
<#macro content>
    <main class="form-signin w-100 m-auto">
        <form th:action="@{/login}" method="post">
            <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

            <div class="form-floating">
                <input type="text" name="login" class="form-control" id="floatingInput" placeholder="name@example.com">
                <label for="floatingInput">Login</label>
            </div>
            <div class="form-floating">
                <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
                <label for="floatingPassword">Password</label>
            </div>

            <div class="form-check text-start my-3">
                <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                    Remember me
                </label>
            </div>
            <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
            <div class="container">
                <div class="row lg-2 mt-2">
                    <div class="col-lg-6">
                        <div class="fw-light text-center text-wrap">Don't have account yet?</div>
                    </div>
                    <div class="col-lg-6">
                        <button class="btn btn-primary py-2" onclick="document.location='/register'" type="button">Register</button>
                    </div>
                </div>
            </div>
        </form>
    </main>
</#macro>