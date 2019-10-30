package com.android.mohamed_habib.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android.mohamed_habib.ui.base.BaseFragment
import com.android.mohamed_habib.ui.databinding.FragmentProductDetailsBinding
import com.android.mohamed_habib.utils.setDisplayHomeAsUpEnabled
import com.android.mohamed_habib.utils.setTitle
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsFragment : BaseFragment() {
    public override val _viewModel: ProductDetailsViewModel by viewModel()

    private lateinit var binding: FragmentProductDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_product_details,
                container,
                false
            )
        binding.lifecycleOwner = this
        binding.viewModel = _viewModel

        //by default no data in the fragment, so show nothing.
        _viewModel.rootVisibility.value = false

        arguments?.let {
            val args = ProductDetailsFragmentArgs.fromBundle(it)
            _viewModel.selectedProduct.postValue(args.selectedProduct)
            setDisplayHomeAsUpEnabled(true)
            setTitle(args.selectedProduct.name)
        }

        _viewModel.selectedProduct.observe(this, Observer {
            _viewModel.rootVisibility.value = true
        })

        return binding.root
    }
}
