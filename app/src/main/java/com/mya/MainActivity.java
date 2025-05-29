package com.mya;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // WebView와 관련된 기능을 관리하는 객체
    private WebViewManager webViewManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // 액티비티의 레이아웃 설정

        // 스토리지 권한을 요청하는 헬퍼 클래스 호출
        PermissionHelper.requestStoragePermission(this);

        // 로그 출력: MainActivity의 onCreate가 호출되었음을 기록
        Log.d("MainActivity onCreate", "onCreate");

        // WebView와 관련된 작업을 관리할 WebViewManager 객체 초기화
        // activity_main.xml 레이아웃에서 webview 요소를 찾아서 WebViewManager에 전달
        webViewManager = new WebViewManager(this, findViewById(R.id.webview));

        // 화면에 있는 버튼들을 찾아서 각 버튼에 클릭 리스너를 설정
        Button btnBack = findViewById(R.id.btnBack);
        Button btnMenu = findViewById(R.id.btnMenu);
        Button btnMyPage = findViewById(R.id.btnMyPage);

        // 뒤로 가기 버튼 클릭 시 WebViewManager의 goBackIfPossible 메서드를 호출
        // 사용자가 WebView에서 뒤로 갈 수 있으면 뒤로 가기, 그렇지 않으면 다른 동작을 처리할 수 있도록 설정
        btnBack.setOnClickListener(v -> webViewManager.goBackIfPossible());

        // 메뉴 버튼 클릭 시 WebViewManager의 showMenuPopup 메서드를 호출
        // 메뉴 팝업을 화면에 표시하는 동작을 담당
        btnMenu.setOnClickListener(v -> webViewManager.showMenuPopup(v));

        // 마이 페이지 버튼 클릭 시 WebViewManager의 loadMyPage 메서드를 호출
        // "마이 페이지"를 로드하여 WebView에 표시
        btnMyPage.setOnClickListener(v -> webViewManager.loadMyPage());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 파일 선택 결과 처리: WebViewManager의 onFileChooserResult 메서드를 호출하여 파일 선택 결과를 전달
        // 파일 선택 후 그 결과를 WebView에서 처리할 수 있도록 전달
        webViewManager.onFileChooserResult(requestCode, resultCode, data);
    }
}
