package com.michaeltroger.gruenerpass.pdf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.michaeltroger.gruenerpass.MainViewModel
import com.michaeltroger.gruenerpass.R
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PdfPagerFragment : Fragment() {

    private val vm by activityViewModels<MainViewModel>()
    private var certificate: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.layout_item_pdf, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        certificate = view.findViewById(R.id.certificate)

        lifecycleScope.launch {
            if (vm.getPdfBitmap() == null) {
                if (vm.areBitmapsReady.filter { it }.first()) { // bitmap was not ready in time, wait for it
                    certificate?.setImageBitmap(vm.getPdfBitmap())
                }
            } else {
                certificate?.setImageBitmap(vm.getPdfBitmap())
            }
        }
    }

}