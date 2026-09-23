package com.example.mywebsite

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)
        setContentView(webView)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {

                val url = request.url.toString()

                if (
                    url.startsWith("tel:") ||
                    url.startsWith("whatsapp:") ||
                    url.startsWith("geo:") ||
                    url.startsWith("googlemaps:") ||
                    url.startsWith("https://maps.google.com") ||
                    url.startsWith("https://www.google.com/maps")
                ) {
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    } catch (e: Exception) {
                        // संबंधित app उपलब्ध नसल्यास काहीही करू नका
                    }
                    return true
                }

                if (!url.startsWith("https://rakeshmunjewar12-cell.github.io/my-website/")) {
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    } catch (e: Exception) {
                        // Browser/app उपलब्ध नसल्यास काहीही करू नका
                    }
                    return true
                }

                return false
            }
        }

        // तुमची GitHub website
        webView.loadUrl("https://rakeshmunjewar12-cell.github.io/my-website/")
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
