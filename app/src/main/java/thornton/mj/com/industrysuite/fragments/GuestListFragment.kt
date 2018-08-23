package thornton.mj.com.industrysuite.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import thornton.mj.com.industrysuite.R

/**
 * Created by mitchthornton on 8/21/18.
 */

class GuestListFragment : Fragment(){

    companion object {

        fun newInstance(): GuestListFragment {
            return GuestListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_guestlist, container, false)
    }


}