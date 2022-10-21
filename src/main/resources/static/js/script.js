/*
 * header
 */

// 실시간 주인장 포지션 가져오기
$(function () {
    timer = setInterval(function () {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/v1/upbit/account",
            contentType: "application/json",
            dataType: "json",
            success: function (data, status) {
                if (data.length != 0) {
                    $.each(data, function (index, value) {
                        var str = "| ";
                        str += value.curruncy + " ";
                        str += "매수가 : " + value.myPrice + " 원 | ";
                        str += "현재가 :" + value.nowPrice + " 원 | ";
                        str += "손익 :" + value.profit + " ";

                        $('#position').text(str);
                    });
                } else {
                    $('#position').text("| 포지션 없음");
                }
            },
            error: function (status) {
                $('#position').text("| 포지션 없음");
            }
        });
    }, 1000);
});

// Nav Active Change , 네비게이션 활성화
$(document).ready(function () {
    var path = window.location.pathname;

    if (path == "/index")
        $("#header-nav-1").addClass("active");
    else if(path == "/free-board/list")
        $("#header-nav-2").addClass("active");
    else if(path == "/free-board/form")
        $("#header-nav-2").addClass("active");
    else
        $("#header-nav-1").addClass("active");
})


// *******************************************************

/*
 * Index
 */

$(function () {
    timer = setInterval(function () {
        $.ajax({
            type: "get",
            url: "http://localhost:8080/api/v1/coinpaprika/tickers",
            contentType: "json",
            success: function (data, stats) {
                var table;

                for (key in data) {
                    table += '<tr>';
                    table += '<td></td>'
                    table += '<td><img src="' + data[key].image + '" style="width:15%; height:15%">&nbsp;' + data[key].name + '</td>';
                    table += '<td>' + data[key].symbol + '</td>';
                    table += '<td>' + data[key].current_price + '</td>';
                    table += '<td>' + data[key].market_cap + '</td>';
                    table += '<td>' + data[key].price_change_percentage_1h_in_currency.toFixed(2) + '</td>';
                    table += '<td>' + data[key].price_change_percentage_7d_in_currency.toFixed(2) + '</td>';
                    table += '<td>' + data[key].price_change_percentage_24h_in_currency.toFixed(2) + '</td>';
                    table += '</tr>';
                }

                $("#coingeckoTbody").empty();
                $("#coingeckoTbody").append(table);
                console.log(data);
            },
            error: function (status) {
                console.log("error");
            }
        });
    }, 5000);
});

// *******************************************************

/**
 *  FreeBoard
 */

$("#freeBoardForm").addEventListener("keydown", function(event) {
   if(event.code === "Enter")
       event.preventDefault();
});