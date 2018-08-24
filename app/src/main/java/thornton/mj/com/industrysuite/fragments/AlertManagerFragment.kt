package thornton.mj.com.industrysuite.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import thornton.mj.com.industrysuite.R
import thornton.mj.com.industrysuite.adapters.AlertManagerAdapter

/**
 * Created by mitchthornton on 8/21/18.
 */

class AlertManagerFragment : Fragment(){


    // Initializing an empty ArrayList to be filled with animals
    val prePopulatedMessages: ArrayList<String> = ArrayList()

    companion object {

        fun newInstance(): AlertManagerFragment {
            return AlertManagerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        println("View Created!")

        populateMessages()

        val rootView : View = inflater!!.inflate(R.layout.fragment_alert_manager, container, false)

        //RECYCER
        val rv = rootView.findViewById<RecyclerView>(R.id.rv_alert_manager) as RecyclerView


//        val recyclerView : RecyclerView = RecyclerView(activity)

        // Creates a vertical Layout Manager
        rv.layoutManager = LinearLayoutManager(activity)

        // Access the RecyclerView Adapter and load the data into it
        rv.adapter = AlertManagerAdapter(prePopulatedMessages, activity)


//        return inflater?.inflate(R.layout.fragment_alert_manager, container, false)
        return rootView
    }




    private fun populateMessages(){
        prePopulatedMessages.add("The keg is tapped")
        prePopulatedMessages.add("Customer would like to speak to you")
        prePopulatedMessages.add("Unruly guest")
        prePopulatedMessages.add("Need to be clocked in")
        prePopulatedMessages.add("Need to be clocked out")
        prePopulatedMessages.add("Unlock door")
        prePopulatedMessages.add("Out of food")
        prePopulatedMessages.add("Out of beer")
        prePopulatedMessages.add("Need more change")
        prePopulatedMessages.add("Problem with the bathroom")
        prePopulatedMessages.add("Someone is requesting you")
        prePopulatedMessages.add("Job Applicant")
        prePopulatedMessages.add("Other")
    }


}