# ADQ
# Sample Project

> ADQ SDK 최신버전은 7.1.0 버전입니다

자세한 내용은 <a href="https://api.qwertlab.com/adq/index.php" taregt="_blank">API Document 사이트</a>에서 확인해보실 수 있습니다.

<br>
<h3>연동가이드</h3>
<hr>

* settings.gradle(Project Settings) 설정

> ADQ SDK는 Maven 저장소를 통해 배포되고 있습니다. settings.gradle 파일에 ADQ Maven 정보를 등록합니다.
<br>

```java
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            google()
            mavenCentral()
            
            //-- Repository for Qwertlab SDK -----------------------------------
            maven { 
              url 'https://sdk.qwertlab.com/repository/internal' 
            }
            //---------------------------------------------------
          
        }
    }
```
<br>

* build.gradle(Module:) 설정


> ADQ SDK를 dependencies에 implementation 합니다.<br>
> Gradle Plugin 4.1 이상을 사용하실 경우 네이티브 디버그 기호파일이 생성될 수 있도록 debugSymbolLevel을 추가합니다.<br>
> (<a href='https://developer.android.com/reference/tools/gradle-api/7.3/com/android/build/api/dsl/Ndk#debugSymbolLevel()' taget='_blank'>디버그 심볼 관련내용</a>)
<br>


```java
    ...

    android {
      buildTypes {
          //-- FOR ADQ -------------------------------------------------------------------------------
          
          debug {
              ndk {
                  debugSymbolLevel 'SYMBOL_TABLE'
              }
          }
          release {
              ndk {
                  debugSymbolLevel 'SYMBOL_TABLE'
              }
          }
          
          //------------------------------------------------------------------------------------------
      }
    }
    
    ...

    dependencies {

        ...
        //-- FOR ADQ -------------------------------------------------------------------------------
        
        implementation 'com.qwertlab.adq:ADQ:7.1.0'
        
        //------------------------------------------------------------------------------------------
        ...
    }
    
    ...
```

<br>


* AndroidManifest.xml 설정

> 항상 귀사의 앱이 실행될 수 있도록 MainActivity에 launchMode 및 clearTaskOnLaunch 속성을 추가합니다.
<br>


```XML

    <application
        ...
    >

      ...
      
      <activity
        android:name=".MainActivity"
        android:launchMode="singleInstance"
        android:clearTaskOnLaunch="true"
      >
			


      ...

    </application>
    
```

<br>

* 연동 API 호출

> 귀사의 메인 액티비티의 onCreate() Method에 알람 권한이 없을 경우 권한을 요청합니다.<br>
> (QuickBar를 노출하기 위해서 알람 권한이 필요합니다.) <br>
> 귀사의 메인 액티비티의 onResume() Method에 알람 권한이 있을 경우 연동 API를 호출합니다.
  
<br>

```java
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-- ADQ Check Permission ------------------------------------------------------------------
        if(!ADQManager.isNotificationPermission(getApplicationContext())){
            //-- Request Notification Permission. [For - QuickBar]
            ADQManager.requestADQPermission(MainActivity.this, registerForActivityResult(new ActivityResultContracts.RequestPermission(), result -> {
                if(ADQManager.isNotificationPermission(getApplicationContext())){
                    ADQManager.initADQ(MainActivity.this, "Agent사이트에서 발급받은 AgentKey");
                }
            }));
        }
        //-- END ADQ -------------------------------------------------------------------------------
         
    }
    
    @Override
    protected void onResume() {
        super.onResume();

        //-- ADQ -----------------------------------------------------------------------------------
        
        if(ADQManager.isNotificationPermission(getApplicationContext())){
            ADQManager.initADQ(MainActivity.this, "Agent사이트에서 발급받은 AgentKey");
        }
        
        //------------------------------------------------------------------------------------------

    }
    
    
```

<br>

* 연동확인

> ADQ SDK 연동 후 앱 실행 시 ADQ 이용 동의창 팝업이 정상적으로 출력되어 QuickBar가 생성되는지 확인합니다.

<p align="center">
<img src='https://api.qwertlab.com/images/quickbar_img.gif' />
</p>

<br>

> 팝업창이 출력되지 않거나, 기능 동작이 안될 경우 Log 기록을 확인합니다. (ADQManager로 검색)

<p align="center">
<img src='https://api.qwertlab.com/images/adq/log.png' />
</p>

<br>

보다 더 자세한 내용을 확인하고 싶으시다면 아래의 링크 정보를 통해서 확인해보실 수 있습니다.

* <a href="https://api.qwertlab.com/adq/api_doc.php">API Document</a>
* <a href="https://api.qwertlab.com/adq/error_doc.php">오류해결</a>
* <a href="https://agent.qwertlab.com/">회원가입(Agent사이트)</a>
* <a href="https://www.qwertlab.com//">홈페이지</a>


<br>

<h3>License</h3>
<hr>

```java

Copyright © 2019-2023 QwertLab Inc. All Rights Reserved.

```




