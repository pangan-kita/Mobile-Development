package com.herdialfachri.pangankita.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.herdialfachri.pangankita.R

class ContactFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email: Button = view.findViewById(R.id.btn_email)
        email.setOnClickListener(this)

        val website: Button = view.findViewById(R.id.btn_website)
        website.setOnClickListener(this)

        val whatsapp: Button = view.findViewById(R.id.btn_whatsapp)
        whatsapp.setOnClickListener(this)

        val facebook: Button = view.findViewById(R.id.btn_facebook)
        facebook.setOnClickListener(this)

        // Mendapatkan referensi ke toolbar
        val toolbar: Toolbar = view.findViewById(R.id.toolbar_kontak)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onClick(v: View) {

        val animation = AnimationUtils.loadAnimation(context, R.anim.animasi)
        v.startAnimation(animation)

        when (v.id) {
            R.id.btn_email -> {
                val namaEmail = "adminpangankita@gmail.com"
                val bukaEmail = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:$namaEmail"))
                startActivity(bukaEmail)
            }

            R.id.btn_whatsapp -> {
                val phoneWhatsapp = "6282122506110"
                val dialWhatsappIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://api.whatsapp.com/send?phone=$phoneWhatsapp&text=Hello, I have some problem with the App")
                )
                startActivity(dialWhatsappIntent)
            }

            R.id.btn_website -> {
                val namaWebsite = "https://dashboard.bangkit.academy/"
                val bukaWesbite = Intent(Intent.ACTION_VIEW, Uri.parse(namaWebsite))
                startActivity(bukaWesbite)
            }

            R.id.btn_facebook -> {
                val facebookProfile = "http://instagram.com/google/"
                val bukaFacebook = Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfile))
                startActivity(bukaFacebook)
            }
        }
    }
}