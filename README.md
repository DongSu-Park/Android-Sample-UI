# Android-App-School-Study - 안드로이드 Sample UI -
## 1. 개요
학교 강의인 '스마트폰앱개발' 의 마지막 과제의 다양한 안드로이드 기능 시연을 위한 앱개발을 진행하였습니다.

지금까지 배운 핵심 기능을 전체 다 넣어서 구현해보라는 내용으로 오로지 해당 기능을 시연 해봐라는 내용이라 하나의 실용성 앱을 만드는 것은 아니었습니다.

하지만 이 과제를 통해 각 기능이 어떻게 동작을 하고 만들어야하는지 실습을 해보았고 나름대로의 UI와 앱의 실용 의미부여를 넣기 위해 
**'안드로이드 Sample UI'** 라는 앱의 제목을 가지고 만들었습니다.

전체적인 구조와 방식은 **이지스퍼브리싱에서 제공하는 'Do it! 안드로이드 앱 프로그래밍 - 6판'** 을 참고하여 제작을 하였고 해당 소스를 조금 변형을 하여 각종 기능을 구현하였습니다.

## 2. 작품 소개
이 앱을 제작한 의도는 처음으로 안드로이드 개발자를 목표로 공부를 하기 위해 다양한 책을 참고를 할텐데 저 같은 경우 **'Do it! 안드로이드 앱 프로그래밍'** 을 참고하여 공부를 하였습니다. 책으로만 공부하면서 느낀점은 이 기능을 수행하는데 어떻게 동작하는지 확인할 방법이 없는 것 입니다.

이 앱은 해당 책의 각 챕터에 있는 기능들을 Demo 형식처럼 보여주는 Sample UI 어플리케이션 입니다. 이렇게 제작한 이유는 이 책의 각 기능들의 챕터를 공부하기 전에 샘플 UI와 동작을 살펴보면서 이 기능이 이런식으로 작동하는 것을 알기 위해 만들었습니다.

실제로 앱 개발자 중에서 자신이 만든 기본이 되는 레이아웃 샘플 UI와 기능 소스들을 오픈소스로 공유 할 때 github 뿐만 아니라 가지고 있는 폰에서도 동작 할 수 있게 apk 링크를 드롭박스 형식으로 올리거나 Google Play Store에 올려서 이 소스가 동적상황에서 어떻게 동작하는지 알려주고 있습니다.

이 어플리케이션 개발 방향도 이런 방식으로 나타내고 제작하였습니다.

## 3. 각 구현 기능
> ### 1. 공통
<div>
<img width="25%" src="https://user-images.githubusercontent.com/41635289/71794081-35390600-3083-11ea-88ff-ef0083842439.jpg" hspace=20>
<img width="25%" src="https://user-images.githubusercontent.com/41635289/71794189-b98b8900-3083-11ea-987d-94947459a08d.jpg">
</div>

첫번째 사진의 메인 화면은 각 챕터의 이름을 리스트 형식으로 보여주고 있으며 스크롤 뷰를 통해 5~14장의 내용을 보여줍니다.

두번째 사진은 해당 기능의 간단한 설명 내용을 나타내고 OK 버튼을 클릭하거나 각 기능 수행 버튼을 클릭하면 해당 기능의 엑티비티 화면으로 이동합니다.

또한 각 엑티비티 이동시 이질감을 느끼지 않도록 애니메이션 기능을 추가하였습니다. 해당 소스는 **res/anim 의 fadein.xml, fadeout.xml** 를 통해 확인 할 수 있습니다.

> ### 2. 프래그먼트
<div>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71795929-cdd38400-308b-11ea-9ff7-70f2af7f0b1d.jpg" hspace=20>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71795931-cf9d4780-308b-11ea-8b99-99d98abc9749.jpg" hspace=20>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71795934-d1ffa180-308b-11ea-9262-c71916920338.jpg">
</div>

  프래그먼트를 통한 상단탭, 뷰페이저, 바로가기 메뉴 기능의 화면입니다. 각 기능별 소스는 **ch05_*.java 또는 ch05_*.xml**를 참고하시기 바라며 화면을 나태내는 프래그먼트는 공통으로 **fragment1, fragment2, fragment3, fragment4**로 사용하였습니다.

> ### 3. 서비스, 브로드케스트&위험권한
<div>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71796155-c1036000-308c-11ea-945a-f2def391203d.jpg" hspace=20>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71796156-c2348d00-308c-11ea-91e3-f9d172213d01.jpg">
</div>

안드로이드의 핵심 기능의 서비스와 위험권한 부여 관련 기능입니다. 전체적인 틀은 **프래그먼트의 바로가기 메뉴** 형식으로 만들었으며 바로가기 메뉴를 클릭 시 두 개의 메뉴인 **서비스, 브로드케스트&위험권한** 이 나옵니다.

서비스의 경우 MusicService.java에서 안드로이드 Service를 상속해 MediaPlayer의 기능을 불러와 재생 버튼을 클릭하면 **res/raw**에 있는 mp3 파일이 재생하게 됩니다.

브로드케스트,위험권한은 SMS를 수신하여 내용을 나타내는 방식으로 구현하였습니다. SMS 수신을 위해 **AndroidManifest.xml**에서 SMS 퍼미션을 부여하였고 브로드케스트 단위에서 SMS를 수신시 onReceive를 실행하게 되며 각 EditText에 수신한 SMS 내용을 보여줍니다.

각 기능별 소스는 **ch06_*.java 또는 ch06_*.xml**를 참고하시기 바랍니다.

> ### 4. 선택위젯 - 스피너뷰
<div>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71796612-43405400-308e-11ea-8ba1-492aaecce393.jpg">
</div>

스피너뷰를 나타내는 화면으로 버튼 클릭시 리스트별로 이미지를 선택하는 선택위젯이 나타나고 각 이미지 클릭 시 뷰에 나타나는 이미지가 바뀝니다.

각 기능별 소스는 **ch07_*.java 또는 ch07_*.xml**를 참고하시기 바랍니다.

> ### 5. 데이터베이스
<div>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71796773-bc3fab80-308e-11ea-833f-992f6e030756.jpg">
</div>

안드로이드 내에 있는 SQLite 기능을 불러와 데이터베이스를 작동하는 내용입니다.

소스 내에 SQL DDL, DML 문을 넣어 추가, 변경, 삭제, 조회 기능을 실행하게 만들었습니다.

각 기능별 소스는 **ch11_*.java 또는 ch11_*.xml**를 참고하시기 바랍니다.

> ### 6. 그래픽
<div>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71796909-61f31a80-308f-11ea-8be0-2aed1790cb34.jpg" hspace=20>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71796910-63bcde00-308f-11ea-8993-226d88be3b7c.jpg" hspace=20>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71796914-661f3800-308f-11ea-9707-d5e57b02939d.jpg">
</div>

그래픽 툴을 사용해 화면상의 그림을 그리는 간단한 그림판 기능 입니다.

선의 색상 변경은 외부 라이브러리인 **Ambilwarna Color Picker** 를 사용하여 위의 그림 버튼 클릭 시 다이얼로그 형식으로 색상을 변경 할 수 있습니다.

선의 굵기 변경도 그림 버튼 클릭 시 다이얼로그 형식으로 해당 값을 입력 해 변경 할 수 있습니다.

각 기능별 소스는 **ch12_*.java 또는 ch12_*.xml**를 참고하시기 바랍니다.

> ### 7. 멀티미디어
<div>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71797104-112ff180-3090-11ea-8332-3362fec19f44.jpg" hspace=20>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71797110-15f4a580-3090-11ea-9ce0-720fa47f1292.jpg" hspace=20>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71797111-1725d280-3090-11ea-9fcd-17296b998391.jpg">
</div>

<div>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71797113-18ef9600-3090-11ea-8e8f-3a26cea0f49e.jpg" hspace=20>
  <img width="25%" src="https://user-images.githubusercontent.com/41635289/71797123-1f7e0d80-3090-11ea-82c3-0a41d5bf448d.jpg">
</div>

멀티미디어는 카메라, 음악, 동영상, 유튜브 동영상 기능을 구현하였고, 프래그먼트 형식의 바로가기 메뉴의 전체적인 틀을 만들어 작성하였습니다.

각 기능의 경우 SDK 버전을 29로 타겟팅한 경우 범위지정소를 정해야하는 규칙이 있기 때문에 scoped storage의 내용을 참고 하였습니다.

하지만 카메라 기능의 경우 사진의 저장소를 경로를 지정하는 방식이기 때문에 안드로이드 10의 경우 이미지가 불러오지 않은 이슈가 있습니다.

이는 scoped storage에서 **requestLegacyExternalStorage** 를 AndroidManifest.xml에서 넣어줌으로써 해결이 되었습니다.

유튜브 동영상은 구글에서 제공하는 YoutubeAndroidPlayerApi.jar를 사용하였으며 해당 기능에서는 위에는 Youtube 영상이 밑은 애플 기기의 스팩을 나타내는 웹페이지로 보여주는 웹뷰를 사용하였습니다.

각 기능별 소스는 **ch13_*.java 또는 ch13_*.xml**를 참고하시기 바랍니다.

> ### 8. 지도

구글 맵스와 지오코딩을 이용해 지도를 표시하고 위의 EditText 내용에 해당 주소를 입력해 검색을 누르면 해당 위치를 마크로 표시해 주소의 내용을 표시해줍니다.

각 기능별 소스는 **ch14_*.java 또는 ch14_*.xml**를 참고하시기 바랍니다.

## 4. 참고 문헌 및 링크
1. 공통
* 참고 문헌 : Do it! 안드로이드 앱 프로그래밍 6판 - 이지스퍼브리싱 (정재곤 저)
* 전체 참고 소스 : https://github.com/mike-jung/DoItAndroid
* 구글 안드로이드 공식 문서 : https://developer.android.com/docs
* 화면전환 애니메이션 : https://boheeee.tistory.com/14
* 인트로 화면 : https://basicto.tistory.com/629
* 아이콘 이미지 : https://www.freepik.com/

2. 서비스
* 음악 서비스 기능 : http://bitly.kr/0uC1Je6
* SMS 위험 권한 : https://hongku.tistory.com/209

3. 그래픽
* 그림판 구현 : https://honeyinfo7.tistory.com/33
* ColorPicker : https://github.com/yukuku/ambilwarna

4. 음악
* 음악 미디어 플레이어 : https://bitsoul.tistory.com/28

5. 유튜브 동영상
* 애플 기기 이미지 및 웹사이트 : https://www.apple.com/
                               https://www.youtube.com/user/Apple

6. 지도
* 검색 기능 추가 : https://blog.naver.com/qbxlvnf11/221183308547
