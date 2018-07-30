# 1. What is this?
 This Project originally developed as a graduate work on 2013 by Sangkyu Lee, Wonjong Shin and Jiho Park. This Android Application is designed to bridge a gap on mobility of disabled and elderly.
 This application solely focus on _**Low-floor bus**_ arrival infomation so that its users can find out when their low-floor bus will arrive.

# 2. How to build
1. You need [Android Studio](https://developer.android.com/studio/) to build this application.
2. Just open this project with Android Studio not importing it.
3. Build and Run application

*Attention) To make this application working, you need a Google Map API key and an Open API key. Open [secrets.xml](app/src/main/res/values/secrets.xml) and put your keys in there.

# 3. Test Suggestion
 This application is intended to be used in Seoul, South Korea since it uses OpenAPI which is provided by [Seoul TOPIS (Transport Operation & Information Service)](http://topis.seoul.go.kr/eng/main/main.jsp)

 When testing this application, I recommend you to use following GPS coordinates on your testing device. [Google Map Link](https://www.google.com/maps/@37.4918,127.005,18z)
- Longitude: 127.005000
- Latitude: 37.491800

# 4. Characteristics
- TODO

# 5. Screenshots
TODO

# 6. TODO
- [x] Seperate API keys with source codes. @2018-07-29
- [ ] Refactor Open API codes.
- [ ] Update Play Services Maps library.
- [ ] Rework UI/UX design.
- [ ] Implement API Key protection methods. (e.g https...)

# 7. References
[서울시교통정보과 버스정보 Open API; Seoul TOPIS BusInfo Open API](http://api.bus.go.kr/)

[공공데이터포털; Public Data Portal](https://www.data.go.kr/)  
[Link (EN)](https://www.data.go.kr/?lang=en)

_ _ _

[버스위치정보조회 서비스; Bus location information inquiry service](https://www.data.go.kr/dataset/15000332/openapi.do)  
[Link (EN)](https://www.data.go.kr/dataset/15000332/openapi.do?lang=en)

[정류소정보조회 서비스; Stop information inquiry service](https://www.data.go.kr/dataset/15000303/openapi.do)  
[Link (EN)](https://www.data.go.kr/dataset/15000303/openapi.do?lang=en)

[노선정보조회 서비스; Route information inquiry service](https://www.data.go.kr/dataset/15000193/openapi.do)  
[Link (EN)](https://www.data.go.kr/dataset/15000193/openapi.do?lang=en)

[대중교통환승경로 조회 서비스; Transit Transit route lookup service](https://www.data.go.kr/dataset/15000414/openapi.do)  
[Link (EN)](https://www.data.go.kr/dataset/15000414/openapi.do?lang=en)

[버스도착정보조회 서비스; Bus arrival information inquiry service](https://www.data.go.kr/dataset/15000314/openapi.do)  
[Link (EN)](https://www.data.go.kr/dataset/15000314/openapi.do?lang=en)

[버스위치정보조회 서비스; Bus location information inquiry service](https://www.data.go.kr/dataset/15000332/openapi.do)  
[Link (EN)](https://www.data.go.kr/dataset/15000332/openapi.do?lang=en)
