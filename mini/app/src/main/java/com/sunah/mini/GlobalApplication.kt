package com.sunah.mini

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
//카카오로그인
class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "53c8237d24a6d7196b6f6aac30ddc5b5")
    }
}