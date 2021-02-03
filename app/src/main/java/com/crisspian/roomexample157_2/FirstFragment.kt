package com.crisspian.roomexample157_2

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.crisspian.roomexample157_2.databinding.FragmentFirstBinding
import com.crisspian.roomexample157_2.model.TaskDataBase
import com.crisspian.roomexample157_2.model.TaskEntity
import com.crisspian.roomexample157_2.viewModel.TaskViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding : FragmentFirstBinding
    private val viewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val adapter = TaskAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL))

        viewModel.allTask.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)
            }
        })

        adapter.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                val bundle = Bundle()
                bundle.putInt("id", it.id)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        })

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(context, "SE ELIMINO TODO", Toast.LENGTH_LONG).show()
                viewModel.deleteAll()
               true
           }
            else -> super.onOptionsItemSelected(item)
        }
    }

}