package org.dps.admin.ui.fragments

//import com.facebook.drawee.backends.pipeline.Fresco
//import kotlinx.android.synthetic.main.fragment_home.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_home.*
import org.dps.admin.R

class HomeFragment : Fragment() {

    companion object {
        @JvmStatic
        fun instance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).asGif().load("https://media3.giphy.com/media/XHwMtmnrD313nZFFuV/source.gif").into(mSimpleDraweeView);

    }
}