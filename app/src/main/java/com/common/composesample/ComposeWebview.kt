package com.common.composesample

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView

/**
 * @Author: Sun
 * @CreateDate: 2022/12/13
 * @Description: java类作用描述
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ComposeWebview(
    url: String
){
    Column (modifier = Modifier.fillMaxSize()){
        val progress = remember{
            mutableStateOf(0f)
        }
        val visible = remember {
            mutableStateOf(true)
        }
        if(visible.value){
            LinearProgressIndicator(
                progress = progress.value/100,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red
            )
        }
        AndroidView(
            factory = { context->
                val webView = WebView(context)
                webView.apply {
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient(){}
                    loadUrl(url)
                }
            },
            modifier = Modifier.fillMaxSize(),
            update = {
                it.webChromeClient = object : WebChromeClient(){
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        progress.value = newProgress.toFloat()
                        if(newProgress == 100){
                            visible.value = false
                        }
                        super.onProgressChanged(view, newProgress)
                    }
                }
            }
        )
    }
}