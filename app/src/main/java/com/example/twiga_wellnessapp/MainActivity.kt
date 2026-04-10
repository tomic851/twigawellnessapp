package com.example.twiga_wellnessapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {
    private var mInterstitial : InterstitialAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        initialize Admob ads
        MobileAds.initialize(this)
        val adview = findViewById<AdView>(R.id.adview)
        val adRequest = AdRequest.Builder().build()
//        display the ad
        adview.loadAd(adRequest)
//        load our interstitial ad
        loadInterstitialAd()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        find views by id
        val healthy_tips = findViewById<Button>(R.id.healthytips)
        val nutrition_activity = findViewById<Button>(R.id.nutrition_advice)
        val meditation = findViewById<Button>(R.id.meditation)
        val hydration = findViewById<Button>(R.id.hydration_alert)
        val startAlert = findViewById<Button>(R.id.start_exercise)
        val dailyMotivation = findViewById<Button>(R.id.daily_motivation)
        val weeklyGoals = findViewById<Button>(R.id.weekly_goals)
        val checkProgress = findViewById<Button>(R.id.check_progress)

        healthy_tips.setOnClickListener {
            val intent = Intent(applicationContext, Healthy_Tips::class.java)
            startActivity(intent)
            nutrition_activity.setOnClickListener {
                val intent = Intent(applicationContext, Nutrition_Activity :: class.java)
                startActivity(intent)
            }
            meditation.setOnClickListener {
                val intent = Intent(applicationContext, Meditation :: class.java)
                startActivity(intent)
                interstitialAd()
            }
            hydration.setOnClickListener {
                val intent = Intent(applicationContext, Hydration :: class.java)
                startActivity(intent)
            }
            startAlert.setOnClickListener {
                val intent = Intent(applicationContext, Start_Alert :: class.java)
                startActivity(intent)
            }
            dailyMotivation.setOnClickListener {
                val intent = Intent(applicationContext, Daily_Motivation :: class.java)
                startActivity(intent)
            }
            weeklyGoals.setOnClickListener {
                val intent = Intent(applicationContext, Weekly_Goals :: class.java)
                startActivity(intent)
            }
            checkProgress.setOnClickListener {
                val intent = Intent(applicationContext, Check_Progress :: class.java)
                startActivity(intent)
            }




        }

    }
//    load interstitial ad (function) requesting ad from ad mob
fun loadInterstitialAd(){
    val adRequest = AdRequest.Builder().build()
    InterstitialAd.load(
        this,
        "ca-app-pub-3940256099942544/1033173712",
        adRequest,
        object  : InterstitialAdLoadCallback(){
            override fun onAdLoaded(ad: InterstitialAd) {
//                load the ad and store in member variable
                mInterstitial = ad
            }
            override fun onAdFailedToLoad(error: LoadAdError){
//        setting ad to null if it fails eg network
                mInterstitial = null
            }

        }


    )
}

    fun interstitialAd(){
        if(mInterstitial != null){
            mInterstitial?.show(this)
        }

    }

}