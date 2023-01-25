<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en" class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.101.0">
    <title>CoinRich(비트 가격 넣기)</title>

    <script src="https://unpkg.com/lightweight-charts/dist/lightweight-charts.standalone.production.js"></script>

</head>
<body class="d-flex text-center text-bg-dark">

<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
    <jsp:include page="../layout/header.jsp">
        <jsp:param name="header" value="header" />
    </jsp:include>

    <div class="d-flex">
        <div id="btcChart" class="m-3"></div>
        <div id="ethChart" class="m-3"></div>
    </div>

    <table class="table table-hover table-dark">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">종목</th>
            <th scope="col">기호</th>
            <th scope="col">가격(KRW)</th>
            <th scope="col">총 시가</th>
            <th scope="col">변동(1H)</th>
            <th scope="col">변동(24H)</th>
            <th scope="col">변동(7D)</th>
        </tr>
        </thead>
        <tbody id="coingeckoTbody"></tbody>
    </table>

    <jsp:include page="../layout/footer.jsp">
        <jsp:param name="footer" value="footer" />
    </jsp:include>
</div>

</body>
    <script>
        $(function () {
            upbitPosition();
            // setInterval(colorOfNumber, 5000);
            timer = setInterval(upbitPosition, 5000);
        });

        let btcChart = LightweightCharts.createChart($("#btcChart")[0], {
            width: 450,
            height: 300,
            timeScale: {
                timeVisible: true,
                borderColor: '#D1D4DC',
            },
        });

        let ethChart = LightweightCharts.createChart($("#ethChart")[0], {
            width: 450,
            height: 300,
            timeScale: {
                timeVisible: true,
                borderColor: '#D1D4DC',
            },
        });

        let btcLineSeries = btcChart.addCandlestickSeries();
        let ethLineSeries = ethChart.addCandlestickSeries();

        let btcCurrentBar = {
          open : null,
          high : null,
          low : null,
          close : null,
          time : null
        };

        let ethCurrentBar = {
            open : null,
            high : null,
            low : null,
            close : null,
            time : null
        };

        btcChart.applyOptions({
            priceScale : {
                autoScale : true,
            },
            timeScale : {
                barSpacing : 20,
                // tickMarkFormatter: (time, tickMarkType3, locale) => {
                //     return time['year']+'-'+time['month']+'-'+time['day']
                // },
            }
        });

        ethChart.applyOptions({
            priceScale : {
                autoScale : true,
            },
            timeScale : {
                barSpacing : 20,
            }
        });

        btcLineSeries.setData(getChartData("BTCUSDT_UMCBL"));
        ethLineSeries.setData(getChartData("ETHUSDT_UMCBL"));

        function getChartData(target) {
            let result = "";

            $.ajax({
                type : "get",
                url : "/api/v1/bitget/market/candle?target="+target,
                contentType : "application/json, charset=utf-8",
                async : false,
                success : function (res) {
                    result = res;
                },
                error : function(err) {
                    console.log(err);
                }
            });
            return result;
        }

        setInterval(function() {
            let btcData = getChartData("BTCUSDT_UMCBL");
            let ethData = getChartData("ETHUSDT_UMCBL");

            btcCurrentBar.open = btcData[btcData.length - 1].open;
            btcCurrentBar.high = btcData[btcData.length - 1].high;
            btcCurrentBar.low = btcData[btcData.length - 1].low;
            btcCurrentBar.close = btcData[btcData.length - 1].close;
            btcCurrentBar.time = btcData[btcData.length - 1].time;

            ethCurrentBar.open = ethData[ethData.length - 1].open;
            ethCurrentBar.high = ethData[ethData.length - 1].high;
            ethCurrentBar.low = ethData[ethData.length - 1].low;
            ethCurrentBar.close = ethData[ethData.length - 1].close;
            ethCurrentBar.time = ethData[ethData.length - 1].time;

            btcLineSeries.update(btcCurrentBar);
            ethLineSeries.update(ethCurrentBar);
        },70);

        setInterval(function() {
            btcLineSeries.setData(getChartData("BTCUSDT_UMCBL"));
            ethLineSeries.setData(getChartData("ETHUSDT_UMCBL"));
        },6000);

        function colorOfNumber() {
            const colorOfNumber = document.getElementsByClassName("color-of-num");

            for(var i = 0; i < colorOfNumber.length; i++) {
                if(colorOfNumber.item(i).textContent > 0) {
                    colorOfNumber.item(i).classList.add("text-success");
                }
                else if(colorOfNumber.item(i).textContent < 0) {
                    colorOfNumber.item(i).classList.add("text-danger");
                }
            }
        }

    </script>
</html>
