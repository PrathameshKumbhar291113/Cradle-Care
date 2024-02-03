package com.cradlecare.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.LinearLayout
import com.cradlecare.R
import com.cradlecare.databinding.LoaderAnimationOverlayBinding

class Loader(context: Context) : Dialog(context){
    lateinit var binding: LoaderAnimationOverlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoaderAnimationOverlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        Glide.with(context).load(R.drawable.loader).into(binding.loaderImg)
        with(window){
            this?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            this?.setBackgroundDrawableResource(R.color.transparent_color)
            this?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            this?.statusBarColor =
                context.resources.getColor(R.color.rose_fam_400)
            this?.navigationBarColor =
                context.resources.getColor(R.color.rose_fam_400)
        }
    }
}
open class LoaderOverlay {
    companion object{
        private var loader: Loader? = null
        fun showLoaderAnimationDialog(
            context: Context?,
        ) {
            hideLoaderAnimationDialog()
            if (context != null) {
                try {
                    loader = Loader(context)
                    loader?.let { loader ->
                        loader.setCanceledOnTouchOutside(true)
                        loader.setCancelable(false)
                        loader.show()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun hideLoaderAnimationDialog() {
            if (loader != null && loader?.isShowing!!) {
                loader = try {
                    loader?.dismiss()
                    null
                } catch (e: Exception) {
                    null
                }
            }
        }
    }
}