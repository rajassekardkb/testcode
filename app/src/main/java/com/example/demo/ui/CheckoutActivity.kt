package com.example.demo.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.demo.database.AlertViewModel
import com.example.demo.databinding.ActivityCheckoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_checkout.view.*

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity() {
    private val viewModel: AlertViewModel by viewModels()
    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var id: String
    private lateinit var image: String
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var phone: String
    private lateinit var website: String
    private lateinit var user: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //actionBar!!.setDisplayHomeAsUpEnabled(true);
        id = intent.getStringExtra("id").toString()
        image = intent.getStringExtra("image").toString()
        email = intent.getStringExtra("email").toString()
        website = intent.getStringExtra("website").toString()
        phone = intent.getStringExtra("phone").toString()
        user = intent.getStringExtra("user").toString()
        name = intent.getStringExtra("name").toString()
        try {
            setView()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setView() {
        Glide.with(this)
            .load(image)
            .into(binding.imgReceipt)
        binding.textname.text = name
        binding.textUserName.text = user
        binding.txtEmail.text = email
        binding.txtphone.text = phone
        binding.txtweb.text = website
        viewModel.getAddress(id).observe(this) { response ->
            binding.address.txtAddress.text =
                response.street + "," + response.city + ",\n" + response.suite + "," + response.zipcode
        }
        viewModel.getCompany(id).observe(this) { response ->
            try {
                binding.txtcomp.text = response.catchPhrase + ",\n" + response.bs

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }

        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}