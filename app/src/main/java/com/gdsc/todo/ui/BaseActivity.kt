package com.gdsc.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

// 화면이 여러개 생길 때 보일러 플레이트를 줄이기 위해 base activity 를 생성한다.
open class BaseActivity<T : ViewDataBinding>(private val layoutId: Int) : AppCompatActivity() {

    private lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
    }
}
