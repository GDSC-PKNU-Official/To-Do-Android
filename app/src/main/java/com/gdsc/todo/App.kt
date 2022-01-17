package com.gdsc.todo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// compile time 시 표준 컴포넌트 빌딩에 필요한 클래스 초기화
// @AndroidEntryPoint 어노테이션을 사용해 멤버 주입을 하는 것이 가능해진다.
@HiltAndroidApp
class App : Application()