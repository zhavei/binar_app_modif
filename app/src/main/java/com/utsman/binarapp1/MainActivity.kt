package com.utsman.binarapp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.utsman.binarapp1.fragment.AccountFragment
import com.utsman.binarapp1.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    companion object {
        // static constant
        const val INTENT_KEY = "key" // ini statis
        const val FRAGMENT_COUNT = "fragment_count"
    }
    override fun onStart() {
        super.onStart()
        println("binar main -------> onstart")
    }

    override fun onPause() {
        super.onPause()
        println("binar main -------> onpause")
    }

    override fun onResume() {
        super.onResume()
        println("binar main -------> onresume")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("binar main -------> ondestroy")
    }

    private var fragmentCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        println("binar main -------> oncreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast()
        snackbar()
        snackbarOnAboard()

        //hello world button to chat activity bring hardcoded string
        val buttonIntent: Button = findViewById(R.id.btn_intent_helloworld)
        buttonIntent.setOnClickListener {
            val intentChat = Intent(this, ChatActivity::class.java)
            val stringAnu = "Hallo saya button hello world"
            val bundle = bundleOf(INTENT_KEY to stringAnu) // ini hardcode
            intentChat.putExtras(bundle)
            startActivity(intentChat)
        }

        //attach_FRAGMENT yg bawah button hello world itu
        val buttonFragment: Button = findViewById(R.id.btn_fragment)
        buttonFragment.setOnClickListener {
            attachHomeFragment()
        }

        //call method create fragment slider
        setupViewPager()

       /* textview tengah clickable nerima/bawa data dari AppIntro Costum
          karena keynys sama sbnernya punya onBoardingAcvty*/
        val bundle = intent.extras
        val name = bundle?.getString("name")
        val age = bundle?.getInt("age")

        val tvBundleResult: TextView = findViewById(R.id.tv_bundle_result)
        tvBundleResult.text = name + " umurnya " + age.toString()

        tvBundleResult.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            //cara ngirim lagi bundle ke activity lain
            bundle?.let { intent.putExtras(it) }
            startActivity(intent)
        }

        //to ButtonDialog Activity
        val buttonDialog: Button = findViewById(R.id.btn_dialog)
        buttonDialog.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }

        //get kiriman dari AppIntro Actvty
        val nameIntro = intent.getStringExtra("name")
        val tvNameResult: TextView = findViewById(R.id.tv_name_result)
        tvNameResult.text = nameIntro
    }

    //inflate frame fragment YG di bawah button hello world
    private fun attachHomeFragment() {
        val homeFragment = HomeFragment()
        homeFragment.arguments = bundleOf(FRAGMENT_COUNT to fragmentCount)

        val containerId = R.id.fragment_container
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, homeFragment)
        fragmentTransaction.commit()

        fragmentCount += 1
    }

    //view pager for fragment slider yg di atas itu
    private fun setupViewPager() {
        val vpSlider: ViewPager = findViewById(R.id.vp_slider)
        val vpAdapter = SliderAdapter(supportFragmentManager)

        val homeFragment = HomeFragment()
        val accountFragment = AccountFragment()
        val fragmentList = listOf(
            homeFragment, accountFragment, AccountFragment(), AccountFragment(), AccountFragment(), AccountFragment(), AccountFragment(),
            AccountFragment()
        )
        vpAdapter.addFragmentList(fragmentList)
        vpSlider.adapter = vpAdapter
        vpSlider.offscreenPageLimit = 5

        // set posisi halaman
        //vpSlider.setCurrentItem(2, true)
    }

    private fun toast() {
        val button: Button = findViewById(R.id.btn_show_toast)
        button.setOnClickListener {
            Toast.makeText(this, "ini toast", Toast.LENGTH_SHORT).show()
        }
    }

    private fun snackbar() {
        val nameIntro = intent.getStringExtra("name")
        val button: Button = findViewById(R.id.btn_show_snackbar)
        val snackbar = Snackbar.make(button, "hello $nameIntro ", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("tutup") {
            onBackPressedDispatcher
        }

        button.setOnClickListener {
            snackbar.show()
        }
    }

    //show snackbar on mainactivity started
    private fun snackbarOnAboard() {
        val nameIntro = intent.getStringExtra("name")
        val contextView = findViewById<View>(R.id.main_activity)
        val snackbar = Snackbar.make(contextView, "hello $nameIntro ", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("tutup") {
            onBackPressedDispatcher
        }.show()
    }

    override fun onStop() {
        super.onStop()
        println("binar main -------> onstop")
    }

    override fun onRestart() {
        super.onRestart()
        println("binar main -------> onrestart")
    }
}