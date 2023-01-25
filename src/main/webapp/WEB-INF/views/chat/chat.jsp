<%@ page contentType="text/html;charset=UTF-8" %>

<%
    // 쿠키값 가져오기
    Cookie[] cookies = request.getCookies() ;

//    if(cookies != null){
//
//        for(Cookie c: cookies){
//            out.print("name: "+ c.getName() + "<br>");
//            out.print("value: " + c.getValue()+"<br>");
//            out.print("----------------------------------<br>");
//        }
//    } else {
//        out.print("cookie null");
//    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <div class="fixed-bottom me-3 mb-3" align="right">
            <div id="chatArea" class="w-25 h-50 form-control text-bg-warning" style="height: 100px"></div>
            <div class="input-group w-25">
                <input id="message" type="text" class="form-control text-white text-bg-dark" onkeyup="enterKey()">
                <button type="button" class="btn btn-outline-light text-bg-warning" onclick="send()">전송</button>
            </div>
        </div>

    <script>
        const webSocket = new WebSocket("ws://localhost:8080/ws/chat");

        webSocket.onmessage = onMessage;
        webSocket.onopen = onOpen;
        webSocket.onclose = onClose;

        function send() {
            let msg = $("#message").val();

            console.log("메시지 : " +msg);
            webSocket.send(msg);
            $("#message").val('');
        }

        function onClose(event) {
            webSocket.send("채팅창을 나갔습니다.");
        }

        function onOpen(event) {
            webSocket.send("채팅창을 입장합니다 .");
        }

        function onMessage(msg) {
            let message = msg.data;

            // let str = "<div class='col-6'>";
            // str += "<div class='alert alert-secondary'>";
            let str = "<p><b>"+message+"</b></p>";
            // str += "</div></div>";
            $("#chatArea").append(str);
        }

        // 채팅이 많아져 스크롤바가 넘어가더라도 자동적으로 스크롤바가 내려가게함
        window.setInterval(function() {
            let elem = document.getElementById('message');
            elem.scrollTop = elem.scrollHeight;
        }, 0);

        // 엔터키를 통해 send함
        function enterKey() {
            if (window.event.keyCode == 13) {
                send();
            }

            // $("#message").addEventListener("keydown", function(event) {
            //     if(event.code === "Enter")
            //         send();
            // });
        }

    </script>
    </body>
</html>