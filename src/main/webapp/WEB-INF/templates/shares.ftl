<#include "base_page.ftl">

<#macro title>Shares</#macro>

<#macro page_content>
    <div class="container" style="background-color: #eee">
        <div class="row">
            <div class="col lg-8">
                <input aria-label="input_share" type="text" class = "form-control" id="search_share">
                <button type="button" class="btn btn-outline-secondary" id="btn_find">Find share</button>
            </div>
            <div class="text-wrap">Data is given by MOEX</div>
        </div>
    </div>
</#macro>

<script>
    $(document).ready(function (){
        $("#btn_find").on("click", function () {
            let share_inp = $("#search_share").val()
            if (share_inp.length <= 5) {
                alert("Too few info")
                return
            }
            $.get("https://iss.moex.com/iss/securities.json?q=" + share_inp, function (response) {
                let data = response.securities.data
                for (let i = 0; i < data.length; i++) {
                    let share = data[i]
                    let share_name = share[2]
                    let sec_id = share[1]
                    $.get("https://iss.moex.com/iss/statistics/engines/stock/currentprices.json", function (response) {
                        let price_data = response.currentprices.data
                        for (let p = 0; p < price_data.length; p++) {
                            if (price_data[p][2] === sec_id) {
                                alert(share_name + ": " + price_data[p][4] + "RUB")
                            }
                        }
                    })
                }
            })
        })
    })
</script>