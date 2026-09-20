package com.example.webviewapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // ॲप स्क्रीनवर पूर्ण साईझमध्ये WebView तयार करणे
        webView = WebView(this)
        setContentView(webView)

        // JavaScript आणि Storage चालू करणे (लॉगिन करण्यासाठी आवश्यक)
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true 
        webSettings.domStorageEnabled = true 

        // WhatsApp आणि Google Map च्या लिंक्स मोबाईलमधील मूळ ॲपमध्ये उघडण्यासाठी
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    // WhatsApp, Google Maps, Phone, किंवा Mail च्या लिंक्स तपासणे
                    if (url.startsWith("whatsapp:") || 
                        url.contains("wa.me") || 
                        url.contains("maps.google") || 
                        url.contains("goo.gl/maps") || 
                        url.startsWith("tel:") || 
                        url.startsWith("mailto:")) {
                        
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            startActivity(intent)
                            return true // लिंक बाहेर इतर ॲपमध्ये यशस्वीरीत्या पाठवली
                        } catch (e: Exception) {
                            Toast.makeText(this@MainActivity, "हे ॲप मोबाईलमध्ये इन्स्टॉल नाही!", Toast.LENGTH_SHORT).show()
                            return false
                        }
                    }
                }
                // वेबसाइटची इतर साधी पेजेस ॲपच्या आतच उघडतील
                return false
            }
        }

        // तुमची लाइव्ह GitHub Pages ची लिंक इथे लोड केली आहे
        webView.loadUrl("https://github.io")
    }

    // मोबाईलचे बॅक बटन दाबल्यावर ॲप एकदम बंद न होता वेबसाइटच्या मागच्या पेजवर जाण्यासाठी
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
