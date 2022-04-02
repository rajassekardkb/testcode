package com.example.demo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.adapter.CartAdapter
import com.example.demo.database.AlertViewModel
import com.example.demo.databinding.ActivityExpenseListBinding
import com.example.demo.models.Address
import com.example.demo.models.Companies
import com.example.demo.models.Profile
import com.example.demo.models.ProfileResponse
import com.example.demo.util.ApiState
import com.example.demo.util.Utilities.showToast
import com.example.demo.viewmodel.AlertsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ExpenseListActivity : AppCompatActivity(), CartAdapter.OnItemClickListener,


    View.OnClickListener {
    private lateinit var binding: ActivityExpenseListBinding
    private lateinit var postAdapter: CartAdapter
    private val viewModel: AlertsListViewModel by viewModels()
    private val viewModels: AlertViewModel by viewModels()

    private lateinit var context: Context
    private val alertViewModel: AlertViewModel by viewModels()
    private var alertsList: List<ProfileResponse> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_expense_list)
        setContentView(binding.root)
        intializeViews()
        intializeData()
    }

    private fun intializeViews() {
        context = this
        postAdapter = CartAdapter(ArrayList(), this, context)
        binding.rvExpenseList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ExpenseListActivity)
            adapter = postAdapter
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                v: RecyclerView,
                h: RecyclerView.ViewHolder,
                t: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(h: RecyclerView.ViewHolder, dir: Int) =
                deleteItemFromDb(h.adapterPosition)
        }).attachToRecyclerView(binding.rvExpenseList)

    }

    private fun deleteItemFromDb(adapterPosition: Int) {
        //alertViewModel.delete(alertsList.get(adapterPosition))
        lifecycleScope.launch {
            delay(1000L)

        }

    }


    private fun callApi() {
        val mHashMap = HashMap<String, Any>()
        mHashMap["request_param"] = "Value"
        mHashMap["page"] = "1"
        viewModel.getEmployeeList(mHashMap)

        lifecycleScope.launchWhenStarted {
            viewModel.postStateFlow.collect {
                when (it) {
                    is ApiState.Loading -> {
                    }
                    is ApiState.Failure -> {
                        showToast(context, it.msg.toString())
                    }
                    is ApiState.Success<*> -> {
                        binding.rvExpenseList.isVisible = true
                        val result = it.result as List<ProfileResponse>
                        //alertsList = result.data
                        val listData: ArrayList<Profile> = ArrayList()

                        for (i in 0 until result.size) {
                            //saving to db
                            val data = result[i]
                            val dat = Profile(
                                data.id,
                                data.name,
                                data.username,
                                data.email,
                                data.image,
                                data.phone,
                                data.website
                            )
                            alertViewModel.insert(dat)
                            val datA = Address(
                                data.id,
                                data.address?.street,
                                data.address?.suite,
                                data.address?.city,
                                data.address?.zipcode
                            )
                            alertViewModel.insertAddress(datA)
                            val datC = Companies(
                                data.id,
                                data.company?.name,
                                data.company?.catchPhrase,
                                data.company?.bs
                            )
                            alertViewModel.insertCompany(datC)


                            listData.add(dat)


                        }



                        postAdapter.setData(listData)
                        postAdapter.notifyDataSetChanged()
                        search()
                    }
                    is ApiState.Empty -> {

                    }
                }
            }
        }

    }

    private fun intializeData() {
        alertViewModel.getList.observe(this, Observer { response ->
            alertsList = (response as List<ProfileResponse>)
            if (alertsList.isNotEmpty()) {
                println("Deia Na DB da")
                postAdapter.setData(response)
                postAdapter.notifyDataSetChanged()
                search()
            } else {
                println("Deia Na serverda")

                // get data from server
                callApi()
            }
        })
    }


    private fun search() {
        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                postAdapter.filter.filter(query)

                return true
            }
        })

    }

    override fun onClick(view: View?) {
        when (view?.id) {
        }
    }


    override fun onItemClicked(data: Profile) {
        val intent = Intent(this, CheckoutActivity::class.java)
        intent.putExtra("id", data.id)
        intent.putExtra("image", data.profile_image)
        intent.putExtra("name", data.name)
        intent.putExtra("email", data.email)
        intent.putExtra("phone", data.phone)
        intent.putExtra("user", data.username)
        intent.putExtra("website", data.website)
        startActivity(intent)

    }

    override fun onItemText(data: TextView, id: String) {
        viewModels.getCompany(id).observe(this) { response ->
            try {
                data.text = response.name

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }

        }

    }

}



