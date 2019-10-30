package com.android.mohamed_habib.ui


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android.mohamed_habib.network.responses.Product
import com.android.mohamed_habib.ui.base.BaseFragment
import com.android.mohamed_habib.ui.base.NavigationCommand
import com.android.mohamed_habib.ui.databinding.FragmentProductListBinding
import com.android.mohamed_habib.utils.isConnected
import com.android.mohamed_habib.utils.setDisplayHomeAsUpEnabled
import com.android.mohamed_habib.utils.setTitle
import com.android.mohamed_habib.utils.setup
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : BaseFragment() {
    override val _viewModel: ProductListViewModel by viewModel()
    private var isTablet = false
    private lateinit var binding: FragmentProductListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        binding.viewModel = _viewModel
        binding.lifecycleOwner = this

        isTablet = resources.getBoolean(R.bool.isTablet)

        setDisplayHomeAsUpEnabled(false)
        setTitle(getString(R.string.app_name))

        setupRecyclerView()

        observers()

        binding.refreshLayout.setOnRefreshListener { loadProducts() }

        return binding.root
    }

    private fun setupRecyclerView() {
        val adapter = CategoryListAdapter { selectedProduct ->
            navigateToDetails(selectedProduct)
        }
        binding.categoriesRecyclerView.setup(adapter)
    }

    private fun observers() {
        _viewModel.showLoading.observe(this, Observer {
            if (!it) binding.refreshLayout?.isRefreshing = false
        })
        _viewModel.showSnackBar.observe(this, Observer {
            var txt = it
            if (TextUtils.isEmpty(it) || it == "null") {
                txt = getString(R.string.error_happened)
            }
            Snackbar.make(binding.refreshLayout, txt, Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.retry)) { loadProducts() }.show()
        })
    }

    private fun loadProducts() {
        activity?.isConnected {
            if (it) {
                _viewModel.loadProducts()
            } else {
                _viewModel.showSnackBar.value = getString(R.string.check_internet_connection)
                _viewModel.invalidateShowNoData()
                binding.refreshLayout.isRefreshing = false
            }
        }
    }

    private fun navigateToDetails(product: Product) {
        when {
            isTablet -> {
                //at the tablet mode, the fragment already exists at the view, so I only need to update the details fragment with the data
                (product_details_fragment as ProductDetailsFragment)._viewModel.selectedProduct.postValue(
                    product
                )
            }
            else ->
                _viewModel.navigationCommand.postValue(
                    NavigationCommand.To(ProductListFragmentDirections.toProductDetail(product))
                )
        }
    }
}
