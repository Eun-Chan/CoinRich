/*
 * header
 */

function upbitPosition() {
    $.ajax({
        type: "get",
        url: "http://localhost:8080/api/v1/coinpaprika/tickers",
        contentType: "json",
        success: function (data, stats) {
            var table;

            for (key in data) {
                table += '<tr>';
                table += '<td><img src="' + data[key].image + '" style="width:15%; height:15%"></td>'
                table += '<td>&nbsp;' + data[key].name + '</td>';
                table += '<td>' + data[key].symbol + '</td>';
                table += '<td>' + data[key].current_price + '</td>';
                table += '<td>' + data[key].market_cap + '</td>';
                table += '<td class="color-of-num">' + data[key].price_change_percentage_1h_in_currency.toFixed(2) + '</td>';
                table += '<td class="color-of-num">' + data[key].price_change_percentage_24h_in_currency.toFixed(2) + '</td>';
                table += '<td class="color-of-num">' + data[key].price_change_percentage_7d_in_currency.toFixed(2) + '</td>';
                table += '</tr>';
            }

            $("#coingeckoTbody").empty();
            $("#coingeckoTbody").append(table);
            colorOfNumber();
            // console.log(data);
        },
        error: function (status) {
            console.log("error");
        }
    });
}

// 실시간 주인장 포지션 가져오기 (Upbit)
$(function () {
    timer = setInterval(function () {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/v1/upbit/account",
            contentType: "application/json",
            dataType: "json",
            success: function (data, status) {
                if (data.length != 0) {
                    var str = "";
                    $.each(data, function (index, value) {
                        str += "| ";
                        str += value.curruncy + " ";
                        str += "매수가 : " + value.myPrice + " 원 | ";
                        str += "현재가 :" + value.nowPrice + " 원 | ";
                        str += "손익 :" + value.profit + " ";
                        
                    });
                    $('#position').text(str);
                } else {
                    $('#position').text("| 포지션 없음");
                }
            },
            error: function (status) {
                $('#position').text("| 포지션 없음");
            }
        });
    }, 5000);
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

// $(function () {
//     upbitPosition();
//     timer = setInterval(upbitPosition, 5000);
// });

// *******************************************************

/**
 *  FreeBoard
 */

// function save() {
//     const data = {
//         writer : $("#freeBoardWriter").val(),
//         password : $("#freeBoardPassword").val(),
//         title : $("#freeBoardTitle").val(),
//         content : $("#summernote").val(),
//         image : new FormData($("inputGroupFile02")[0])
//     }
//
//     if(!data.writer || data.writer.trim() === "" || !data.password || data.password.trim() === "" ||
//         !data.title || data.title.trim() === "" || !data.content || data.content.trim() === "") {
//         alert("공백 또는 입력하지 않은 부분이 있습니다.");
//         return false;
//     } else {
//         $.ajax({
//             type : "post",
//             url : "/api/v1/free-board",
//             contentType: "application/json; charset=utf-8",
//             data : JSON.stringify(data),
//             dataType : "text",
//             enctype:'multipart/form-data',
//             success : function(res) {
//                 alert("게시판이 등록 되었습니다.");
//                 location.href = "/free-board/list";
//             },
//             error : function(res) {
//                 location.href = "/free-board/list";
//             }
//         })
//     }
// }

/**
 * FreeBoard - Detail
 */


/**
 * Comment
 */
function commentSave() {
    const data = {
        writer : $("#commentWriter").val(),
        password : $("#commentPassword").val(),
        content : $("#commentContent").val()
    }


    let freeBoardId = $("#freeBoardId").val();

    if(!data.writer || data.writer.trim() === "" || !data.password || data.password.trim() === "" || !data.content || data.content.trim() === "") {
        alert("공백 또는 입력하지 않은 부분이 있습니다.");
        return false;
    } else {
        $.ajax({
            type : "post",
            url : "/api/v1/free-board/"+freeBoardId+"/comment",
            contentType : "application/json; charset=utf-8",
            data : JSON.stringify(data),
            success : function(res) {
                alert("댓글이 등록 되었습니다.");
                location.reload();
            },
            error : function(res) {
                location.reload();
            }
        })
    }

}