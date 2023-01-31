Coinrich

개발 환경

- Spring boot 2.7
- Java 1.8
- Gradle

---



주요기능
![image-20230130140911455](/Users/EunChan/Library/Application Support/typora-user-images/image-20230130140911455.png)
1. 현물 포지션 현황

upbit API 를 통해 관리자 현물 포트폴리오 내역(매수가, 현재가, 손익률) 알림



2. Bitcoin Chart

BTC, ETH 1분봉 현물 거래차트

데이터 - Bitget API

차트 - LightWeight Chart(Trading View에서 무료 제공)



3. 시가총액별 암호화폐 시세

1 - 30 위 까지의 총 시가총액 별 테이블

데이터 - CoinGecko API



4. 자유 게시판

단순 익명게시판으로 CRUD 기능



5. 채팅 

사이트 방문자들 간의 채팅

단순 웹소켓 통신

현재 기능 개발 중



6. Discord 연동

Discord & Scheduling 을 연동한 100틱 알람
![image-20230130172603571](/Users/EunChan/Library/Application Support/typora-user-images/image-20230130172603571.png)


Bitget API 의 BTC 현재 가격 & Discord Webhook 를 활용하여 BTC 100 단위 가격 마다 알람

단타매매의 용도



Youtube Searcher 를 통한 뮤직 봇



Youtube Searcher 와 Discord Webhook 를 활용하여 명령어를 통한 음악 재생

현재 음악 재생만 가능 다른 기능은 개발 중

---



Todo List

1. 차트 관련

1분봉을 제외한 분,시간,날 봉 차트 제공

시간 봉 or 날 봉에서 중요 이평선 - etc 20,60 등등 터치시 알림

나스닥, 달러, 도미넌스 등 주요 지표 차트 추가



2. 채팅 관련

사이트 입장 시 랜덤 닉네임 부여 및 변경 가능하도록 설정

귓속말 기능 추가



3. 경제 주요 지표 뉴스 게시판 추가
